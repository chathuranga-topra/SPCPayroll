package spc.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

@SuppressWarnings("serial")
public class MHRLoanType extends X_HR_LoanType{

	public MHRLoanType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MHRLoanType(Properties ctx, int C_HRLoanType_ID, String trxName) {
		super(ctx, C_HRLoanType_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	protected boolean beforeSave(boolean newRecord){ 
	
		return true;
	}

}
