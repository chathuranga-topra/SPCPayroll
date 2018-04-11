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

/** Generated Interface for HR_LoanEarllySettlelLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_HR_LoanEarllySettlelLine 
{

    /** TableName=HR_LoanEarllySettlelLine */
    public static final String Table_Name = "HR_LoanEarllySettlelLine";

    /** AD_Table_ID=1000013 */
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

    /** Column name EffectiveFrom */
    public static final String COLUMNNAME_EffectiveFrom = "EffectiveFrom";

	/** Set EffectiveFrom	  */
	public void setEffectiveFrom (Timestamp EffectiveFrom);

	/** Get EffectiveFrom	  */
	public Timestamp getEffectiveFrom();

    /** Column name HR_LoanEarllySettle_ID */
    public static final String COLUMNNAME_HR_LoanEarllySettle_ID = "HR_LoanEarllySettle_ID";

	/** Set Earlly Settlement	  */
	public void setHR_LoanEarllySettle_ID (int HR_LoanEarllySettle_ID);

	/** Get Earlly Settlement	  */
	public int getHR_LoanEarllySettle_ID();

    /** Column name HR_LoanEarllySettlelLine_ID */
    public static final String COLUMNNAME_HR_LoanEarllySettlelLine_ID = "HR_LoanEarllySettlelLine_ID";

	/** Set HR_LoanEarllySettlelLine ID	  */
	public void setHR_LoanEarllySettlelLine_ID (int HR_LoanEarllySettlelLine_ID);

	/** Get HR_LoanEarllySettlelLine ID	  */
	public int getHR_LoanEarllySettlelLine_ID();

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

    /** Column name OriginalInterest */
    public static final String COLUMNNAME_OriginalInterest = "OriginalInterest";

	/** Set Original Interest	  */
	public void setOriginalInterest (BigDecimal OriginalInterest);

	/** Get Original Interest	  */
	public BigDecimal getOriginalInterest();

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

    /** Column name RemainingDays */
    public static final String COLUMNNAME_RemainingDays = "RemainingDays";

	/** Set Remaining Days	  */
	public void setRemainingDays (int RemainingDays);

	/** Get Remaining Days	  */
	public int getRemainingDays();

    /** Column name RevisedInterest */
    public static final String COLUMNNAME_RevisedInterest = "RevisedInterest";

	/** Set Revised Interest	  */
	public void setRevisedInterest (BigDecimal RevisedInterest);

	/** Get Revised Interest	  */
	public BigDecimal getRevisedInterest();

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
