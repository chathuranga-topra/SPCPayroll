package spc.payroll.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
		MHROtCategory otc = (MHROtCategory) ot.getHR_OtCategory(); 
		
		if(emp.getHR_OtCategory_ID() == 0) { 
			throw new AdempiereException("No OT catogory selected!");
		}
		
		//validate OT category with employee master
		if(ot.getHR_OtCategory_ID() != emp.getHR_OtCategory_ID()) {
			throw new AdempiereException("Mismatch OT category! Please check with Employee master");
		}
		
		//set OT rate
		int basedConcept_ID = otc.get_ValueAsInt("BasedConcept_ID");
		String stringRate = otc.getRate().replace('?', ' ');
		//get basic sallary
		String sql = "select NVL(amount "+stringRate+" , 0)  from HR_Attribute where hr_concept_id = ? "
			+ " and c_bpartner_id = ?";
		
		BigDecimal rateAmt = DB.getSQLValueBD(get_TrxName(), sql,basedConcept_ID , getC_BPartner_ID());
		
		if(rateAmt == null)
			rateAmt = new BigDecimal(0);
		
		setRate(rateAmt);
		setTotalOTAmt((getOTHours().multiply(rateAmt)).setScale(2, RoundingMode.HALF_UP));
		
		//meal allowance
		setIsMeal(getMealAllowance().doubleValue() > 0);
		//set total line
		setLineTotalAmt(getTotalOTAmt().add(getMealAllowance().setScale(2, RoundingMode.HALF_UP)));
		
		//validate meal attribute
		String sqlWhere = " C_Bpartner_ID = "+getC_BPartner_ID() +" AND HR_Concept_ID = " + ot.getMealConcept_ID(); 
		
		int i [] = MHRAttribute.getAllIDs(MHRAttribute.Table_Name, sqlWhere, get_TrxName());
		MHRAttribute mealAtr = null;
		if(i.length == 0) { //No attribute create new Attribute
			mealAtr = new MHRAttribute(getCtx(), 0, get_TrxName());
			mealAtr.setC_BPartner_ID(getC_BPartner_ID());
			mealAtr.setHR_Concept_ID(ot.getMealConcept_ID());
			mealAtr.setHR_Employee_ID(emp.get_ID());
			mealAtr.setValidFrom(new Timestamp(System.currentTimeMillis()));
			mealAtr.setColumnType("A");
			mealAtr.save();
		}else {
			mealAtr = new MHRAttribute(getCtx(), i[0], get_TrxName());
		}
		setMealAtrribute_ID(mealAtr.get_ID());
		
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
