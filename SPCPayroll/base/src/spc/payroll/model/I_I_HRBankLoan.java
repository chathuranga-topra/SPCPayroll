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

/** Generated Interface for I_HRBankLoan
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_I_HRBankLoan 
{

    /** TableName=I_HRBankLoan */
    public static final String Table_Name = "I_HRBankLoan";

    /** AD_Table_ID=1000030 */
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

    /** Column name CapitalAmt */
    public static final String COLUMNNAME_CapitalAmt = "CapitalAmt";

	/** Set Capital Amount	  */
	public void setCapitalAmt (BigDecimal CapitalAmt);

	/** Get Capital Amount	  */
	public BigDecimal getCapitalAmt();

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

    /** Column name EffectiveFrom */
    public static final String COLUMNNAME_EffectiveFrom = "EffectiveFrom";

	/** Set EffectiveFrom	  */
	public void setEffectiveFrom (Timestamp EffectiveFrom);

	/** Get EffectiveFrom	  */
	public Timestamp getEffectiveFrom();

    /** Column name GrantedDate */
    public static final String COLUMNNAME_GrantedDate = "GrantedDate";

	/** Set GrantedDate	  */
	public void setGrantedDate (Timestamp GrantedDate);

	/** Get GrantedDate	  */
	public Timestamp getGrantedDate();

    /** Column name HR_LoanType_ID */
    public static final String COLUMNNAME_HR_LoanType_ID = "HR_LoanType_ID";

	/** Set LoanType	  */
	public void setHR_LoanType_ID (int HR_LoanType_ID);

	/** Get LoanType	  */
	public int getHR_LoanType_ID();

	public spc.payroll.model.I_HR_LoanType getHR_LoanType() throws RuntimeException;

    /** Column name I_HRBankLoan_ID */
    public static final String COLUMNNAME_I_HRBankLoan_ID = "I_HRBankLoan_ID";

	/** Set I_HRBankLoan ID	  */
	public void setI_HRBankLoan_ID (int I_HRBankLoan_ID);

	/** Get I_HRBankLoan ID	  */
	public int getI_HRBankLoan_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name InstallmentCount */
    public static final String COLUMNNAME_InstallmentCount = "InstallmentCount";

	/** Set Installment Count	  */
	public void setInstallmentCount (int InstallmentCount);

	/** Get Installment Count	  */
	public int getInstallmentCount();

    /** Column name InterestAmt */
    public static final String COLUMNNAME_InterestAmt = "InterestAmt";

	/** Set Interest Amount.
	  * Interest Amount
	  */
	public void setInterestAmt (BigDecimal InterestAmt);

	/** Get Interest Amount.
	  * Interest Amount
	  */
	public BigDecimal getInterestAmt();

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

    /** Column name LoanAmount */
    public static final String COLUMNNAME_LoanAmount = "LoanAmount";

	/** Set LoanAmount	  */
	public void setLoanAmount (BigDecimal LoanAmount);

	/** Get LoanAmount	  */
	public BigDecimal getLoanAmount();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

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

    /** Column name gepf1 */
    public static final String COLUMNNAME_gepf1 = "gepf1";

	/** Set gepf1.
	  * Alphanumeric identifier of the entity
	  */
	public void setgepf1 (String gepf1);

	/** Get gepf1.
	  * Alphanumeric identifier of the entity
	  */
	public String getgepf1();

    /** Column name gepf2 */
    public static final String COLUMNNAME_gepf2 = "gepf2";

	/** Set gepf2.
	  * Alphanumeric identifier of the entity
	  */
	public void setgepf2 (String gepf2);

	/** Get gepf2.
	  * Alphanumeric identifier of the entity
	  */
	public String getgepf2();
}
