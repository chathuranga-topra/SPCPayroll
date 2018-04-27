package spc.payroll.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;

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
	
	//create attribues
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
			
			while(rs.next()){
				
				npa = new MHRNoPayAttribute(getCtx(), 0, get_TrxName());
				
				npa.setHR_Concept_ID(rs.getInt("hr_concept_id"));
				npa.setHR_NoPayLine_ID(this.get_ID());
				npa.setNoPayConcept_ID(rs.getInt("nopayconcept_id"));
				npa.setBaseAmt(rs.getBigDecimal("amount"));
				
				npa.save();
				
				System.out.println(npa + " " +  get_ID() + "");
				
			}	
			
		}catch(Exception ex) {
			
			throw new AdempiereException(ex.getMessage());
		}finally {
			DB.close(rs, psmt);
			rs = null;psmt = null;
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
}
