package spc.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRNoPayAttribute extends X_HR_NoPayAttribute{

	public MHRNoPayAttribute(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNoPayAttribute(Properties ctx, int HR_NoPayAttribute_ID, String trxName) {
		super(ctx, HR_NoPayAttribute_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	protected boolean beforeSave(boolean newRecord){
		
		
		
		
		return true;
	}
	
	

}
