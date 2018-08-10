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

/** Generated Model for HR_OTLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_OTLine extends PO implements I_HR_OTLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180810L;

    /** Standard Constructor */
    public X_HR_OTLine (Properties ctx, int HR_OTLine_ID, String trxName)
    {
      super (ctx, HR_OTLine_ID, trxName);
      /** if (HR_OTLine_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_OTLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_OTLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_OTLine[")
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

	/** Set DoubleOTAttribute_ID.
		@param DoubleOTAttribute_ID DoubleOTAttribute_ID	  */
	public void setDoubleOTAttribute_ID (int DoubleOTAttribute_ID)
	{
		if (DoubleOTAttribute_ID < 1) 
			set_Value (COLUMNNAME_DoubleOTAttribute_ID, null);
		else 
			set_Value (COLUMNNAME_DoubleOTAttribute_ID, Integer.valueOf(DoubleOTAttribute_ID));
	}

	/** Get DoubleOTAttribute_ID.
		@return DoubleOTAttribute_ID	  */
	public int getDoubleOTAttribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DoubleOTAttribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_OTLine ID.
		@param HR_OTLine_ID HR_OTLine ID	  */
	public void setHR_OTLine_ID (int HR_OTLine_ID)
	{
		if (HR_OTLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_OTLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_OTLine_ID, Integer.valueOf(HR_OTLine_ID));
	}

	/** Get HR_OTLine ID.
		@return HR_OTLine ID	  */
	public int getHR_OTLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_OTLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_OT ID.
		@param HR_OT_ID HR_OT ID	  */
	public void setHR_OT_ID (int HR_OT_ID)
	{
		if (HR_OT_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_OT_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_OT_ID, Integer.valueOf(HR_OT_ID));
	}

	/** Get HR_OT ID.
		@return HR_OT ID	  */
	public int getHR_OT_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_OT_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Meal.
		@param IsMeal Meal	  */
	public void setIsMeal (boolean IsMeal)
	{
		set_Value (COLUMNNAME_IsMeal, Boolean.valueOf(IsMeal));
	}

	/** Get Meal.
		@return Meal	  */
	public boolean isMeal () 
	{
		Object oo = get_Value(COLUMNNAME_IsMeal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line Total.
		@param LineTotalAmt 
		Total line amount incl. Tax
	  */
	public void setLineTotalAmt (BigDecimal LineTotalAmt)
	{
		set_Value (COLUMNNAME_LineTotalAmt, LineTotalAmt);
	}

	/** Get Line Total.
		@return Total line amount incl. Tax
	  */
	public BigDecimal getLineTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineTotalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set MealAtrribute_ID.
		@param MealAtrribute_ID MealAtrribute_ID	  */
	public void setMealAtrribute_ID (int MealAtrribute_ID)
	{
		if (MealAtrribute_ID < 1) 
			set_Value (COLUMNNAME_MealAtrribute_ID, null);
		else 
			set_Value (COLUMNNAME_MealAtrribute_ID, Integer.valueOf(MealAtrribute_ID));
	}

	/** Get MealAtrribute_ID.
		@return MealAtrribute_ID	  */
	public int getMealAtrribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MealAtrribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set OTAtrribute_ID.
		@param OTAtrribute_ID OTAtrribute_ID	  */
	public void setOTAtrribute_ID (int OTAtrribute_ID)
	{
		if (OTAtrribute_ID < 1) 
			set_Value (COLUMNNAME_OTAtrribute_ID, null);
		else 
			set_Value (COLUMNNAME_OTAtrribute_ID, Integer.valueOf(OTAtrribute_ID));
	}

	/** Get OTAtrribute_ID.
		@return OTAtrribute_ID	  */
	public int getOTAtrribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OTAtrribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Rate.
		@param Rate 
		Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate)
	{
		set_Value (COLUMNNAME_Rate, Rate);
	}

	/** Get Rate.
		@return Rate or Tax or Exchange
	  */
	public BigDecimal getRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RateDouble.
		@param RateDouble RateDouble	  */
	public void setRateDouble (BigDecimal RateDouble)
	{
		set_Value (COLUMNNAME_RateDouble, RateDouble);
	}

	/** Get RateDouble.
		@return RateDouble	  */
	public BigDecimal getRateDouble () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RateDouble);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Remarks.
		@param Remarks 
		Remarks
	  */
	public void setRemarks (String Remarks)
	{
		set_Value (COLUMNNAME_Remarks, Remarks);
	}

	/** Get Remarks.
		@return Remarks
	  */
	public String getRemarks () 
	{
		return (String)get_Value(COLUMNNAME_Remarks);
	}

	/** Set TotalOTAmt.
		@param TotalOTAmt TotalOTAmt	  */
	public void setTotalOTAmt (BigDecimal TotalOTAmt)
	{
		set_Value (COLUMNNAME_TotalOTAmt, TotalOTAmt);
	}

	/** Get TotalOTAmt.
		@return TotalOTAmt	  */
	public BigDecimal getTotalOTAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalOTAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set TotalOTAmtDouble.
		@param TotalOTAmtDouble TotalOTAmtDouble	  */
	public void setTotalOTAmtDouble (BigDecimal TotalOTAmtDouble)
	{
		set_Value (COLUMNNAME_TotalOTAmtDouble, TotalOTAmtDouble);
	}

	/** Get TotalOTAmtDouble.
		@return TotalOTAmtDouble	  */
	public BigDecimal getTotalOTAmtDouble () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalOTAmtDouble);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}