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

/** Generated Interface for HR_Loan
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_HR_Loan 
{

    /** TableName=HR_Loan */
    public static final String Table_Name = "HR_Loan";

    /** AD_Table_ID=1000009 */
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

    /** Column name BPValue */
    public static final String COLUMNNAME_BPValue = "BPValue";

	/** Set BP Search Key.
	  * Business Partner Key Value
	  */
	public void setBPValue (String BPValue);

	/** Get BP Search Key.
	  * Business Partner Key Value
	  */
	public String getBPValue();

    /** Column name Balance */
    public static final String COLUMNNAME_Balance = "Balance";

	/** Set Balance	  */
	public void setBalance (BigDecimal Balance);

	/** Get Balance	  */
	public BigDecimal getBalance();

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

    /** Column name FPBalance */
    public static final String COLUMNNAME_FPBalance = "FPBalance";

	/** Set FP Balance	  */
	public void setFPBalance (BigDecimal FPBalance);

	/** Get FP Balance	  */
	public BigDecimal getFPBalance();

    /** Column name FourtyPresent */
    public static final String COLUMNNAME_FourtyPresent = "FourtyPresent";

	/** Set Fourty Present	  */
	public void setFourtyPresent (BigDecimal FourtyPresent);

	/** Get Fourty Present	  */
	public BigDecimal getFourtyPresent();

    /** Column name GrantedDate */
    public static final String COLUMNNAME_GrantedDate = "GrantedDate";

	/** Set GrantedDate	  */
	public void setGrantedDate (Timestamp GrantedDate);

	/** Get GrantedDate	  */
	public Timestamp getGrantedDate();

    /** Column name HR_Attribute_ID */
    public static final String COLUMNNAME_HR_Attribute_ID = "HR_Attribute_ID";

	/** Set Payroll Attribute.
	  * Employee Attribute allows to add any metadata of type (text, date , quantity and amount ) of an Employee.
	  */
	public void setHR_Attribute_ID (int HR_Attribute_ID);

	/** Get Payroll Attribute.
	  * Employee Attribute allows to add any metadata of type (text, date , quantity and amount ) of an Employee.
	  */
	public int getHR_Attribute_ID();

	public org.eevolution.model.I_HR_Attribute getHR_Attribute() throws RuntimeException;

    /** Column name HR_LoanType_ID */
    public static final String COLUMNNAME_HR_LoanType_ID = "HR_LoanType_ID";

	/** Set LoanType	  */
	public void setHR_LoanType_ID (int HR_LoanType_ID);

	/** Get LoanType	  */
	public int getHR_LoanType_ID();

	public spc.payroll.model.I_HR_LoanType getHR_LoanType() throws RuntimeException;

    /** Column name HR_Loan_ID */
    public static final String COLUMNNAME_HR_Loan_ID = "HR_Loan_ID";

	/** Set HR_Loan ID	  */
	public void setHR_Loan_ID (int HR_Loan_ID);

	/** Get HR_Loan ID	  */
	public int getHR_Loan_ID();

    /** Column name InstallmentCount */
    public static final String COLUMNNAME_InstallmentCount = "InstallmentCount";

	/** Set Installment Count	  */
	public void setInstallmentCount (int InstallmentCount);

	/** Get Installment Count	  */
	public int getInstallmentCount();

    /** Column name InterestAttribute_ID */
    public static final String COLUMNNAME_InterestAttribute_ID = "InterestAttribute_ID";

	/** Set Interest Attribute	  */
	public void setInterestAttribute_ID (int InterestAttribute_ID);

	/** Get Interest Attribute	  */
	public int getInterestAttribute_ID();

	public org.eevolution.model.I_HR_Attribute getInterestAttribute() throws RuntimeException;

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

    /** Column name IsValid */
    public static final String COLUMNNAME_IsValid = "IsValid";

	/** Set Valid.
	  * Element is valid
	  */
	public void setIsValid (boolean IsValid);

	/** Get Valid.
	  * Element is valid
	  */
	public boolean isValid();

    /** Column name LoanAmount */
    public static final String COLUMNNAME_LoanAmount = "LoanAmount";

	/** Set LoanAmount	  */
	public void setLoanAmount (BigDecimal LoanAmount);

	/** Get LoanAmount	  */
	public BigDecimal getLoanAmount();

    /** Column name LoanInstallment */
    public static final String COLUMNNAME_LoanInstallment = "LoanInstallment";

	/** Set Loan Installment	  */
	public void setLoanInstallment (BigDecimal LoanInstallment);

	/** Get Loan Installment	  */
	public BigDecimal getLoanInstallment();

    /** Column name LoanType */
    public static final String COLUMNNAME_LoanType = "LoanType";

	/** Set LoanType	  */
	public void setLoanType (String LoanType);

	/** Get LoanType	  */
	public String getLoanType();

    /** Column name PayrollEffectiveDate */
    public static final String COLUMNNAME_PayrollEffectiveDate = "PayrollEffectiveDate";

	/** Set PayrollEffectiveDate	  */
	public void setPayrollEffectiveDate (Timestamp PayrollEffectiveDate);

	/** Get PayrollEffectiveDate	  */
	public Timestamp getPayrollEffectiveDate();

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

    /** Column name TotalDeduction */
    public static final String COLUMNNAME_TotalDeduction = "TotalDeduction";

	/** Set Total Deduction	  */
	public void setTotalDeduction (BigDecimal TotalDeduction);

	/** Get Total Deduction	  */
	public BigDecimal getTotalDeduction();

    /** Column name TotalEarnings */
    public static final String COLUMNNAME_TotalEarnings = "TotalEarnings";

	/** Set TotalEarnings	  */
	public void setTotalEarnings (BigDecimal TotalEarnings);

	/** Get TotalEarnings	  */
	public BigDecimal getTotalEarnings();

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
