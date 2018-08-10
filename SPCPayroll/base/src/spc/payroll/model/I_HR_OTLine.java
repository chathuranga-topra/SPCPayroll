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

/** Generated Interface for HR_OTLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_HR_OTLine 
{

    /** TableName=HR_OTLine */
    public static final String Table_Name = "HR_OTLine";

    /** AD_Table_ID=1000020 */
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

    /** Column name DoubleOTAttribute_ID */
    public static final String COLUMNNAME_DoubleOTAttribute_ID = "DoubleOTAttribute_ID";

	/** Set DoubleOTAttribute_ID	  */
	public void setDoubleOTAttribute_ID (int DoubleOTAttribute_ID);

	/** Get DoubleOTAttribute_ID	  */
	public int getDoubleOTAttribute_ID();

    /** Column name HR_OTLine_ID */
    public static final String COLUMNNAME_HR_OTLine_ID = "HR_OTLine_ID";

	/** Set HR_OTLine ID	  */
	public void setHR_OTLine_ID (int HR_OTLine_ID);

	/** Get HR_OTLine ID	  */
	public int getHR_OTLine_ID();

    /** Column name HR_OT_ID */
    public static final String COLUMNNAME_HR_OT_ID = "HR_OT_ID";

	/** Set HR_OT ID	  */
	public void setHR_OT_ID (int HR_OT_ID);

	/** Get HR_OT ID	  */
	public int getHR_OT_ID();

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

    /** Column name IsMeal */
    public static final String COLUMNNAME_IsMeal = "IsMeal";

	/** Set Meal	  */
	public void setIsMeal (boolean IsMeal);

	/** Get Meal	  */
	public boolean isMeal();

    /** Column name LineTotalAmt */
    public static final String COLUMNNAME_LineTotalAmt = "LineTotalAmt";

	/** Set Line Total.
	  * Total line amount incl. Tax
	  */
	public void setLineTotalAmt (BigDecimal LineTotalAmt);

	/** Get Line Total.
	  * Total line amount incl. Tax
	  */
	public BigDecimal getLineTotalAmt();

    /** Column name MealAllowance */
    public static final String COLUMNNAME_MealAllowance = "MealAllowance";

	/** Set Meal Allowance	  */
	public void setMealAllowance (BigDecimal MealAllowance);

	/** Get Meal Allowance	  */
	public BigDecimal getMealAllowance();

    /** Column name MealAtrribute_ID */
    public static final String COLUMNNAME_MealAtrribute_ID = "MealAtrribute_ID";

	/** Set MealAtrribute_ID	  */
	public void setMealAtrribute_ID (int MealAtrribute_ID);

	/** Get MealAtrribute_ID	  */
	public int getMealAtrribute_ID();

    /** Column name OTAtrribute_ID */
    public static final String COLUMNNAME_OTAtrribute_ID = "OTAtrribute_ID";

	/** Set OTAtrribute_ID	  */
	public void setOTAtrribute_ID (int OTAtrribute_ID);

	/** Get OTAtrribute_ID	  */
	public int getOTAtrribute_ID();

    /** Column name OTHours */
    public static final String COLUMNNAME_OTHours = "OTHours";

	/** Set OTHours	  */
	public void setOTHours (BigDecimal OTHours);

	/** Get OTHours	  */
	public BigDecimal getOTHours();

    /** Column name OTHoursDouble */
    public static final String COLUMNNAME_OTHoursDouble = "OTHoursDouble";

	/** Set OTHoursDouble	  */
	public void setOTHoursDouble (BigDecimal OTHoursDouble);

	/** Get OTHoursDouble	  */
	public BigDecimal getOTHoursDouble();

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

    /** Column name Rate */
    public static final String COLUMNNAME_Rate = "Rate";

	/** Set Rate.
	  * Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate);

	/** Get Rate.
	  * Rate or Tax or Exchange
	  */
	public BigDecimal getRate();

    /** Column name RateDouble */
    public static final String COLUMNNAME_RateDouble = "RateDouble";

	/** Set RateDouble	  */
	public void setRateDouble (BigDecimal RateDouble);

	/** Get RateDouble	  */
	public BigDecimal getRateDouble();

    /** Column name Remarks */
    public static final String COLUMNNAME_Remarks = "Remarks";

	/** Set Remarks.
	  * Remarks
	  */
	public void setRemarks (String Remarks);

	/** Get Remarks.
	  * Remarks
	  */
	public String getRemarks();

    /** Column name TotalOTAmt */
    public static final String COLUMNNAME_TotalOTAmt = "TotalOTAmt";

	/** Set TotalOTAmt	  */
	public void setTotalOTAmt (BigDecimal TotalOTAmt);

	/** Get TotalOTAmt	  */
	public BigDecimal getTotalOTAmt();

    /** Column name TotalOTAmtDouble */
    public static final String COLUMNNAME_TotalOTAmtDouble = "TotalOTAmtDouble";

	/** Set TotalOTAmtDouble	  */
	public void setTotalOTAmtDouble (BigDecimal TotalOTAmtDouble);

	/** Get TotalOTAmtDouble	  */
	public BigDecimal getTotalOTAmtDouble();

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
