package spc.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHROtCategory extends X_HR_OtCategory{

	public MHROtCategory(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHROtCategory(Properties ctx, int HR_OtCategory_ID, String trxName) {
		super(ctx, HR_OtCategory_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	protected boolean beforeSave(boolean newRecord){
		
		
		return true;
	}
	
	

}
