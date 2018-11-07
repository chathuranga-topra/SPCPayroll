package spc.payroll.model;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
		String sql = "DOCSTATUS NOT IN('VO' , 'CO') AND HR_LOAN_ID = "+getHR_Loan_ID()+" AND HR_LoanEarllySettle_ID NOT IN ("+get_ID()+")";

		int length = MHRLoanEarllySettle.getAllIDs("HR_LoanEarllySettle", sql , get_TrxName()).length;
		
		if(length >= 1)
			throw new AdempiereException("DUPLICATE DOCUMENT OPENED! Your are not allowed to open duplicate");
		
		if(!getDocStatus().equals("DR"))
			return true;
		
		
		MHRLoan loan = (MHRLoan) getHR_Loan();
		
		BigDecimal ir = loan.getHR_LoanType().getRate();
		BigDecimal balance , OldInterestTotal = null;
		
		int paybleInstallmentCount = 0; // next payment installment sequence no
		
		int lastPaymentSeqNo = DB.getSQLValue(get_TrxName(), ""
			+ " SELECT MAX(SEQNO) FROM HR_LoanSchedule WHERE ISPAID = 'Y' "
			+ " AND HR_Loan_ID = " + loan.get_ID());
		
		if(this.getType().equals("TT")) {//Two of tree settlement
			
			//Next installment sequence no
			paybleInstallmentCount = DB.getSQLValue(get_TrxName(), ""
				+ " SELECT COUNT(*) * 2 /3 FROM HR_LoanSchedule WHERE "
				+ " HR_Loan_ID = " + loan.get_ID());
			
		}else if(this.getType().equals("FS")) {//full settlement
			//Next installment sequence no
			paybleInstallmentCount = DB.getSQLValue(get_TrxName(), ""
				+ " SELECT MAX(SEQNO) FROM HR_LoanSchedule WHERE "
				+ " HR_Loan_ID = " + loan.get_ID());
		}
		setPaidInstallmentCount(lastPaymentSeqNo);
		setPayableInstallmentCount(paybleInstallmentCount);
		
		//Validation for half of total installment
		if(getHR_Loan().getInstallmentCount() /2 > lastPaymentSeqNo) {
			throw new AdempiereException("Early settlement eligable only for more than half settled loans only");
		}
		
		//set balance 
		sql = "SELECT SUM(CapitalAmt) FROM HR_LoanSchedule WHERE seqno BETWEEN ? "
				+ "AND ? AND Ispaid = 'N' AND HR_Loan_ID=? ";
		balance = DB.getSQLValueBD(get_TrxName(), sql, 
			lastPaymentSeqNo , 
			paybleInstallmentCount,
			getHR_Loan_ID()
		);
		
		//set old interest
		sql = "SELECT SUM(InterestAmt) FROM HR_LoanSchedule WHERE IsPaid = 'N' AND HR_Loan_ID=? ";
		OldInterestTotal = DB.getSQLValueBD(get_TrxName(), sql,getHR_Loan_ID());
		
		//calculate new interest total
		sql = "SELECT SUM(InterestAmt) FROM HR_LoanSchedule WHERE  Ispaid = 'N' AND HR_Loan_ID=? ";
		BigDecimal oldTotalRestInterest =  DB.getSQLValueBD(get_TrxName(), sql, getHR_Loan_ID());
		
		//new interest under new formuller
		int toBePaidTotalInstallment = loan.getInstallmentCount() - MHRLoan.getPaidInstallmentCount(loan);
		BigDecimal newInterestBeforeAdgustment = MHRLoan.getBalance(loan)
				.multiply(new BigDecimal(toBePaidTotalInstallment + 1)
				.multiply(ir))
				.divide(new BigDecimal(100) , 2 , RoundingMode.HALF_UP)
				.divide(new BigDecimal(12) , 2 , RoundingMode.HALF_UP)
				.divide(new BigDecimal(2) , 2 , RoundingMode.HALF_UP);
		
		newInterestBeforeAdgustment = newInterestBeforeAdgustment.setScale(2, RoundingMode.HALF_UP);
		BigDecimal newInterestBeforeNoOfDaysInterest = oldTotalRestInterest.subtract(newInterestBeforeAdgustment);
		
		//calculate late days for interest
		Calendar cal = Calendar.getInstance();
		cal.setTime(getSettleDate());
		cal.set(5, 25);//default date is 25th of every month
		
		Date defaultDate = cal.getTime();
		int dayDiff = (int) TimeUnit.DAYS.convert(getSettleDate().getTime() - defaultDate.getTime(), TimeUnit.MILLISECONDS);
		
		if(dayDiff<0) {//minus date differences
			dayDiff = 0;
			//cal.setTime(defaultDate);
			//cal.add(Calendar.MONTH, -1);
			//defaultDate = cal.getTime();
			//dayDiff = (int) TimeUnit.DAYS.convert(getSettleDate().getTime() - defaultDate.getTime(), TimeUnit.MILLISECONDS);
		}
		//interest for dates
		BigDecimal lateDaysInterest = MHRLoan.getBalance(loan)
			.multiply(new BigDecimal(dayDiff))
			.divide(new BigDecimal(365) , 2 , RoundingMode.HALF_UP)
			.multiply(new BigDecimal(7))
			.divide(new BigDecimal(100) , 2 , RoundingMode.HALF_UP);
		
		setBalance(balance);
		setOldInterestTotal(OldInterestTotal);
		setNewInterestTotal(newInterestBeforeNoOfDaysInterest.add(lateDaysInterest));
		setLateDaysInterest(lateDaysInterest);
		
		return true;
	}

	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {
		
		/*if (docStatus.equals(DocumentEngine.STATUS_Drafted)
    			|| docStatus.equals(DocumentEngine.STATUS_Invalid)) {
    		options[index++] = DocumentEngine.ACTION_Complete;
    	}*/
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
		MHRLoanSchedule[] lns = MHRLoan.getLoanSchedule(getCtx(), 
			getHR_Loan_ID(),
			" SEQNO BETWEEN '" +getPaidInstallmentCount() + "' "
			+ " AND '"+ getPayableInstallmentCount() + "'"
			+ " AND ISPAID = 'N'",
			get_TrxName());
		
		MHRLoanEarllySettlelLine sLine = null;
		for(MHRLoanSchedule sc : lns){
			
			if(sLine == null) {//get identified the first installment then add the days interest calculation
				
			}
			
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
		
		if(getType().equals("TT")) {
		
			//VALIDATE FOR NEW LOAN IS COMPLETED
			MHRLoan	loan = getNewRenewalLoan();
			if(loan == null)
				throw new AdempiereException("Please complete new loan for settlement");
			//set loan information
			setNewHR_Loan_ID(loan.getHR_Loan_ID());
			setNewLoanAmount(loan.getLoanAmount());
			
			//set Deduction 2 of 3 from old loan
			BigDecimal oneOfthree = getHR_Loan().getLoanAmount().divide(new BigDecimal(3) , 2 , RoundingMode.HALF_UP)
			.multiply(new BigDecimal(1));
			
			setDeduction(oneOfthree);
			setBalanceToPaid(loan.getLoanAmount().subtract(oneOfthree ));
			
			//validate payment reference no
			if(getPaymentDocumentNo()==null || getPaymentDocumentNo().length() ==0) {
				throw new AdempiereException("Payment document no is empty!");
			}
		}
		
		//set paid all installment in base loan
		String sql = "UPDATE HR_LoanSchedule set IsPaid = 'Y' , "
			+ " HR_LoanEarllySettle_ID =  "+get_ID()+" WHERE IsPaid = 'N' AND HR_Loan_ID = ?";
		DB.executeUpdate(sql, getHR_Loan_ID(), get_TrxName());
		
		sql = "UPDATE HR_Loan set DocStatus = 'CL' , DocAction = '--' WHERE HR_Loan_ID = ?";
		DB.executeUpdate(sql, getHR_Loan_ID(), get_TrxName());
		
		setDocStatus("CO");
		setDocAction("CL");
		setProcessed(true);
		
		return "Done!";
	}

	@Override
	public boolean voidIt() {
		
		/*String sql = "";
		
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
		DB.executeUpdate(sql, get_ID(), get_TrxName());*/
		
		setDocStatus("VO");
		setDocAction("--");
		setProcessed(true);
		
		return false;
	}
	
	private MHRLoan getNewRenewalLoan() {
		
		MHRLoan loan = new Query(getCtx(), MHRLoan.Table_Name, "IsRenewalLoan = 'Y' AND HR_LoanEarllySettle_ID = ? AND DocStatus = 'CO'" , get_TrxName())
			.setParameters(get_ID())
			.first();
		
		return loan;
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
