package spc.payroll.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;

public class MHROT extends X_HR_OT implements DocAction , DocOptions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MHROT(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MHROT(Properties ctx, int HR_OT_ID, String trxName) {
		super(ctx, HR_OT_ID, trxName);
	}
	
	protected boolean beforeSave(boolean newRecord){
		
		return true;
	}

	@Override
	public int customizeValidActions(String docStatus, Object processing, String orderType, String isSOTrx,
			int AD_Table_ID, String[] docAction, String[] options, int index) {
		/*if (docStatus.equals(DocumentEngine.STATUS_Drafted)
    			|| docStatus.equals(DocumentEngine.STATUS_Invalid)) {
    		options[index++] = DocumentEngine.ACTION_Prepare;
    	}*/
    	// If status = Completed, add "Reactivate" in the list
    	if (docStatus.equals(DocumentEngine.STATUS_Completed)) {
    		//options[index++] = DocumentEngine.ACTION_ReActivate;
    		options[index++] = DocumentEngine.ACTION_Void;
    		options[index++] = DocumentEngine.ACTION_ReActivate;
    	}
		return index;
	}

	@Override
	public boolean processIt(String action) throws Exception {
		
		if(getDocAction().equals("CO") && getDocStatus().equals("DR"))
			this.completeIt();
		else if(getDocAction().equalsIgnoreCase("CL") && getDocStatus().equals("CO"))
			this.closeIt();
		else if(getDocAction().equalsIgnoreCase("VO"))
			this.voidIt();
		else if(getDocAction().equalsIgnoreCase("RE")){
			this.reActivateIt();
		}
		
		return true;
	}

	@Override
	public boolean unlockIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean invalidateIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String prepareIt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approveIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String completeIt() {
		
		//remove non OT lines
		String sql = "";//"DELETE FROM HR_OTLine where HR_OT_ID= ? and OTHours <= 0 OR OTHours is null ";
		//DB.executeUpdate(sql, get_ID(), get_TrxName());
		
		//validate lines
		 if(MHROTLine.getLines(this).length == 0)
			 throw new AdempiereException("No OT lines with quantity");
		
		sql = "UPDATE HR_OTLine SET PROCESSED = 'Y' WHERE HR_OT_ID= ?";
		DB.executeUpdate(sql, get_ID(), get_TrxName());
		
		setDocStatus("CO");
		setProcessed(true);

		return "Done!";
	}

	@Override
	public boolean voidIt() {
		
		setDocStatus("VO");
		setDocAction("--");
		
		return true;
	}

	@Override
	public boolean closeIt() {
		
		setDocStatus("CL");
		setDocAction("--");
		
		return true;
	}

	@Override
	public boolean reverseCorrectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reActivateIt() {
		
		this.setDocStatus(DOCSTATUS_Drafted);
		this.setDocAction(DOCACTION_Prepare);
		this.setProcessed(false);
		
		//update the lines
		String sql = "UPDATE HR_OTLine set Processed = 'N' WHERE HR_OT_ID = ? ";
		DB.executeUpdate(sql, get_ID(), get_TrxName());
		
		return true;
	}

	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocumentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getC_Currency_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return null;
	}

}
