package spc.payroll.model;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;

@SuppressWarnings("serial")
public class MHRLoanEarllySettle extends X_HR_LoanEarllySettle implements DocAction , DocOptions{

	public MHRLoanEarllySettle(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRLoanEarllySettle(Properties ctx, int HR_LoanEarllySettle_ID,
			String trxName) {
		super(ctx, HR_LoanEarllySettle_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	protected boolean beforeSave(boolean newRecord){
		
		//validate duplicate loans opening
		String whereClause = "DOCSTATUS NOT IN('VO' , 'CO') AND HR_LOAN_ID = "+getHR_Loan_ID()+" AND HR_LoanEarllySettle_ID NOT IN ("+get_ID()+")";
		int length = MHRLoanEarllySettle.getAllIDs("HR_LoanEarllySettle", whereClause , get_TrxName()).length;
		
		if(length >= 1)
			throw new AdempiereException("DUPLICATE DOCUMENT OPENED! Your are not allowed to open duplicate");
		
		if(!getDocStatus().equals("DR"))
			return true;
		
		//set loan balance
		MHRLoan loan = new MHRLoan(getCtx() , this.getHR_Loan_ID() , get_TrxName());
		BigDecimal balance = MHRLoan.getBalance(loan);
		BigDecimal ir = loan.getHR_LoanType().getRate();
		int paybleInstallmentCount = MHRLoan.getPayableInstallmentCount(loan);
		BigDecimal interest = new BigDecimal(0);
		//BigDecimal newInterest = new BigDecimal(0);
		
		
		//get total interest for no paid schedule
		String sql = "SELECT SUM(interestamt) FROM HR_LoanSchedule WHERE HR_Loan_ID = ? and ispaid = 'N' AND IsActive = 'Y'";
		
		BigDecimal oldInterest = DB.getSQLValueBD(this.get_TrxName(), sql, this.getHR_Loan_ID());
		this.setOldInterestTotal(oldInterest);
		
		//set paid count
		setPaidInstallmentCount(MHRLoan.getPaidInstallmentCount(loan));
		
		
		
		//calculate new interest based on the settlment type
		if(this.getType().equals("TT")){//2 of 3 payment
			
			//get 2 of third 3 rest payment schedule
			sql = "SELECT MIN(SEQNO) FROM HR_LoanSchedule WHERE HR_Loan_ID = ? AND ISPAID = 'N'";
			int from = DB.getSQLValue(get_TrxName(), sql, getHR_Loan_ID());
			sql = "SELECT COUNT(*) * 2 /3  FROM HR_LoanSchedule WHERE HR_Loan_ID = ?";
			int to = DB.getSQLValue(get_TrxName(), sql, getHR_Loan_ID());
			
			if(from > to){//already paid 2 of 3
				throw new AdempiereException("ERROR! Already paid 2 of 3 from this loan");
			}
			
			paybleInstallmentCount = to - from + 1;
			
		}else if(this.getType().equals("FS")){//full payment
			
			interest = balance.multiply(ir).divide(new BigDecimal(100) , 2 , RoundingMode.HALF_UP);
			interest = interest.divide(new BigDecimal(12) , 2 , RoundingMode.HALF_UP);
			interest = interest.multiply(new BigDecimal(paybleInstallmentCount + 1));
			interest = interest.divide(new BigDecimal(paybleInstallmentCount) , 2 , RoundingMode.HALF_UP);
			interest = interest.multiply(new BigDecimal(0.5));
			
			setNewInterestTotal(interest.multiply(new BigDecimal(paybleInstallmentCount)).setScale(2, RoundingMode.HALF_UP));
			
		}
		
		
		this.setBalance(balance);
		setPayableInstallmentCount(paybleInstallmentCount);
		
		return true;
	}

	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {
		
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

	@Override
	public boolean processIt(String action) throws Exception {
		
		if(action.equals("--") && getDocAction().equalsIgnoreCase("PR"))
			this.prepareIt();
		else if(action.equals("--") && getDocAction().equalsIgnoreCase("CO"))
			this.completeIt();
		else if(action.equals("PR") && getDocAction().equalsIgnoreCase("CL"))
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

	@Override
	public String prepareIt() {
		//delete existing records
		String sql = "DELETE FROM HR_LoanEarllySettlelLine WHERE HR_LoanEarllySettle_ID = ? ";
		DB.executeUpdate(sql, this.get_ID(), this.get_TrxName());
		MHRLoanSchedule[] lns = null;
		
		if(getType().equals("FS")){//full settlement
			lns = MHRLoan.getLoanUpPaidSchedule(getCtx(), this.getHR_Loan_ID(), get_TrxName());
		}else if(getType().equals("TT")){//2 of 3 third settlement
		
			//get 2 of third 3 rest payment schedule
			sql = "SELECT MIN(SEQNO) FROM HR_LoanSchedule WHERE HR_Loan_ID = ? AND ISPAID = 'N'";
			int from = DB.getSQLValue(get_TrxName(), sql, getHR_Loan_ID());
			sql = "SELECT COUNT(*) * 2 /3  FROM HR_LoanSchedule WHERE HR_Loan_ID = ?";
			int to = DB.getSQLValue(get_TrxName(), sql, getHR_Loan_ID());
			
			if(from >=to){//already paid 2 of 3
				throw new AdempiereException("ERROR! Already paid 2 of 3 from this loan");
			}
		}
		
		MHRLoanEarllySettlelLine sLine;
		for(MHRLoanSchedule sc : lns){
			
			sLine = new MHRLoanEarllySettlelLine(getCtx(), 0, get_TrxName());
			sLine.setHR_LoanEarllySettle_ID(this.get_ID());
			sLine.setSeqNo(sc.getSeqNo());
			sLine.setEffectiveFrom(sc.getEffectiveFrom());
			sLine.setCapitalAmt(sc.getCapitalAmt());
			sLine.setOriginalInterest(sc.getInterestAmt());
			sLine.setRevisedInterest(getNewInterestTotal().divide(new BigDecimal(getPayableInstallmentCount())).setScale(2, RoundingMode.HALF_UP));
			
			sLine.setHR_LoanSchedule_ID(sc.get_ID());
			sLine.save();
		}
		
		this.setDocStatus("IP");
		this.setDocAction("CO");
		this.save();
		return null;
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
		//copy revised interest plan to loan schedule
		MHRLoanEarllySettlelLine[] lines = this.getLines(this);
		MHRLoanSchedule sch = null;
		
		for(MHRLoanEarllySettlelLine l: lines){
			
			sch = new MHRLoanSchedule(getCtx(), l.getHR_LoanSchedule_ID(), get_TrxName());
			sch.setInterestAmt(l.getRevisedInterest());
			sch.setHR_LoanEarllySettle_ID(get_ID());
			sch.setIsPaid(true);
			sch.setProcessed(true);
			sch.save();
			l.setProcessed(true);
			l.save();
		}
		
		if(getType().equals("FS")){//closed the loan if full paid
			MHRLoan loan = new MHRLoan(getCtx(),sch.getHR_Loan_ID(),get_TrxName());
			loan.closeIt();
			setProcessed(true);
		}
		
		setDocStatus("CO");
		setDocAction("CL");
		
		return "Done!";
	}

	@Override
	public boolean voidIt() {
		
		String sql = "";
		
		if(getDocStatus().equals("CO")){
			//remove updated data from loan schedule
			MHRLoanEarllySettlelLine [] lines = getLines(this);
			
			for(MHRLoanEarllySettlelLine line : lines) {
				line.getHR_LoanSchedule_ID();
			}
			
			//UNCLOSED LOAN
			MHRLoan loan = (MHRLoan) this.getHR_Loan();
			loan.setDocAction("CL");
			loan.setDocStatus("CO");
			loan.save();
		}
		
		sql = "UPDATE HR_LoanEarllySettlelLine SET PROCESSED = 'Y' WHERE HR_LoanEarllySettle_ID = ?";
		DB.executeUpdate(sql, get_ID(), get_TrxName());
		
		setDocStatus("VO");
		setDocAction("--");
		setProcessed(true);
		
		return false;
	}

	@Override
	public boolean closeIt() {
		// TODO Auto-generated method stub
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
	
	public MHRLoanEarllySettlelLine[] getLines(MHRLoanEarllySettle les){
		
		List<MHRLoanEarllySettlelLine> list = new Query(les.getCtx(), MHRLoanEarllySettlelLine.Table_Name, "HR_LoanEarllySettle_ID = ? ", les.get_TrxName())
		.setParameters(les.get_ID())
		.list();
		return list.toArray(new MHRLoanEarllySettlelLine[list.size()]);
	}
}
