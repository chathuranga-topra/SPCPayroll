package spc.payroll.model;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.ADialog;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.MBPartner;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.eevolution.model.MHRAttribute;
import org.eevolution.model.MHRConcept;
import org.eevolution.model.MHREmployee;
import org.eevolution.model.MHRMovement;

import com.sun.corba.ee.impl.orbutil.fsm.GuardedAction;

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
		this.validateDuplicate();
		
		//basic salary :: concept is hard corded
		MHRConcept basicSallaryConcept = new MHRConcept(getCtx(), HardCodedVal.hr_LoanType_idBasicsalary, this.get_TrxName());
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
		
		//validation for festival advance for current year opening more than one
		if(this.getHR_LoanType_ID() == HardCodedVal.hr_LoanType_idFestivalAdvance){
			this.validateFastivalAdvance();
		}
		
		//salary advance : one for one month
		if(getHR_LoanType_ID() == HardCodedVal.hr_LoanType_idSalaryAdvance){
			this.validateSallaryAdvance();
		}
		
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
		
		Trx trx = Trx.get(Trx.createTrxName(), true);
		trx.start();
		
		//delete existing records
		String sql = "DELETE FROM HR_LOANSCHEDULE WHERE HR_Loan_ID = ? ";
		DB.executeUpdate(sql, this.get_ID(), trx.getTrxName());
		
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
			
			sc = new MHRLoanSchedule(p_ctx, 0, trx.getTrxName());
			
			sc.setCapitalAmt(new BigDecimal(monthCapital).setScale(0, RoundingMode.HALF_UP));
			sc.setInterestAmt(new BigDecimal(interest).setScale(2, RoundingMode.HALF_UP));
			sc.setHR_Loan_ID(this.get_ID());
			sc.setSeqNo(i+1);
			//set effective date for loan premium applicable for
			cal.setTime(d);
	        cal.add(Calendar.MONTH, i);
	        sc.setEffectiveFrom(new Timestamp(cal.getTime().getTime()));
	        sc.save();
		}
		
		trx.commit(); //do not delete
		
		//less the total capitle amount difference from last schedule line
		sql = "SELECT LOANAMOUNT - (SELECT SUM(CAPITALAMT) FROM HR_LoanSchedule WHERE HR_Loan_ID=?) FROM HR_Loan WHERE HR_Loan_ID=?";
		BigDecimal diff = DB.getSQLValueBD(get_TrxName(), sql, get_ID() , get_ID());
		sc.setCapitalAmt(sc.getCapitalAmt().add(diff));
		sc.save();
		
		//calculate 40 Present payment feasible range based on the last payroll
		this.createFourtyPresentLines(trx);
		
		//this.set loan installemet value
		this.setLoanInstallment(sc.getCapitalAmt().add(sc.getInterestAmt().subtract(diff)));
		
		//40 present validation
		setIsValid(getLoanInstallment().doubleValue() <= (getFourtyPresent().doubleValue() - getTotalDeduction().doubleValue()));
	
		if(!isValid())
			//there can be loans which is not validated the 40 
			setIsValid(getFourtyPresent().doubleValue() == 0.00);
		
		if(!isValid()) {
			sql = "update HR_Loan set isvalid = 'N' where hr_loan_id = ?";
			DB.executeUpdate(sql, get_ID(), trx.getTrxName());
		}	
		
		trx.commit();trx.close();
		
		if(!isValid()) {
			throw new AdempiereException("LOAN PROCESS STOPED! 40 present validation failed!");
		}
		
		this.setDocStatus("IP");
		this.save();
	
		return "Completed!";
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
		
		//set capitle atribute
		setHR_Attribute_ID(atribute.get_ID());
		
		long dayDiff = getPayrollEffectiveDate().getTime() - getGrantedDate().getTime();
		dayDiff = dayDiff/1000/60/60/24;
		
		//interest for date between payroll effective date and loan  granted date
		double arreasInterest = getLoanAmount().doubleValue() * getHR_LoanType().getRate().doubleValue() / 100.00/365.00 * dayDiff;
		
		//adjust the first month interest from granted date and payroll effective date
		int HR_LoanSchedule_ID = MHRLoanSchedule.getAllIDs(MHRLoanSchedule.Table_Name, "SeqNo=1 AND HR_Loan_ID = " + get_ID(), get_TrxName())[0];
		MHRLoanSchedule sc = new MHRLoanSchedule(getCtx(), HR_LoanSchedule_ID, get_TrxName());
		
		sc.setInterestAmt(sc.getInterestAmt().add(new BigDecimal(arreasInterest).setScale(2, RoundingMode.HALF_UP)));
		sc.save();
		
		setDocStatus("CO");
		setDocAction("CL");
		setProcessed(true);
		save();
		
		String sql = "update HR_LoanGurantee set processed = 'Y' where hr_loan_id = ?";
		DB.executeUpdate(sql, get_ID(), get_TrxName());
		
		return null;
	}

	@Override
	public boolean voidIt() {
		
		//inactive the attribute for particular loan
		MHRAttribute atribute = new MHRAttribute(this.getCtx() , this.getHR_Attribute_ID() , this.get_TrxName());
		
		if(atribute.get_ID() != 0){//no attribute
			
			atribute.setIsActive(false);
			atribute.setDescription("**VOIDED**");
			atribute.set_CustomColumn("processed", "Y");
			atribute.save();
		}
		
		//Interest attribute
		if(!(getInterestAttribute() == null || getInterestAttribute().getHR_Attribute_ID() == -1)) {
			atribute = (MHRAttribute) getInterestAttribute();
			atribute.setIsActive(false);
			atribute.setDescription("**VOIDED**");
			atribute.set_CustomColumn("processed", "Y");
			atribute.save();
		}
		
		//loan schedule
		String sql = "UPDATE HR_LoanSchedule SET ISACTIVE = 'N' , PROCESSED = 'Y' WHERE HR_Loan_ID = ? ";
		DB.executeUpdate(sql, get_ID(), get_TrxName());
		
		setDocStatus("VO");
		setDocAction("--");
		setProcessed(true);
		
		return true;
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
	
	public static MHRLoanSchedule[] getLoanSchedule(Properties ctx,int HR_Loan_ID, String where , String trxName){
		
		List<MHRLoanSchedule> list = new Query(ctx, MHRLoanSchedule.Table_Name, COLUMNNAME_HR_Loan_ID+"=? AND ISACTIVE = 'Y' AND " + where, trxName)
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
	
	public static int getPaidInstallmentCount(MHRLoan loan){
		String WhereClause = " HR_Loan_ID="+loan.get_ID()+" AND ISPAID = 'Y' ";
		return MHRLoan.getAllIDs("HR_LoanSchedule", WhereClause, loan.get_TrxName()).length;
	}
	
	public static int getPayableInstallmentCount(MHRLoan loan){
		
		String WhereClause = " HR_Loan_ID="+loan.get_ID()+" AND ISPAID = 'N' ";
		return MHRLoan.getAllIDs("HR_LoanSchedule", WhereClause, loan.get_TrxName()).length;
	}
	
	private void validateDuplicate(){
		
		int length = MHRLoan.getLoans(getCtx(), getC_BPartner_ID(), getHR_LoanType_ID(), " DOCSTATUS NOT IN('CL' , 'VO') AND "
		 		+ "HR_LOAN_ID NOT IN ("+get_ID()+")", get_TrxName()).length;
		 if(length >= 1) {
			 //second validation validate for renewal type new loan
			 if(!isRenewalLoan())
				 throw new AdempiereException("DUPLICATE LOAN DOCUMENT!Your are not allowed to open duplicate loans");
		 }
			 
	}
	
	private void validateFastivalAdvance(){
		
		//one year can get only one Festival advance
		String sql = "SELECT NVL(CASE WHEN EXTRACT (YEAR FROM GRANTEDDATE) = EXTRACT (YEAR FROM CURRENT_DATE)THEN"
		+ " CAST (DOCUMENTNO AS VARCHAR(12)) ELSE 'N' END , 'N') FROM HR_Loan WHERE C_BPartner_ID = ? "
		+ " AND HR_LoanType_ID = ? AND HR_LOAN_ID NOT IN (?)"
		+ " AND AD_Client_ID = ? ";
		
		String res = DB.getSQLValueString(get_TrxName(), sql, getC_BPartner_ID() , getHR_LoanType_ID() , get_ID() , getAD_Client_ID());
		
		if(!(res == null || res.equalsIgnoreCase("N")))
			throw new AdempiereException("Duplicate advance! ~ Document No - " + res);
	
	}
	
	private void validateSallaryAdvance() {
		
		//salary advance can be grant 50 of selected earnings
		
		String sql = "SELECT sum(m.amount * f.percentage / 100) as totalearnings " + 
		"FROM  HR_LoanMaxAmtFactors f " + 
		"inner join hr_movement m on f.hr_concept_id = m.hr_concept_id " + 
		"where f.HR_LoanType_ID=? " + 
		"and m.c_bpartner_id = ? " + 
		"and f.isactive = 'Y' " + 
		"and m.hr_process_id = ? ";
		
		int latsHrProcessId = getLastPayrollProcessId(get_TrxName());
		if(latsHrProcessId == -1)
			throw new AdempiereException("No previous payroll movements found -process failed");
		
		BigDecimal val = DB.getSQLValueBD(get_TrxName(), sql, getHR_LoanType_ID() , getC_BPartner_ID() ,latsHrProcessId);
		
		if(val.doubleValue() < getLoanAmount().doubleValue()) {
			
			throw new AdempiereException("Excess loan amount  : " + val + "  >= " + "Loan amount - " + getLoanAmount());
		}
	}
	
	private int getLastPayrollProcessId(String trxName) {
		
		String sql = "select p.hr_process_id from  HR_Process p " + 
			"inner join HR_Movement m on p.hr_process_id = m.hr_process_id " + 
			"where m.c_bpartner_id = ?  and p.docstatus IN ('CO' , 'CL') fetch first 1 rows only";
		
		return DB.getSQLValue(trxName, sql, this.getC_BPartner_ID());
	}
	
	private void createFourtyPresentLines(Trx trx) {

		
		//delete all lines before prepare
		String sql = "delete from HR_LoanFourtyPresent where HR_Loan_ID = ?";
		DB.executeUpdate(sql, get_ID(), trx.getTrxName());
		
		//last payroll process id
		int latsHrProcessId = getLastPayrollProcessId(trx.getTrxName());
		
		if(latsHrProcessId == -1)
			throw new AdempiereException("No previous payroll movements found - 40 present validation failed");
		
		//this has done only based on the payroll movement
		sql = "select p.hr_process_id,bp.name,m.hr_concept_id, " + 
			" (case when m.hr_concept_category_id = ? " + 
			" then m.amount * -1 when m.hr_concept_category_id = ? then m.amount end) * f.percentage /100  as amount " + 
			" ,m.hr_movement_id , m.hr_concept_category_id , " + 
			" (case when m.hr_concept_category_id = ? then  con.name " +
			" when m.hr_concept_category_id = ? then con.name || ' - ' ||m.amount  end) as conceptname, " +
			" m.hr_movement_id " + 
			" from HR_Process p " + 
			" inner join HR_Movement m on p.hr_process_id = m.hr_process_id " + 
			" inner join c_bpartner bp on bp.c_bpartner_id = m.c_bpartner_id " + 
			" inner join HR_LoanTypeFactors f on f.hr_concept_id = m.hr_concept_id " +
			" inner join hr_concept con on con.hr_concept_id = m.hr_concept_id " + 
			" where m.c_bpartner_ID=? " + 
			" and f.hr_loantype_id = ? and f.isactive = 'Y' " + 
			" and  p.hr_process_id = ? " + 
			" and m.hr_movement_id not in " + 
			" (select sh.interestmovement_id from HR_LoanSchedule sh  inner join hr_loan l on l.hr_loan_id = sh.hr_loan_id " + 
			" where sh.interestmovement_id IN (select interestmovement_id from HR_Movement where HR_Process_ID=?) " + 
			" and l.docstatus = 'CL'" + 
			" union " + 
			" select sh.hr_movement_id  from HR_LoanSchedule sh  inner join hr_loan l on l.hr_loan_id = sh.hr_loan_id " + 
			" where sh.hr_movement_id IN (select hr_movement_id from HR_Movement where HR_Process_ID=?) " + 
			" and l.docstatus = 'CL') " + 
			" order by m.hr_concept_category_id , m.amount desc ";
		
		PreparedStatement ps = null;ResultSet rs = null;
		MHRLoanFourtyPresent lfp = null;
		int seqNo = 10;
		
		try {
			ps = DB.prepareStatement(sql, trx.getTrxName());
			
			ps.setInt(1, HardCodedVal.hr_concept_category_idDeduction);
			ps.setInt(2, HardCodedVal.hr_concept_category_idEarning);
			ps.setInt(3, HardCodedVal.hr_concept_category_idDeduction);
			ps.setInt(4, HardCodedVal.hr_concept_category_idEarning);
			
			ps.setInt(5, this.getC_BPartner_ID());
			ps.setInt(6, getHR_LoanType_ID());
			ps.setInt(7, latsHrProcessId);
			ps.setInt(8, latsHrProcessId);
			ps.setInt(9, latsHrProcessId);
			
 			rs = ps.executeQuery();
 			
 			while(rs.next()) {
 				
 				lfp = new MHRLoanFourtyPresent(p_ctx, 0, get_TrxName());
 				lfp.setName(rs.getString("conceptname"));
 				lfp.setAmount(rs.getBigDecimal("amount"));
 				lfp.setHR_Concept_Category_ID(rs.getInt("hr_concept_category_id"));
 				lfp.setHR_Loan_ID(get_ID());
 				lfp.setSeqNo(seqNo);
 				lfp.setHR_Movement_ID(rs.getInt("hr_movement_id"));
 				lfp.save(trx.getTrxName());
 				
 				seqNo+=10;
 			}
		
			//based on loan guarantee :: validate loan applicant signed for any loan and 40 present include 
			//50% of loan installment
			
			sql = "select lt.name as loantype , bp.name as signedto, l.loaninstallment *-.5 as amount , l.documentno  from HR_LoanGurantee g " + 
				" inner join hr_loan l on g.hr_loan_id = l.hr_loan_id " + 
				" inner join c_bpartner bp on l.c_bpartner_id = bp.c_bpartner_id " + 
				" inner join hr_loantype lt on l.hr_loantype_id = lt.hr_loantype_id " + 
				" where g.c_bpartner_id = ? " + 
				" and l.docstatus = 'CO'";
			
			ps = DB.prepareStatement(sql, trx.getTrxName());
			ps.setInt(1, this.getC_BPartner_ID());
			
			rs = ps.executeQuery();
				
			while(rs.next()) {
				
				lfp = new MHRLoanFourtyPresent(p_ctx, 0, get_TrxName());
				lfp.setName("Loan Guarantee : Loan Doc No - " + rs.getString("documentno"));
				lfp.setAmount(rs.getBigDecimal("amount"));
				lfp.setHR_Concept_Category_ID(HardCodedVal.hr_concept_category_idDeduction);
				lfp.setHR_Loan_ID(get_ID());
				lfp.setSeqNo(seqNo);
				lfp.setHR_Movement_ID(-1);
				lfp.save(trx.getTrxName());
				
				seqNo+=10;
			}
		
		}catch(Exception ex) { throw new AdempiereException(ex.getMessage());}
	}
}
