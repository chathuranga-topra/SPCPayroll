package spc.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRLoanTypeFactors extends X_HR_LoanTypeFactors{

	public MHRLoanTypeFactors(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRLoanTypeFactors(Properties ctx, int HR_LoanTypeFactors_ID,
			String trxName) {
		super(ctx, HR_LoanTypeFactors_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	protected boolean beforeSave(boolean newRecord){
		
		//set loan type
		setHR_Concept_Category_ID(getHR_Concept().getHR_Concept_Category_ID());
		
		return true;
	}
}
