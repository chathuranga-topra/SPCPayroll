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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_LoanType
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_LoanType extends PO implements I_HR_LoanType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180320L;

    /** Standard Constructor */
    public X_HR_LoanType (Properties ctx, int HR_LoanType_ID, String trxName)
    {
      super (ctx, HR_LoanType_ID, trxName);
      /** if (HR_LoanType_ID == 0)
        {
			setHR_Concept_ID (0);
// 0
			setHR_LoanType_ID (0);
			setInstallmentCount (0);
// 0
			setMaxAmt (Env.ZERO);
			setMinAmt (Env.ZERO);
			setName (null);
			setRate (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_HR_LoanType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_LoanType[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.eevolution.model.I_HR_Concept getHR_Concept() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Concept)MTable.get(getCtx(), org.eevolution.model.I_HR_Concept.Table_Name)
			.getPO(getHR_Concept_ID(), get_TrxName());	}

	/** Set Payroll Concept.
		@param HR_Concept_ID 
		The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public void setHR_Concept_ID (int HR_Concept_ID)
	{
		if (HR_Concept_ID < 1) 
			set_Value (COLUMNNAME_HR_Concept_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Concept_ID, Integer.valueOf(HR_Concept_ID));
	}

	/** Get Payroll Concept.
		@return The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public int getHR_Concept_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Concept_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set LoanType.
		@param HR_LoanType_ID LoanType	  */
	public void setHR_LoanType_ID (int HR_LoanType_ID)
	{
		if (HR_LoanType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LoanType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LoanType_ID, Integer.valueOf(HR_LoanType_ID));
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

	/** Set Max Amount.
		@param MaxAmt 
		Maximum Amount in invoice currency
	  */
	public void setMaxAmt (BigDecimal MaxAmt)
	{
		set_Value (COLUMNNAME_MaxAmt, MaxAmt);
	}

	/** Get Max Amount.
		@return Maximum Amount in invoice currency
	  */
	public BigDecimal getMaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Min Amount.
		@param MinAmt 
		Minimum Amount in invoice currency
	  */
	public void setMinAmt (BigDecimal MinAmt)
	{
		set_Value (COLUMNNAME_MinAmt, MinAmt);
	}

	/** Get Min Amount.
		@return Minimum Amount in invoice currency
	  */
	public BigDecimal getMinAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Rate %.
		@param Rate 
		Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate)
	{
		set_Value (COLUMNNAME_Rate, Rate);
	}

	/** Get Rate %.
		@return Rate or Tax or Exchange
	  */
	public BigDecimal getRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}