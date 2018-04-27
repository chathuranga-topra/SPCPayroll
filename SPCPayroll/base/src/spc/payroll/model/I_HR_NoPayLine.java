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

/** Generated Interface for HR_NoPayLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_HR_NoPayLine 
{

    /** TableName=HR_NoPayLine */
    public static final String Table_Name = "HR_NoPayLine";

    /** AD_Table_ID=1000028 */
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

    /** Column name BaseAmt */
    public static final String COLUMNNAME_BaseAmt = "BaseAmt";

	/** Set Base Amt	  */
	public void setBaseAmt (BigDecimal BaseAmt);

	/** Get Base Amt	  */
	public BigDecimal getBaseAmt();

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

    /** Column name HR_NoPayLine_ID */
    public static final String COLUMNNAME_HR_NoPayLine_ID = "HR_NoPayLine_ID";

	/** Set HR_NoPayLine ID	  */
	public void setHR_NoPayLine_ID (int HR_NoPayLine_ID);

	/** Get HR_NoPayLine ID	  */
	public int getHR_NoPayLine_ID();

    /** Column name HR_NoPay_ID */
    public static final String COLUMNNAME_HR_NoPay_ID = "HR_NoPay_ID";

	/** Set NoPay ID	  */
	public void setHR_NoPay_ID (int HR_NoPay_ID);

	/** Get NoPay ID	  */
	public int getHR_NoPay_ID();

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

    /** Column name NoOfDays */
    public static final String COLUMNNAME_NoOfDays = "NoOfDays";

	/** Set No of Days	  */
	public void setNoOfDays (BigDecimal NoOfDays);

	/** Get No of Days	  */
	public BigDecimal getNoOfDays();

    /** Column name RatePerDay */
    public static final String COLUMNNAME_RatePerDay = "RatePerDay";

	/** Set Rate Per Day	  */
	public void setRatePerDay (BigDecimal RatePerDay);

	/** Get Rate Per Day	  */
	public BigDecimal getRatePerDay();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

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
