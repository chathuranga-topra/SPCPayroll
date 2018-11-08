package spc.payroll.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import org.eevolution.model.MHRAttribute;

@SuppressWarnings("serial")
public class MHREmpBankAccount extends X_HR_EmpBankAccount{

	public MHREmpBankAccount(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHREmpBankAccount(Properties ctx, int HR_EmpBankAccount_ID,
			String trxName) {
		super(ctx, HR_EmpBankAccount_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		//creating new attribute for the concept
		MHRAttribute attribute = new MHRAttribute(this.getCtx() , getHR_Attribute_ID() , this.get_TrxName());
		attribute.setHR_Concept_ID(this.getHR_Concept_ID());
		//valid from treated as current month as 1 day
		Date effectiveFrom = new Date();
		effectiveFrom.setDate(1);
		attribute.setValidFrom(new Timestamp(effectiveFrom.getTime()));
		attribute.setC_BPartner_ID(this.getC_BPartner_ID());
		attribute.setColumnType(getHR_Concept().getColumnType());
		attribute.setAmount(getAmount());
		attribute.setIsActive(isActive());
		attribute.set_ValueOfColumn("HR_BankBranch_ID", getHR_BankBranch_ID());
		attribute.save();
		
		this.setHR_Attribute_ID(attribute.get_ID());
		
		return super.beforeSave(newRecord);
	}
	
	@Override
	protected boolean beforeDelete() {
		
		//delete the attribute
		if(getHR_Attribute_ID()!=0){
			MHRAttribute attribute = new MHRAttribute(this.getCtx() , getHR_Attribute_ID() , this.get_TrxName());
			attribute.delete(true);
			attribute.save();
		}
		
		return super.beforeDelete();
	}

}
