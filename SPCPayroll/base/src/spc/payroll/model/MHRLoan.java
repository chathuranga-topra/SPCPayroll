package spc.payroll.model;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.MBPartner;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.MHRAttribute;
import org.eevolution.model.MHRConcept;

@SuppressWarnings("serial")
public class MHRLoan extends X_HR_Loan implements DocAction , DocOptions{

	public MHRLoan(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRLoan(Properties ctx, int HR_Loan_ID, String trxName) {
		super(ctx, HR_Loan_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	protected boolean beforeSave(boolean newRecord){
		
		//validate duplicate loans opening
		 int length = MHRLoan.getLoans(getCtx(), getC_BPartner_ID(), getHR_LoanType_ID(), " DOCSTATUS NOT IN('CL') AND "
		 		+ "HR_LOAN_ID NOT IN ("+get_ID()+")", get_TrxName()).length;
		 if(length >= 1)
			 throw new AdempiereException("DUPLICATE LOAN DOCUMENT!Your are not allowed to open duplicate loans");
				 
		
		//validation for fastival advance for current year opening more than one
		if(this.getHR_LoanType_ID() == 1000006){ // loan type is hard corded
			String sql = "SELECT NVL(CASE WHEN EXTRACT (YEAR FROM GRANTEDDATE) = EXTRACT (YEAR FROM CURRENT_DATE)THEN"
			+ " CAST (DOCUMENTNO AS VARCHAR(12)) ELSE 'N' END , 'N') FROM HR_Loan WHERE C_BPartner_ID = ? "
			+ " AND HR_LoanType_ID = ? AND HR_LOAN_ID NOT IN (?)"
			+ " AND AD_Client_ID = ? ";
			
			String res = DB.getSQLValueString(get_TrxName(), sql, getC_BPartner_ID() , getHR_LoanType_ID() , get_ID() , getAD_Client_ID());
			
			if(!(res == null || res.equalsIgnoreCase("N")))
				throw new AdempiereException("Duplicate advance! ~ Document No - " + res);
		}
		
		//basic sallary :: concept is hard corded
		MHRConcept basicSallaryConcept = new MHRConcept(getCtx(), 1000001, this.get_TrxName());
		//no basic sallary
		if(basicSallaryConcept == null || basicSallaryConcept.get_ID() == 0){
			throw new AdempiereException("Process failed : canot find basic sallary concept : HR_Concept_ID " + 1000001);
		}
		
		final String whereClause = "HR_Concept_ID= ? AND ISACTIVE = 'Y' AND AD_CLIENT_ID = ? AND C_BPartner_ID = ?"; 
		MHRAttribute atribute = new Query(this.getCtx(), MHRAttribute.Table_Name, whereClause, null)
		.setParameters
		(
			basicSallaryConcept.get_ID(),
			this.getAD_Client_ID(),
			this.getC_BPartner_ID()
		).firstOnly();
		
		if(atribute == null){
			throw new AdempiereException("No basic salary attribute : please chack it before process loan");
		}
		
		BigDecimal maxLoanForBasicSallary = atribute.getAmount().multiply(new BigDecimal(10));
		
		//loan amount should me lessor eual to 10 times of basic sallary
		if(maxLoanForBasicSallary.doubleValue() < this.getLoanAmount().doubleValue()){
			throw new AdempiereException("Basic sallary * 10 exceeds Loan amount!");
		}
		
		//validate minimum maximum loan amounts with loan type min max amount
		MHRLoanType loanType = (MHRLoanType) this.getHR_LoanType();
		
		if(!(loanType.getMinAmt().doubleValue() <= this.getLoanAmount().doubleValue() && loanType.getMaxAmt().doubleValue() >= this.getLoanAmount().doubleValue())){
			throw new AdempiereException("Loan Min Max amount mismatch with loan type : " + loanType.getName());
		}
		
		//set default installment count
		if(this.getInstallmentCount() == 0){
			//set loan type default count
			this.setInstallmentCount(loanType.getInstallmentCount());
		}
		
		//validate for interrest attribute with loan type interest attribute
		if(getHR_LoanType().getInterestConcept_ID() == 0){ //no interest attribute
			setInterestAttribute_ID(0);
		}
		
		//Within 40% limit grant should be given (From basic+safety+travelling+cost of living 40% can be taken).
		String sql = "SELECT * FROM";
		
		return true;
	}

	@Override
	public boolean processIt(String action) throws Exception {
		
		if(action.equals("--") && getDocAction().equalsIgnoreCase("PR")&& getDocStatus().equals("DR"))
			this.prepareIt();
		else if(action.equals("CO") && getDocAction().equalsIgnoreCase("CO") && getDocStatus().equals("IP"))
			this.completeIt();
		else if(action.equals("--") && getDocAction().equalsIgnoreCase("CL") && getDocStatus().equals("CO"))
			this.closeIt();
		else if(action.equals("--") && getDocAction().equalsIgnoreCase("VO"))
			this.voidIt();
		
		return true;
	}

	@Override
	public boolean unlockIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean invalidateIt() {
		// TODO Auto-generated method stub
		return false;
	}

	//when preparing create loan schedule
	@SuppressWarnings("deprecation")
	@Override
	public String prepareIt(){
		
		if(this.getLoanAmount().intValue() == 0)
			throw new AdempiereException("Process Terminated! Zero loan amount");
		
		if(this.getInstallmentCount() == 0)
			throw new AdempiereException("Process Terminated! Zero Installment count");
		
		//delete existing records
		String sql = "DELETE FROM HR_LOANSCHEDULE WHERE HR_Loan_ID = ? ";
		DB.executeUpdate(sql, this.get_ID(), this.get_TrxName());
		
		int installmentCount = this.getInstallmentCount();
		MHRLoanSchedule sc = null;
		Date dateGranted = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateGranted);
		
		//set loan payroll applicable date :: always next month 25 th
		cal.add(Calendar.MONTH, 1);
		Date d = new Date(cal.getTime().getTime());
		d.setDate(1);
		cal.setTime(d);
		setPayrollEffectiveDate(new Timestamp(d.getTime()));
		
		BigDecimal ir = this.getHR_LoanType().getRate();
		double monthCapital =  this.getLoanAmount().doubleValue() / installmentCount;
		double interest = ((((this.getLoanAmount().doubleValue() * ir.doubleValue() / 100) * 1 / 12) * (installmentCount + 1) / installmentCount) * 1 / 2);
		
		for(int i = 0;i < installmentCount;i++){
			
			sc = new MHRLoanSchedule(p_ctx, 0, this.get_TrxName());
			sc.setCapitalAmt(new BigDecimal(monthCapital).setScale(2, RoundingMode.HALF_UP));
			sc.setInterestAmt(new BigDecimal(interest).setScale(2, RoundingMode.HALF_UP));
			sc.setHR_Loan_ID(this.get_ID());
			sc.setSeqNo(i+1);
			//set effective date for loan premium applicable for
			cal.setTime(d);
	        cal.add(Calendar.MONTH, i);
	        sc.setEffectiveFrom(new Timestamp(cal.getTime().getTime()));
	        sc.save();
		}
		
		this.setDocStatus("IP");
		this.save();
	
		return "Line Created : " + installmentCount;
	}

	@Override
	public boolean approveIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String completeIt() {
		
		this.prepareIt();
		
		this.setGrantedDate(new Timestamp(System.currentTimeMillis()));
		
		//create a concept atribute for particulart employee
		//If interest for separate concept
		if(getHR_LoanType().getInterestConcept_ID() > 0){
			MHRAttribute inte = new MHRAttribute(this.getCtx() , 0 , this.get_TrxName());
			inte = new MHRAttribute(this.getCtx() , 0 , this.get_TrxName());
			inte.setHR_Concept_ID(this.getHR_LoanType().getInterestConcept_ID());
			inte.setValidFrom(this.getPayrollEffectiveDate());
			inte.setC_BPartner_ID(this.getC_BPartner_ID());
			inte.setColumnType(this.getHR_LoanType().getHR_Concept().getColumnType());
			inte.set_ValueOfColumn("processed", "Y");
			inte.save();
			//set interest atribute
			setInterestAttribute_ID(inte.get_ID());
		}
		
		//capital
		MHRAttribute atribute = new MHRAttribute(this.getCtx() , 0 , this.get_TrxName());
		atribute = new MHRAttribute(this.getCtx() , 0 , this.get_TrxName());
		atribute.setHR_Concept_ID(this.getHR_LoanType().getHR_Concept_ID());
		atribute.setValidFrom(this.getPayrollEffectiveDate());
		atribute.setC_BPartner_ID(this.getC_BPartner_ID());
		atribute.setColumnType(this.getHR_LoanType().getHR_Concept().getColumnType());
		atribute.set_ValueOfColumn("processed", "Y");
		atribute.save();
		
		setDocStatus("CO");
		setDocAction("CL");
		setProcessed(true);
		save();
		//set capitle atribute
		setHR_Attribute_ID(atribute.get_ID());
		setGrantedDate(new Timestamp(System.currentTimeMillis()));
		
		return null;
	}

	@Override
	public boolean voidIt() {
		
		//inactive the atribute for partucular loan
		MHRAttribute atribute = new MHRAttribute(this.getCtx() , this.getHR_Attribute_ID() , this.get_TrxName());
		atribute.setIsActive(false);
		atribute.setDescription("**VOIDED**");
		atribute.set_CustomColumn("processed", "Y");
		atribute.save();
		
		//loan schedule
		String sql = "UPDATE HR_LoanSchedule SET ISACTIVE = 'N' , PROCESSED = 'Y' WHERE HR_Loan_ID = ? ";
		DB.executeUpdate(sql, get_ID(), get_TrxName());
		
		setDocStatus("VO");
		setDocAction("--");
		save();
		
		return false;
	}

	@Override
	public boolean closeIt() {
		
		//check for balance
		if(this.getBalance(this).compareTo(new BigDecimal(0)) == 1){
			throw new AdempiereException("Loans canot be closed with balance!");
		}else{
			this.setDocStatus("CL");
			this.setDocAction("--");
			this.save();
		}
		
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reActivateIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocumentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getC_Currency_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int customizeValidActions(String docStatus, Object processing, String orderType, String isSOTrx, int AD_Table_ID, String[] docAction, String[] options, int index) {
		if (docStatus.equals(DocumentEngine.STATUS_Drafted)
    			|| docStatus.equals(DocumentEngine.STATUS_Invalid)) {
    		options[index++] = DocumentEngine.ACTION_Prepare;
    	}
    	// If status = Completed, add "Reactivate" in the list
    	if (docStatus.equals(DocumentEngine.STATUS_Completed)) {
    		//options[index++] = DocumentEngine.ACTION_ReActivate;
    		options[index++] = DocumentEngine.ACTION_Void;
    	}
		return index;
	}
	
	public static MHRLoan[] getLoans(Properties ctx, int C_BPartner_ID , String trxName){
		
		List<MHRLoan> list = new Query(ctx, Table_Name, COLUMNNAME_C_BPartner_ID+"=? AND DOCSTATUS = 'CO' AND ISACTIVE = 'Y' ", trxName)
		.setParameters(C_BPartner_ID)
		.list();
		return list.toArray(new MHRLoan[list.size()]);
	}
	
	public static MHRLoan[] getLoans(Properties ctx, int C_BPartner_ID ,int loantype , String where ,String trxName){
		
		String sqlWhere = COLUMNNAME_C_BPartner_ID+"=? AND ISACTIVE = 'Y' AND " + where + " AND HR_LOANTYPE_ID = "+ loantype;
		
		List<MHRLoan> list = new Query(ctx, Table_Name, sqlWhere, trxName)
		.setParameters(C_BPartner_ID)
		.list();
		return list.toArray(new MHRLoan[list.size()]);
	}
	
	public static MHRLoanSchedule[] getLoanSchedule(Properties ctx, int HR_Loan_ID , String trxName){
		
		List<MHRLoanSchedule> list = new Query(ctx, MHRLoanSchedule.Table_Name, COLUMNNAME_HR_Loan_ID+"=? AND ISACTIVE = 'Y' ", trxName)
		.setParameters(HR_Loan_ID)
		.list();
		return list.toArray(new MHRLoanSchedule[list.size()]);
	}
	
	public static MHRLoanSchedule[] getLoanUpPaidSchedule(Properties ctx, int HR_Loan_ID , String trxName){
		
		List<MHRLoanSchedule> list = new Query(ctx, MHRLoanSchedule.Table_Name, COLUMNNAME_HR_Loan_ID+"=? AND ISPAID = 'N' AND ISACTIVE = 'Y'", trxName)
		.setParameters(HR_Loan_ID)
		.list();
		return list.toArray(new MHRLoanSchedule[list.size()]);
	}
	
	public static MHRLoanSchedule getDuePaymentSc(Properties ctx, Timestamp  payrolDate ,int  HR_Loan_ID,  String trxName){
		
		String dateFormat = "dd-MM-yyyy";
		String dateString = new SimpleDateFormat(dateFormat).format(new Date(payrolDate.getTime()));
		
		String sql = "SELECT HR_LoanSchedule_ID  FROM HR_LoanSchedule WHERE HR_Loan_ID = ? "
			+ "AND IsPaid = 'N' AND effectivefrom <= to_date('"+dateString+"','"+dateFormat+"') "
			+ "ORDER BY 1 FETCH FIRST 1 ROWS ONLY";
		
		int MHRLoanSchedule_ID = DB.getSQLValue(trxName, sql, HR_Loan_ID);
		return new MHRLoanSchedule(ctx ,MHRLoanSchedule_ID ,trxName);
	}
	
	public static BigDecimal getBalance(MHRLoan loan){
		
		String sql = "SELECT NVL(SUM(CAPITALAMT) , 0) FROM HR_LoanSchedule WHERE HR_Loan_ID=? AND ISPAID = 'Y' AND ISACTIVE = 'Y'";
		BigDecimal paid = DB.getSQLValueBD(loan.get_TrxName(), sql, loan.get_ID());
		
		return loan .getLoanAmount().subtract(paid);
	}
	
}
