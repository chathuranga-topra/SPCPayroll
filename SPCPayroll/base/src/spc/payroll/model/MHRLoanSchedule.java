package spc.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRLoanSchedule extends X_HR_LoanSchedule{

	public MHRLoanSchedule(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRLoanSchedule(Properties ctx, int HR_LoanSchedule_ID,
			String trxName) {
		super(ctx, HR_LoanSchedule_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	protected boolean beforeSave (boolean newRecord)
	{
		return true;
	}
	

}
