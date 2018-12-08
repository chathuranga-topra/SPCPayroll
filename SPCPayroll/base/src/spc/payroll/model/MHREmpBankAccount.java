package spc.payroll.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MInvoice;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
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
	
	private MHRAttribute [] getHRAttribute(){
		
		List<MHRAttribute> list = new Query(
				getCtx(),
				MHRAttribute.Table_Name, 
				COLUMNNAME_C_BPartner_ID+"=? AND HR_Concept_ID = ? ", 
				get_TrxName())
				.setParameters(getC_BPartner_ID() , this.getHR_Concept_ID())
				.setOrderBy("HR_Attribute_ID DESC ")
				.list();
		return list.toArray(new MHRAttribute[list.size()]);
	}
	
	@Override
	protected boolean afterSave (boolean newRecord, boolean success){
		/*
		if(isDefault()) //Default accounts are not considered for salary deduction 
			return success;*/
		
		//VALIDATE ALREADY HAVING A HR ATTRIBUTE
		MHRAttribute [] attributes = getHRAttribute();
		
		MHRAttribute attribute = null;
		if(attributes.length == 0){ // new record
			
			attribute = new MHRAttribute(this.getCtx() ,0, this.get_TrxName());
			attribute.setHR_Concept_ID(this.getHR_Concept_ID());
			Date effectiveFrom = new Date();
			effectiveFrom.setDate(1); //valid from treated as current month as 1 day
			attribute.setValidFrom(new Timestamp(effectiveFrom.getTime()));
			attribute.setC_BPartner_ID(this.getC_BPartner_ID());
			attribute.setColumnType(getHR_Concept().getColumnType());
			attribute.setAmount(getAmount());
			attribute.setIsActive(isActive());
			
		}else{ //there are records
			attribute = attributes[0];
			attribute.setAmount(getSumAmount());
		}
		
		attribute.save();
		
		this.setHR_Attribute_ID(attribute.get_ID());
		
		return success;
	}
	
	private BigDecimal getSumAmount(){
		
		String sql = "SELECT SUM(amount) FROM "
			+ "HR_EmpBankAccount where C_BPartner_ID=? AND IsActive = 'Y' AND isDefault = 'N' ";
		
		return DB.getSQLValueBD(get_TrxName(), sql, getC_BPartner_ID());
	}
	
	protected boolean afterDelete (boolean success){
	
		MHRAttribute [] attributes = getHRAttribute();
		MHRAttribute attribute = attributes[0];
		attribute.setAmount(getSumAmount());
		attribute.save();
		
		return success;
	} 
}
