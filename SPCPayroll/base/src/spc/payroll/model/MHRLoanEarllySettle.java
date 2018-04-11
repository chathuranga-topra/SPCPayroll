package spc.payroll.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

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
		
		//set loan balance
		MHRLoan loan = new MHRLoan(getCtx() , this.getHR_Loan_ID() , get_TrxName());
		this.setBalance(MHRLoan.getBalance(loan));
		
		// need to check loan amount paid more than 3/2 installment
		
		
		//get total interest for no paid schedule
		String sql = "SELECT SUM(interestamt) FROM HR_LoanSchedule WHERE HR_Loan_ID = ? and ispaid = 'N' AND IsActive = 'Y'";
		
		BigDecimal oldInterest = DB.getSQLValueBD(this.get_TrxName(), sql, this.getHR_Loan_ID());
		this.setOldInterestTotal(oldInterest);
		//calculate remaining days and new total interest
		sql = " SELECT MAX(EFFECTIVEFROM) - MIN(EFFECTIVEFROM) as RemainDays "
		+ " FROM HR_LoanSchedule WHERE ISPAID = 'N' AND HR_Loan_ID = ? ";
		
		int days =  DB.getSQLValue(get_TrxName(), sql,this.getHR_Loan_ID());
		this.setRemainingDays(days);
		
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
		
		System.out.println("action : " + action);
		System.out.println("getDocAction() : " + getDocAction());
		
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
		
		//coping loan schedule
		MHRLoanSchedule[] lns = MHRLoan.getLoanUpPaidSchedule(getCtx(), this.getHR_Loan_ID(), get_TrxName());
		
		MHRLoanEarllySettlelLine sLine;
		for(MHRLoanSchedule sc : lns){
			
			sLine = new MHRLoanEarllySettlelLine(getCtx(), 0, get_TrxName());
			sLine.setHR_LoanEarllySettle_ID(this.get_ID());
			sLine.setSeqNo(sc.getSeqNo());
			sLine.setEffectiveFrom(sc.getEffectiveFrom());
			sLine.setCapitalAmt(sc.getCapitalAmt());
			sLine.setOriginalInterest(sc.getInterestAmt());
			sLine.setRevisedInterest(new BigDecimal(0));
			sLine.setRemainingDays(10);
			
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean voidIt() {
		// TODO Auto-generated method stub
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

}
