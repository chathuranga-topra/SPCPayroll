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

/** Generated Model for HR_Loan
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_Loan extends PO implements I_HR_Loan, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180801L;

    /** Standard Constructor */
    public X_HR_Loan (Properties ctx, int HR_Loan_ID, String trxName)
    {
      super (ctx, HR_Loan_ID, trxName);
      /** if (HR_Loan_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_LoanEarllySettle_ID (0);
			setHR_LoanType_ID (0);
			setHR_Loan_ID (0);
			setInstallmentCount (0);
// 0
			setLoanAmount (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_HR_Loan (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_Loan[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BP Search Key.
		@param BPValue 
		Business Partner Key Value
	  */
	public void setBPValue (String BPValue)
	{
		throw new IllegalArgumentException ("BPValue is virtual column");	}

	/** Get BP Search Key.
		@return Business Partner Key Value
	  */
	public String getBPValue () 
	{
		return (String)get_Value(COLUMNNAME_BPValue);
	}

	/** Set Balance.
		@param Balance Balance	  */
	public void setBalance (BigDecimal Balance)
	{
		throw new IllegalArgumentException ("Balance is virtual column");	}

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

	/** Set FP Balance.
		@param FPBalance FP Balance	  */
	public void setFPBalance (BigDecimal FPBalance)
	{
		throw new IllegalArgumentException ("FPBalance is virtual column");	}

	/** Get FP Balance.
		@return FP Balance	  */
	public BigDecimal getFPBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FPBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Fourty Present.
		@param FourtyPresent Fourty Present	  */
	public void setFourtyPresent (BigDecimal FourtyPresent)
	{
		set_Value (COLUMNNAME_FourtyPresent, FourtyPresent);
	}

	/** Get Fourty Present.
		@return Fourty Present	  */
	public BigDecimal getFourtyPresent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FourtyPresent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set GrantedDate.
		@param GrantedDate GrantedDate	  */
	public void setGrantedDate (Timestamp GrantedDate)
	{
		set_Value (COLUMNNAME_GrantedDate, GrantedDate);
	}

	/** Get GrantedDate.
		@return GrantedDate	  */
	public Timestamp getGrantedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_GrantedDate);
	}

	public org.eevolution.model.I_HR_Attribute getHR_Attribute() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Attribute)MTable.get(getCtx(), org.eevolution.model.I_HR_Attribute.Table_Name)
			.getPO(getHR_Attribute_ID(), get_TrxName());	}

	/** Set Payroll Attribute.
		@param HR_Attribute_ID 
		Employee Attribute allows to add any metadata of type (text, date , quantity and amount ) of an Employee.
	  */
	public void setHR_Attribute_ID (int HR_Attribute_ID)
	{
		if (HR_Attribute_ID < 1) 
			set_Value (COLUMNNAME_HR_Attribute_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Attribute_ID, Integer.valueOf(HR_Attribute_ID));
	}

	/** Get Payroll Attribute.
		@return Employee Attribute allows to add any metadata of type (text, date , quantity and amount ) of an Employee.
	  */
	public int getHR_Attribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Attribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public spc.payroll.model.I_HR_LoanEarllySettle getHR_LoanEarllySettle() throws RuntimeException
    {
		return (spc.payroll.model.I_HR_LoanEarllySettle)MTable.get(getCtx(), spc.payroll.model.I_HR_LoanEarllySettle.Table_Name)
			.getPO(getHR_LoanEarllySettle_ID(), get_TrxName());	}

	/** Set Earlly Settlement.
		@param HR_LoanEarllySettle_ID Earlly Settlement	  */
	public void setHR_LoanEarllySettle_ID (int HR_LoanEarllySettle_ID)
	{
		if (HR_LoanEarllySettle_ID < 1) 
			set_Value (COLUMNNAME_HR_LoanEarllySettle_ID, null);
		else 
			set_Value (COLUMNNAME_HR_LoanEarllySettle_ID, Integer.valueOf(HR_LoanEarllySettle_ID));
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

	public spc.payroll.model.I_HR_LoanType getHR_LoanType() throws RuntimeException
    {
		return (spc.payroll.model.I_HR_LoanType)MTable.get(getCtx(), spc.payroll.model.I_HR_LoanType.Table_Name)
			.getPO(getHR_LoanType_ID(), get_TrxName());	}

	/** Set LoanType.
		@param HR_LoanType_ID LoanType	  */
	public void setHR_LoanType_ID (int HR_LoanType_ID)
	{
		if (HR_LoanType_ID < 1) 
			set_Value (COLUMNNAME_HR_LoanType_ID, null);
		else 
			set_Value (COLUMNNAME_HR_LoanType_ID, Integer.valueOf(HR_LoanType_ID));
	}

	/** Get LoanType.
		@return LoanType	  */
	public int getHR_LoanType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LoanType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Loan ID.
		@param HR_Loan_ID HR_Loan ID	  */
	public void setHR_Loan_ID (int HR_Loan_ID)
	{
		if (HR_Loan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Loan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Loan_ID, Integer.valueOf(HR_Loan_ID));
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

	/** Set Installment Count.
		@param InstallmentCount Installment Count	  */
	public void setInstallmentCount (int InstallmentCount)
	{
		set_Value (COLUMNNAME_InstallmentCount, Integer.valueOf(InstallmentCount));
	}

	/** Get Installment Count.
		@return Installment Count	  */
	public int getInstallmentCount () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_InstallmentCount);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Attribute getInterestAttribute() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Attribute)MTable.get(getCtx(), org.eevolution.model.I_HR_Attribute.Table_Name)
			.getPO(getInterestAttribute_ID(), get_TrxName());	}

	/** Set Interest Attribute.
		@param InterestAttribute_ID Interest Attribute	  */
	public void setInterestAttribute_ID (int InterestAttribute_ID)
	{
		if (InterestAttribute_ID < 1) 
			set_Value (COLUMNNAME_InterestAttribute_ID, null);
		else 
			set_Value (COLUMNNAME_InterestAttribute_ID, Integer.valueOf(InterestAttribute_ID));
	}

	/** Get Interest Attribute.
		@return Interest Attribute	  */
	public int getInterestAttribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_InterestAttribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IsRenewalLoan.
		@param IsRenewalLoan IsRenewalLoan	  */
	public void setIsRenewalLoan (boolean IsRenewalLoan)
	{
		set_Value (COLUMNNAME_IsRenewalLoan, Boolean.valueOf(IsRenewalLoan));
	}

	/** Get IsRenewalLoan.
		@return IsRenewalLoan	  */
	public boolean isRenewalLoan () 
	{
		Object oo = get_Value(COLUMNNAME_IsRenewalLoan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Valid.
		@param IsValid 
		Element is valid
	  */
	public void setIsValid (boolean IsValid)
	{
		set_Value (COLUMNNAME_IsValid, Boolean.valueOf(IsValid));
	}

	/** Get Valid.
		@return Element is valid
	  */
	public boolean isValid () 
	{
		Object oo = get_Value(COLUMNNAME_IsValid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set LoanAmount.
		@param LoanAmount LoanAmount	  */
	public void setLoanAmount (BigDecimal LoanAmount)
	{
		set_Value (COLUMNNAME_LoanAmount, LoanAmount);
	}

	/** Get LoanAmount.
		@return LoanAmount	  */
	public BigDecimal getLoanAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LoanAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Loan Installment.
		@param LoanInstallment Loan Installment	  */
	public void setLoanInstallment (BigDecimal LoanInstallment)
	{
		set_Value (COLUMNNAME_LoanInstallment, LoanInstallment);
	}

	/** Get Loan Installment.
		@return Loan Installment	  */
	public BigDecimal getLoanInstallment () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LoanInstallment);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set LoanType.
		@param LoanType LoanType	  */
	public void setLoanType (String LoanType)
	{
		throw new IllegalArgumentException ("LoanType is virtual column");	}

	/** Get LoanType.
		@return LoanType	  */
	public String getLoanType () 
	{
		return (String)get_Value(COLUMNNAME_LoanType);
	}

	/** Set PayrollEffectiveDate.
		@param PayrollEffectiveDate PayrollEffectiveDate	  */
	public void setPayrollEffectiveDate (Timestamp PayrollEffectiveDate)
	{
		set_Value (COLUMNNAME_PayrollEffectiveDate, PayrollEffectiveDate);
	}

	/** Get PayrollEffectiveDate.
		@return PayrollEffectiveDate	  */
	public Timestamp getPayrollEffectiveDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PayrollEffectiveDate);
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

	/** Set Total Deduction.
		@param TotalDeduction Total Deduction	  */
	public void setTotalDeduction (BigDecimal TotalDeduction)
	{
		set_Value (COLUMNNAME_TotalDeduction, TotalDeduction);
	}

	/** Get Total Deduction.
		@return Total Deduction	  */
	public BigDecimal getTotalDeduction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalDeduction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set TotalEarnings.
		@param TotalEarnings TotalEarnings	  */
	public void setTotalEarnings (BigDecimal TotalEarnings)
	{
		set_Value (COLUMNNAME_TotalEarnings, TotalEarnings);
	}

	/** Get TotalEarnings.
		@return TotalEarnings	  */
	public BigDecimal getTotalEarnings () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalEarnings);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}