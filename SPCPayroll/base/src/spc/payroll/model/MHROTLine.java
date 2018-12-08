package spc.payroll.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.eevolution.model.MHRAttribute;
import org.eevolution.model.MHRConcept;
import org.eevolution.model.MHREmployee;
import org.eevolution.process.HRCreateConcept;

public class MHROTLine extends X_HR_OTLine{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MHROTLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MHROTLine(Properties ctx, int HR_OTLine_ID, String trxName) {
		super(ctx, HR_OTLine_ID, trxName);
	}
	
	protected boolean beforeSave(boolean newRecord){
		
		//set HR Employee ID
		MHREmployee emp = MHREmployee.getActiveEmployee(p_ctx, getC_BPartner_ID(), get_TrxName());
		MHROT ot = new MHROT(getCtx(), getHR_OT_ID(), get_TrxName());
		MHROtCategory otcSingle , otcDouble = null;
		
		//validate Single OT categories from employee master
		otcSingle = new MHROtCategory(getCtx() , emp.getHR_OtCategory_ID() , get_TrxName());
		if(otcSingle == null || otcSingle.get_ID() == 0){
			throw new AdempiereException("No OT category is selected in employee master!");
		}
		
		//validate Double OT categories from employee master
		otcDouble = new MHROtCategory(getCtx() , emp.getHR_OtDoubleCategory_ID() , get_TrxName());
		if(otcDouble == null || otcDouble.get_ID() == 0){
			throw new AdempiereException("No double OT category is selected in employee master!");
		}
	
		
		//set OT rate single
		int basedConcept_ID = otcSingle.get_ValueAsInt("BasedConcept_ID");
		String stringRate = otcSingle.getRate().replace('?', ' ');
		//get basic salary based on the payroll movements
		String sql = "select NVL(amount "+stringRate+" , 0)  from HR_Movement where hr_concept_id = ? "
			+ " and c_bpartner_id = ? AND HR_Process_ID = ?";
		
		BigDecimal rateAmt = DB.getSQLValueBD(get_TrxName(), sql,basedConcept_ID , getC_BPartner_ID() , ot.getLastPayroll_ID());
		
		if(rateAmt == null) {
			//no rate means that new employee >> No last month sallary
			//based on the current basic salary rate amt will be calculated
			sql = "SELECT NVL(amount "+stringRate+" , 0) FROM HR_ATTRIBUTE WHERE "
				+ "HR_ATTRIBUTE.HR_CONCEPT_ID = ? "
				+ "AND HR_ATTRIBUTE.C_BPARTNER_ID = ?";
			
			rateAmt = DB.getSQLValueBD(get_TrxName(), sql,basedConcept_ID , getC_BPartner_ID());
			
			if(rateAmt == null)
				throw new AdempiereException("System can not calculate OT Hourly rate!");
		}
		
		setRate(rateAmt.setScale(2, RoundingMode.HALF_UP));
		setTotalOTAmt((getOTHours().multiply(rateAmt)).setScale(2, RoundingMode.HALF_UP));
		
		//set OT Double
		if(otcDouble !=null) {//only there are selected double OT
			basedConcept_ID = otcDouble.get_ValueAsInt("BasedConcept_ID");
			stringRate = otcDouble.getRate().replace('?', ' ');
			//get basic salary based on the payroll movements
			sql = "select NVL(amount "+stringRate+" , 0)  from HR_Movement where hr_concept_id = ? "
				+ " and c_bpartner_id = ? AND HR_Process_ID = ?";
			
			rateAmt = DB.getSQLValueBD(get_TrxName(), sql,basedConcept_ID , getC_BPartner_ID() , ot.getLastPayroll_ID());
			
			if(rateAmt == null) {
				if(rateAmt == null) {
				//no rate means that new employee >> No last month sallary
				//based on the current basic salary rate amt will be calculated
					sql = "SELECT NVL(amount "+stringRate+" , 0) FROM HR_ATTRIBUTE WHERE "
					+ "HR_ATTRIBUTE.HR_CONCEPT_ID = ? "
					+ "AND HR_ATTRIBUTE.C_BPARTNER_ID = ?";
				
					rateAmt = DB.getSQLValueBD(get_TrxName(), sql,basedConcept_ID , getC_BPartner_ID());
				
					if(rateAmt == null)
						throw new AdempiereException("System can not calculate OT Hourly rate!");
				}
			}
			
			setRateDouble(rateAmt.setScale(2, RoundingMode.HALF_UP));
			setTotalOTAmtDouble((getOTHoursDouble().multiply(rateAmt)).setScale(2, RoundingMode.HALF_UP));
		}
		
		
		
		//meal allowance
		setIsMeal(getMealAllowance().doubleValue() > 0);
		//set total line
		setLineTotalAmt(getTotalOTAmt().add(getTotalOTAmtDouble()).add(getMealAllowance().setScale(2, RoundingMode.HALF_UP)));
		
		//set and validate OT attribute Single
		if(otcSingle != null) {
			sql = " C_Bpartner_ID = "+getC_BPartner_ID() +" AND HR_Concept_ID = " + otcSingle.getHR_Concept_ID();
			int i [] = MHRAttribute.getAllIDs(MHRAttribute.Table_Name, sql, get_TrxName());
			 
			MHRAttribute oTAtr = null;
			if(i.length == 0) { //No attribute create new Attribute
				oTAtr = new MHRAttribute(getCtx(), 0, get_TrxName());
				oTAtr.setC_BPartner_ID(getC_BPartner_ID());
				oTAtr.setHR_Concept_ID(ot.getHR_OtCategory().getHR_Concept_ID());
				oTAtr.setHR_Employee_ID(emp.get_ID());
				
				Date dt = new Date();
				dt.setDate(1);
				oTAtr.setValidFrom(new Timestamp(dt.getTime()));
				oTAtr.setColumnType("A");
				oTAtr.save();
			}else {
				oTAtr = new MHRAttribute(getCtx(), i[i.length -1], get_TrxName());
			}
			setOTAtrribute_ID(oTAtr.get_ID());
		}
		//set and validate OT attribute Double
		if(otcDouble != null) {
			sql = " C_Bpartner_ID = "+getC_BPartner_ID() +" AND HR_Concept_ID = " + otcDouble.getHR_Concept_ID();
			int i [] = MHRAttribute.getAllIDs(MHRAttribute.Table_Name, sql, get_TrxName());
			 
			MHRAttribute oTAtrD = null;
			if(i.length == 0) { //No attribute create new Attribute
				oTAtrD = new MHRAttribute(getCtx(), 0, get_TrxName());
				oTAtrD.setC_BPartner_ID(getC_BPartner_ID());
				oTAtrD.setHR_Concept_ID(ot.getHR_OtCategory().getHR_Concept_ID());
				oTAtrD.setHR_Employee_ID(emp.get_ID());

				Date dt = new Date();
				dt.setDate(1);
				oTAtrD.setValidFrom(new Timestamp(dt.getTime()));
				oTAtrD.setColumnType("A");
				oTAtrD.save();
			}else {
				oTAtrD = new MHRAttribute(getCtx(), i[i.length -1], get_TrxName());
			}
			setDoubleOTAttribute_ID(oTAtrD.get_ID());
		}
		
		
		//set and validate meal attribute
		if(getMealAtrribute_ID() == 0) {
			
			sql = " C_Bpartner_ID = "+getC_BPartner_ID() +" AND HR_Concept_ID = " + ot.getMealConcept_ID();
			int i [] = MHRAttribute.getAllIDs(MHRAttribute.Table_Name, sql, get_TrxName());
			MHRAttribute mealAtr = null;
			if(i.length == 0) { //No attribute create new Attribute
				mealAtr = new MHRAttribute(getCtx(), 0, get_TrxName());
				mealAtr.setC_BPartner_ID(getC_BPartner_ID());
				mealAtr.setHR_Concept_ID(ot.getMealConcept_ID());
				mealAtr.setHR_Employee_ID(emp.get_ID());
				
				Date dt = new Date();
				dt.setDate(1);
				mealAtr.setValidFrom(new Timestamp(dt.getTime()));
				mealAtr.setColumnType("A");
				mealAtr.save();
			}else {
				mealAtr = new MHRAttribute(getCtx(), i[i.length -1], get_TrxName());
			}
			setMealAtrribute_ID(mealAtr.get_ID());
		}
		
		
		return true;
	}
	
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		
		
		return success;
	}	//	afterSave

	public static MHROTLine [] getLines(MHROT ot) {
		
		List<MHROTLine> list = new Query(ot.getCtx(), MHROTLine.Table_Name, COLUMNNAME_HR_OT_ID+"=?", ot.get_TrxName())
		.setParameters(ot.get_ID())
		.list();
		return list.toArray(new MHROTLine[list.size()]);
	}

}
