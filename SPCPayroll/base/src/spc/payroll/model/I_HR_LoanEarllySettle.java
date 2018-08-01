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
package spc.payroll.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_LoanEarllySettle
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_HR_LoanEarllySettle 
{

    /** TableName=HR_LoanEarllySettle */
    public static final String Table_Name = "HR_LoanEarllySettle";

    /** AD_Table_ID=1000011 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Balance */
    public static final String COLUMNNAME_Balance = "Balance";

	/** Set Balance	  */
	public void setBalance (BigDecimal Balance);

	/** Get Balance	  */
	public BigDecimal getBalance();

    /** Column name BalanceToPaid */
    public static final String COLUMNNAME_BalanceToPaid = "BalanceToPaid";

	/** Set BalanceToPaid	  */
	public void setBalanceToPaid (BigDecimal BalanceToPaid);

	/** Get BalanceToPaid	  */
	public BigDecimal getBalanceToPaid();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Deduction */
    public static final String COLUMNNAME_Deduction = "Deduction";

	/** Set Deduction	  */
	public void setDeduction (BigDecimal Deduction);

	/** Get Deduction	  */
	public BigDecimal getDeduction();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name HR_LoanEarllySettle_ID */
    public static final String COLUMNNAME_HR_LoanEarllySettle_ID = "HR_LoanEarllySettle_ID";

	/** Set Earlly Settlement	  */
	public void setHR_LoanEarllySettle_ID (int HR_LoanEarllySettle_ID);

	/** Get Earlly Settlement	  */
	public int getHR_LoanEarllySettle_ID();

    /** Column name HR_Loan_ID */
    public static final String COLUMNNAME_HR_Loan_ID = "HR_Loan_ID";

	/** Set HR_Loan ID	  */
	public void setHR_Loan_ID (int HR_Loan_ID);

	/** Get HR_Loan ID	  */
	public int getHR_Loan_ID();

	public spc.payroll.model.I_HR_Loan getHR_Loan() throws RuntimeException;

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name LateDaysInterest */
    public static final String COLUMNNAME_LateDaysInterest = "LateDaysInterest";

	/** Set LateDaysInterest	  */
	public void setLateDaysInterest (BigDecimal LateDaysInterest);

	/** Get LateDaysInterest	  */
	public BigDecimal getLateDaysInterest();

    /** Column name NewHR_Loan_ID */
    public static final String COLUMNNAME_NewHR_Loan_ID = "NewHR_Loan_ID";

	/** Set NewHR_Loan_ID	  */
	public void setNewHR_Loan_ID (int NewHR_Loan_ID);

	/** Get NewHR_Loan_ID	  */
	public int getNewHR_Loan_ID();

	public spc.payroll.model.I_HR_Loan getNewHR_Loan() throws RuntimeException;

    /** Column name NewInterestTotal */
    public static final String COLUMNNAME_NewInterestTotal = "NewInterestTotal";

	/** Set New Interest Total	  */
	public void setNewInterestTotal (BigDecimal NewInterestTotal);

	/** Get New Interest Total	  */
	public BigDecimal getNewInterestTotal();

    /** Column name NewLoanAmount */
    public static final String COLUMNNAME_NewLoanAmount = "NewLoanAmount";

	/** Set NewLoanAmount	  */
	public void setNewLoanAmount (BigDecimal NewLoanAmount);

	/** Get NewLoanAmount	  */
	public BigDecimal getNewLoanAmount();

    /** Column name OldInterestTotal */
    public static final String COLUMNNAME_OldInterestTotal = "OldInterestTotal";

	/** Set Old Interest Total	  */
	public void setOldInterestTotal (BigDecimal OldInterestTotal);

	/** Get Old Interest Total	  */
	public BigDecimal getOldInterestTotal();

    /** Column name PaidInstallmentCount */
    public static final String COLUMNNAME_PaidInstallmentCount = "PaidInstallmentCount";

	/** Set Paid Installment	  */
	public void setPaidInstallmentCount (int PaidInstallmentCount);

	/** Get Paid Installment	  */
	public int getPaidInstallmentCount();

    /** Column name PayableInstallmentCount */
    public static final String COLUMNNAME_PayableInstallmentCount = "PayableInstallmentCount";

	/** Set Payable Installment	  */
	public void setPayableInstallmentCount (int PayableInstallmentCount);

	/** Get Payable Installment	  */
	public int getPayableInstallmentCount();

    /** Column name PaymentDocumentNo */
    public static final String COLUMNNAME_PaymentDocumentNo = "PaymentDocumentNo";

	/** Set Payment Document No.
	  * Document number of the Payment
	  */
	public void setPaymentDocumentNo (String PaymentDocumentNo);

	/** Get Payment Document No.
	  * Document number of the Payment
	  */
	public String getPaymentDocumentNo();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name SettleDate */
    public static final String COLUMNNAME_SettleDate = "SettleDate";

	/** Set Settle Date	  */
	public void setSettleDate (Timestamp SettleDate);

	/** Get Settle Date	  */
	public Timestamp getSettleDate();

    /** Column name TotalPayable */
    public static final String COLUMNNAME_TotalPayable = "TotalPayable";

	/** Set TotalPayable	  */
	public void setTotalPayable (BigDecimal TotalPayable);

	/** Get TotalPayable	  */
	public BigDecimal getTotalPayable();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
