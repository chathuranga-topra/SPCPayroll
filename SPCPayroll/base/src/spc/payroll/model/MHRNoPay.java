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

@SuppressWarnings("serial")
public class MHRNoPay extends X_HR_NoPay implements DocAction , DocOptions {

	private boolean isJustProcessed = false;
	
	public MHRNoPay(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MHRNoPay(Properties ctx, int HR_NoPay_ID, String trxName) {
		super(ctx, HR_NoPay_ID, trxName);
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
		
		if(isJustProcessed)
			return true;
		
		if(getDocAction().equals("CO") && getDocStatus().equals("DR") && action.equals("--"))
			this.completeIt();
		else if(getDocAction().equalsIgnoreCase("CL") && getDocStatus().equals("CO"))
			this.closeIt();
		else if(getDocAction().equalsIgnoreCase("VO") && getDocStatus().equals("CO"))
			this.voidIt();
		else if(getDocAction().equalsIgnoreCase("RE") && getDocStatus().equals("CO"))
			this.reActivateIt();
		
		isJustProcessed = true;
		
		return true;
	}

	@Override
	public boolean unlockIt() { return false; }
	@Override
	public boolean invalidateIt() { return false; }
	@Override
	public String prepareIt() { return null; }
	@Override
	public boolean approveIt() { return false; }
	@Override
	public boolean rejectIt() { return false; }
	@Override
	public String completeIt() {
		
		//before complete validate lines
		MHRNoPayLine [] lines = MHRNoPayLine.getLines(this);
		if(lines.length == 0) {
			throw new AdempiereException("Zero nopay lines found!");
		}
		
		//update all payroll lines as processed
		String sql = "UPDATE HR_NoPayLine SET PROCESSED = 'Y' WHERE HR_NoPay_ID = ?";
		DB.executeUpdate(sql, get_ID(), get_TrxName());
		
		setDocStatus("CO");
		setProcessed(true);
		return null;
	}
	

	@Override
	public boolean voidIt() {
		
		setDocStatus("VO");
		setDocAction("--");
		return false;
	}

	@Override
	public boolean closeIt() {
		setDocStatus("CL");
		setDocAction("--");
		
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		return false;
	}

	@Override
	public boolean reActivateIt() {
		
		String sql = "UPDATE HR_NoPayLine SET PROCESSED = 'N' WHERE HR_NoPay_ID = ?";
		DB.executeUpdate(sql, this.get_ID(), get_TrxName());
		
		this.setProcessed(false);
		this.setDocStatus(DOCSTATUS_Drafted);
		this.setDocAction(DOCACTION_Complete);
		
		return true;
	}

	@Override
	public String getSummary() {
		return null;
	}

	@Override
	public String getDocumentInfo() {
		return null;
	}

	@Override
	public File createPDF() {
		return null;
	}

	@Override
	public String getProcessMsg() {
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		return 0;
	}

	@Override
	public int getC_Currency_ID() {
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		return null;
	}
}
