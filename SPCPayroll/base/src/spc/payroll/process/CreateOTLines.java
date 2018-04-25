package spc.payroll.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import spc.payroll.model.MHROT;
import spc.payroll.model.MHROTLine;

public class CreateOTLines extends SvrProcess{

	int HR_Job_ID , C_BPartner_ID , HR_Department_ID; boolean DeleteOld;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			
			if(name.equals("HR_Job_ID")){
				HR_Job_ID = para[i].getParameter() == null?0:((BigDecimal)para[i].getParameter()).intValue();
			}else if (name.equals("C_BPartner_ID")){
				C_BPartner_ID = para[i].getParameter() == null?0:((BigDecimal)para[i].getParameter()).intValue();
			}else if(name.equals("HR_Department_ID")){
				HR_Department_ID = para[i].getParameter() == null?0:((BigDecimal)para[i].getParameter()).intValue();
			}else if(name.equals("DeleteOld")){
				DeleteOld = "Y".equals(para[i].getParameter());
			}
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		String sql = "";
		MHROT ot = new MHROT(getCtx(), getRecord_ID(), get_TrxName());
		
		if(DeleteOld) {
			sql = "delete from HR_OTLine where HR_OT_ID = ? ";
			DB.executeUpdate(sql, getRecord_ID(), get_TrxName());			
		}
		
		sql = "select emp.c_bpartner_id , max(atr.hr_attribute_id) as hr_attribute_id " + 
		"from  HR_Employee emp " + 
		"inner join c_bpartner bp on bp.c_bpartner_id = emp.c_bpartner_id " + 
		"left join hr_attribute atr on atr.c_bpartner_id = emp.c_bpartner_id " + 
		"left join HR_Department dept on dept.HR_Department_ID = emp.HR_Department_ID " + 
		"left join HR_Job j on j.HR_Job_ID = emp.HR_Job_ID " +
		"where emp.enddate is null " + 
		"and emp.hr_otcategory_id = ?" +
		"and atr.hr_concept_id = ? " +
		"and bp.isactive = 'Y' " + 
		"and emp.c_bpartner_id not in(select c_bpartner_id from HR_OTLine  where hr_ot_id = ?) ";
		
		if(HR_Department_ID!=0)
			sql +=" and dept.HR_Department_ID = " + HR_Department_ID;
		
		if(HR_Department_ID!=0)
			sql +=" and j.HR_Job_ID = " + HR_Job_ID;
		
		if(C_BPartner_ID!=0)
			sql +=" and emp.C_BPartner_ID = " + C_BPartner_ID;
		
		sql += " group by emp.c_bpartner_id  ,bp.value " + 
			"order by bp.value";
		
		PreparedStatement psmt = null; ResultSet rs = null;
		
		try {
			
			psmt = DB.prepareStatement(sql, get_TrxName());
			psmt.setInt(1, ot.getHR_OtCategory_ID());
			psmt.setInt(2, ot.getHR_OtCategory().getHR_Concept_ID());
			psmt.setInt(3, getRecord_ID());
			rs = psmt.executeQuery();
			
			MHROTLine ol = null;
			while(rs.next()) {
				
				 ol = new MHROTLine(getCtx(), 0, get_TrxName());
				 ol.setC_BPartner_ID(rs.getInt("c_bpartner_id"));
				 ol.setHR_OT_ID(getRecord_ID());
				 ol.setOTAtrribute_ID(rs.getInt("hr_attribute_id"));
				 
				 ol.save();
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
			
		return "Done!";
	}

}
