package spc.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

@SuppressWarnings("serial")
public class MHRLoanEarllySettlelLine extends X_HR_LoanEarllySettlelLine{

	public MHRLoanEarllySettlelLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MHRLoanEarllySettlelLine(Properties ctx,
			int HR_LoanEarllySettlelLine_ID, String trxName) {
		super(ctx, HR_LoanEarllySettlelLine_ID, trxName);
	}
	
	protected boolean beforeSave(boolean newRecord){
		
		return true;
	}

}
