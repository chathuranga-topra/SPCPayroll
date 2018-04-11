/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package spc.payroll.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_LoanEarllySettle
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_LoanEarllySettle extends PO implements I_HR_LoanEarllySettle, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180403L;

    /** Standard Constructor */
    public X_HR_LoanEarllySettle (Properties ctx, int HR_LoanEarllySettle_ID, String trxName)
    {
      super (ctx, HR_LoanEarllySettle_ID, trxName);
      /** if (HR_LoanEarllySettle_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_LoanEarllySettle_ID (0);
			setHR_Loan_ID (0);
			setPaymentDocumentNo (null);
			setRemainingDays (0);
			setSettleDate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_HR_LoanEarllySettle (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_HR_LoanEarllySettle[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Balance.
		@param Balance Balance	  */
	public void setBalance (BigDecimal Balance)
	{
		set_Value (COLUMNNAME_Balance, Balance);
	}

	/** Get Balance.
		@return Balance	  */
	public BigDecimal getBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Balance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Earlly Settlement.
		@param HR_LoanEarllySettle_ID Earlly Settlement	  */
	public void setHR_LoanEarllySettle_ID (int HR_LoanEarllySettle_ID)
	{
		if (HR_LoanEarllySettle_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LoanEarllySettle_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LoanEarllySettle_ID, Integer.valueOf(HR_LoanEarllySettle_ID));
	}

	/** Get Earlly Settlement.
		@return Earlly Settlement	  */
	public int getHR_LoanEarllySettle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LoanEarllySettle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public spc.payroll.model.I_HR_Loan getHR_Loan() throws RuntimeException
    {
		return (spc.payroll.model.I_HR_Loan)MTable.get(getCtx(), spc.payroll.model.I_HR_Loan.Table_Name)
			.getPO(getHR_Loan_ID(), get_TrxName());	}

	/** Set HR_Loan ID.
		@param HR_Loan_ID HR_Loan ID	  */
	public void setHR_Loan_ID (int HR_Loan_ID)
	{
		if (HR_Loan_ID < 1) 
			set_Value (COLUMNNAME_HR_Loan_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Loan_ID, Integer.valueOf(HR_Loan_ID));
	}

	/** Get HR_Loan ID.
		@return HR_Loan ID	  */
	public int getHR_Loan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Loan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set New Interest Total.
		@param NewInterestTotal New Interest Total	  */
	public void setNewInterestTotal (BigDecimal NewInterestTotal)
	{
		set_Value (COLUMNNAME_NewInterestTotal, NewInterestTotal);
	}

	/** Get New Interest Total.
		@return New Interest Total	  */
	public BigDecimal getNewInterestTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NewInterestTotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Old Interest Total.
		@param OldInterestTotal Old Interest Total	  */
	public void setOldInterestTotal (BigDecimal OldInterestTotal)
	{
		set_Value (COLUMNNAME_OldInterestTotal, OldInterestTotal);
	}

	/** Get Old Interest Total.
		@return Old Interest Total	  */
	public BigDecimal getOldInterestTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OldInterestTotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Payment Document No.
		@param PaymentDocumentNo 
		Document number of the Payment
	  */
	public void setPaymentDocumentNo (String PaymentDocumentNo)
	{
		set_Value (COLUMNNAME_PaymentDocumentNo, PaymentDocumentNo);
	}

	/** Get Payment Document No.
		@return Document number of the Payment
	  */
	public String getPaymentDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_PaymentDocumentNo);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Remaining Days.
		@param RemainingDays Remaining Days	  */
	public void setRemainingDays (int RemainingDays)
	{
		set_Value (COLUMNNAME_RemainingDays, Integer.valueOf(RemainingDays));
	}

	/** Get Remaining Days.
		@return Remaining Days	  */
	public int getRemainingDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RemainingDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Settle Date.
		@param SettleDate Settle Date	  */
	public void setSettleDate (Timestamp SettleDate)
	{
		set_Value (COLUMNNAME_SettleDate, SettleDate);
	}

	/** Get Settle Date.
		@return Settle Date	  */
	public Timestamp getSettleDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_SettleDate);
	}
}