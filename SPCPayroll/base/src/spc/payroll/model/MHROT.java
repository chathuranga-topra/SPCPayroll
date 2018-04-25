package spc.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHROT extends X_HR_OT{

	public MHROT(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MHROT(Properties ctx, int HR_OT_ID, String trxName) {
		super(ctx, HR_OT_ID, trxName);
	}
	
	protected boolean beforeSave(boolean newRecord){
		
		System.out.println("Hello");
		
		return true;
	}

}
