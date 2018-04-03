package spc.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

@SuppressWarnings("serial")
public class MHRLoanEarllySettle extends X_HR_LoanEarllySettle{

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
		
		return true;
	}

}
