package spc.payroll.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.eevolution.model.MHRProcess;

import spc.payroll.model.MHROT;
import spc.payroll.model.MHROTLine;
import spc.payroll.model.X_I_HRImportOT;

//spc.payroll.process.ImportHROT
public class ImportHROT extends SvrProcess{

	private boolean	p_deleteOldImported = false;
	private String sql = "";
	private boolean	DeleteOld = false;
	private int HR_OT_ID = 0;
	
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			
			if (name.equals("DeleteOldImported")){
				p_deleteOldImported = "Y".equals(para[i].getParameter());
			}else if(name.equals("DeleteOld")){
				DeleteOld =  "Y".equals(para[i].getParameter());
			}else if(name.equals("HR_OT_ID")) {
				HR_OT_ID = ((BigDecimal)para[i].getParameter()).intValue();
			}
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		//Delete Old Imported only
		if (p_deleteOldImported) 
			DB.executeUpdate("DELETE I_HRImportOT WHERE I_IsImported='Y'", get_TrxName());
		
		//Delete all records
		if (DeleteOld) 
			DB.executeUpdate("DELETE I_HRImportOT", get_TrxName());
		
		//update the business partner
		sql = "MERGE INTO I_HRImportOT it " + 
			"USING C_BPARTNER bp ON (it.bpvalue = bp.value AND bp.isemployee = 'Y') " + 
			"WHEN MATCHED THEN UPDATE SET it.C_BPARTNER_ID = bp.C_BPARTNER_ID ";
		DB.executeUpdate(sql, get_TrxName());
		
		sql = "SELECT * FROM I_HRImportOT WHERE I_IsImported='N'";
		
		PreparedStatement st = null;ResultSet rs = null;
		int i = 0;
		MHROT ot = new MHROT(getCtx(), HR_OT_ID, get_TrxName());
		try {
			st = DB.prepareStatement(sql, get_TrxName());
			rs = st.executeQuery();
			X_I_HRImportOT x = null;
			MHROTLine otl = null;
			
			while(rs.next()) {
				x = new X_I_HRImportOT(getCtx(), rs, get_TrxName());
				
				//no business partner
				if(x.getC_BPartner_ID() == 0) {
					//can not find the employee for given epf
					x.setI_ErrorMsg("Could not find the employee for given epf!");
					x.save();
					
					continue;
				}
				//create OT lines
				otl = new MHROTLine(getCtx(), 0, get_TrxName());
				otl.setC_BPartner_ID(x.getC_BPartner_ID());
				otl.setOTHours(x.getOTHours());
				otl.setOTHoursDouble(x.getOTHoursDouble());
				otl.setHR_OT_ID(HR_OT_ID);
				otl.setMealAllowance(x.getMealAllowance());
				if(otl.save()) {
					x.setI_IsImported(true);
					x.setProcessed(true);
					x.save();
					i ++;
				}
				
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(rs, st);
			rs = null;st = null;
		}
		
		
		return "Uploaded : " + i;
	}

}
