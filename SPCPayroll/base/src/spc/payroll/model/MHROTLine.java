package spc.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.eevolution.model.MHREmployee;

import net.sf.jasperreports.engine.export.GenericElementJExcelApiMetadataHandler;

public class MHROTLine extends X_HR_OTLine{

	public MHROTLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MHROTLine(Properties ctx, int HR_OTLine_ID, String trxName) {
		super(ctx, HR_OTLine_ID, trxName);
	}
	
	protected boolean beforeSave(boolean newRecord){
		
		//set HR Employee ID
		MHREmployee emp = MHREmployee.getActiveEmployee(p_ctx, getC_BPartner_ID(), get_TrxName());
		
		
		if(emp.getHR_OtCategory_ID() == 0) 
			throw new AdempiereException("No OT catogory selected!");
		
		//validate OT category with employee master
		
		
		
		
		return true;
	}

}
