package spc.payroll.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttribute;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.eevolution.model.MHRAttribute;
import org.eevolution.model.MHRConcept;
import org.eevolution.model.MHRMovement;
import org.eevolution.model.MHRProcess;

public class MHRNoPayLine extends X_HR_NoPayLine{

	public MHRNoPayLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNoPayLine(Properties ctx, int HR_NoPayLine_ID, String trxName) {
		super(ctx, HR_NoPayLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	protected boolean beforeSave(boolean newRecord){
	
		//validate nopay date
		if(getNoOfDays().intValue() == 0) {
			throw new AdempiereException("Enter no pay days count!");
		}
		
		//when changing day count
		if(! (newRecord || getNoOfDays().intValue() == get_ValueOldAsInt(COLUMNNAME_NoOfDays))) {
			this.changeNoOfDates(getNoOfDays());
		}
		
		MHRNoPay noPay = new MHRNoPay(getCtx(), getHR_NoPay_ID(), get_TrxName());
		//validate paroll movement lines are available
		validatePayMoveLine(noPay);
		
		return true;
	}
	
	protected boolean afterSave (boolean newRecord, boolean success) {
		
		MHRNoPay noPay = new MHRNoPay(getCtx(), getHR_NoPay_ID(), get_TrxName());
		
		if(newRecord || getC_BPartner_ID()!=get_ValueOldAsInt(COLUMNNAME_C_BPartner_ID)) {
			createNoPayAttributeLines(noPay);
		}
		
		return success;
	}	//	afterSave
	
	//create attributes
	@SuppressWarnings("deprecation")
	private void createNoPayAttributeLines(MHRNoPay noPay) {
		
		String sql = "";
		//delete * before crating
		sql = "DELETE FROM HR_NoPayAttribute WHERE HR_NoPayLine_ID = ? ";
		//DB.executeUpdate(sql, get_TrxName() , get_ID());
		
		sql = "select m.hr_concept_id , m.amount , con.nopayconcept_id " + 
		"from  HR_Movement m " + 
		"inner join hr_concept con on con.hr_concept_id = m.hr_concept_id " + 
		"where m.hr_process_id = ? " + 
		"and m.c_bpartner_id = ? " + 
		"and con.nopay = 'Y' ";
		
		PreparedStatement psmt = null; ResultSet rs = null;
		
		try {
			psmt = DB.prepareStatement(sql, get_TrxName());
			psmt.setInt(1, noPay.getHR_Process_ID());
			psmt.setInt(2, getC_BPartner_ID());
			
			rs = psmt.executeQuery();
			
			MHRNoPayAttribute npa = null;
			MHRNoPayLine npl = new MHRNoPayLine(getCtx(), get_ID(), get_TrxName());
			MHRAttribute attribute = null;Date d = null; 
			
			while(rs.next()){
				
				npa = new MHRNoPayAttribute(getCtx(), 0, get_TrxName());
				
				npa.setHR_Concept_ID(rs.getInt("hr_concept_id"));
				npa.setHR_NoPayLine_ID(this.get_ID());
				npa.setNoPayConcept_ID(rs.getInt("nopayconcept_id"));
				npa.setBaseAmt(rs.getBigDecimal("amount"));
				npa.setNoOfDays(getNoOfDays());
				//setup day rate
				npa.setDayRate(rs.getBigDecimal("amount").divide(new BigDecimal(noPay.getMonthWorkDays()) , 2 , RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP));
				
				//set deduction
				npa.setDeduction(npa.getDayRate().multiply(getNoOfDays()).setScale(2, RoundingMode.HALF_UP));
				//set balance
				npa.setBalance(npa.getBaseAmt().subtract(npa.getDeduction()));
				
				//validate payroll attribute
				sql = "C_BPartner_ID= "+npl.getC_BPartner_ID()+" "
					+ " and  HR_Concept_ID="+npa.getNoPayConcept_ID();
				
				int [] atr = MHRAttribute.getAllIDs(MHRAttribute.Table_Name, sql, get_TrxName());
				
				if(atr.length == 0) {
					//create new payroll attribute
					attribute = new MHRAttribute(getCtx(), 0, get_TrxName());
					attribute.setHR_Concept_ID(npa.getNoPayConcept_ID());
					attribute.setC_BPartner_ID(npl.getC_BPartner_ID());
					
					//valid from current month 1st date
					d =new Date(System.currentTimeMillis());
					d.setDate(1);
					attribute.setValidFrom(new Timestamp(d.getTime()));
					attribute.save();
					npa.setHR_Attribute_ID(attribute.get_ID()); // last added one
					
				}else {
					npa.setHR_Attribute_ID(atr[atr.length - 1]); // last added one
				}
				
				npa.save();
			}
			
		}catch(Exception ex) {
			
			throw new AdempiereException(ex.getMessage());
		}finally {
			DB.close(rs, psmt);
			rs = null;psmt = null;
		}
	}
	
	public static MHRNoPayLine [] getLines(MHRNoPay noPay) {
		
		List<MHRNoPayLine> list = new Query(noPay.getCtx(), MHRNoPayLine.Table_Name, COLUMNNAME_HR_NoPay_ID+"=?", noPay.get_TrxName())
		.setParameters(noPay.get_ID())
		.list();
		return list.toArray(new MHRNoPayLine[list.size()]);
	}
	
	private void changeNoOfDates(BigDecimal NoOfDays) {
		
		
		int [] ii = MHRNoPayAttribute.getAllIDs(MHRNoPayAttribute.Table_Name, "HR_NoPayLine_ID = " + get_ID(), get_TrxName());
		MHRNoPayAttribute npa = null;
		
		for(int i : ii) {
			npa = new MHRNoPayAttribute(getCtx(),i, get_TrxName());
			npa.setNoOfDays(NoOfDays);
			//set deduction
			npa.setDeduction(npa.getDayRate().multiply(NoOfDays).setScale(2, RoundingMode.HALF_UP));
			//set balance
			npa.setBalance(npa.getBaseAmt().subtract(npa.getDeduction()));
			npa.save();
		}
	}
	
	//validate for current business partner has payroll movement in for particular process 
	private void validatePayMoveLine(MHRNoPay noPay) {
		
		
		String sql = "SELECT COUNT(*) FROM HR_Movement WHERE HR_Process_ID = ? "
				+ "AND C_Bpartner_ID = ? ";
		
		int count = DB.getSQLValue(get_TrxName(), sql, noPay.getHR_Process_ID() , getC_BPartner_ID());
		
		if(count <= 0) {
			throw new AdempiereException("No payroll movement found!");
		}
	}
	
	public ResultSet getInforToCreateMovements() {
		
		String sql = "select sum(deduction) as total , nopayconcept_id " + 
		"from HR_NoPayAttribute " + 
		"where HR_NoPayLine_ID=? " + 
		"and ad_client_id = ? " + 
		"group by hr_attribute_id ,nopayconcept_id";
		
		PreparedStatement psmt = null; ResultSet rs = null;
		try{
			
			psmt = DB.prepareStatement(sql, get_TrxName());
			psmt.setInt(1, this.get_ID());
			psmt.setInt(2, this.getAD_Client_ID());
			
			rs = psmt.executeQuery();
			
		}catch(Exception ex) { }
		
		return rs;
	}
}
