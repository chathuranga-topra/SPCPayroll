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

/** Generated Model for I_HRImportOT
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_I_HRImportOT extends PO implements I_I_HRImportOT, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180905L;

    /** Standard Constructor */
    public X_I_HRImportOT (Properties ctx, int I_HRImportOT_ID, String trxName)
    {
      super (ctx, I_HRImportOT_ID, trxName);
      /** if (I_HRImportOT_ID == 0)
        {
			setBPValue (null);
			setI_HRImportOT_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_HRImportOT (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_HRImportOT[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BP Search Key.
		@param BPValue 
		Business Partner Key Value
	  */
	public void setBPValue (String BPValue)
	{
		set_Value (COLUMNNAME_BPValue, BPValue);
	}

	/** Get BP Search Key.
		@return Business Partner Key Value
	  */
	public String getBPValue () 
	{
		return (String)get_Value(COLUMNNAME_BPValue);
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

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set I_HRImportOT ID.
		@param I_HRImportOT_ID I_HRImportOT ID	  */
	public void setI_HRImportOT_ID (int I_HRImportOT_ID)
	{
		if (I_HRImportOT_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_HRImportOT_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_HRImportOT_ID, Integer.valueOf(I_HRImportOT_ID));
	}

	/** Get I_HRImportOT ID.
		@return I_HRImportOT ID	  */
	public int getI_HRImportOT_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_HRImportOT_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Meal Allowance.
		@param MealAllowance Meal Allowance	  */
	public void setMealAllowance (BigDecimal MealAllowance)
	{
		set_Value (COLUMNNAME_MealAllowance, MealAllowance);
	}

	/** Get Meal Allowance.
		@return Meal Allowance	  */
	public BigDecimal getMealAllowance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MealAllowance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set OTHours.
		@param OTHours OTHours	  */
	public void setOTHours (BigDecimal OTHours)
	{
		set_Value (COLUMNNAME_OTHours, OTHours);
	}

	/** Get OTHours.
		@return OTHours	  */
	public BigDecimal getOTHours () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OTHours);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set OTHoursDouble.
		@param OTHoursDouble OTHoursDouble	  */
	public void setOTHoursDouble (BigDecimal OTHoursDouble)
	{
		set_Value (COLUMNNAME_OTHoursDouble, OTHoursDouble);
	}

	/** Get OTHoursDouble.
		@return OTHoursDouble	  */
	public BigDecimal getOTHoursDouble () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OTHoursDouble);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}