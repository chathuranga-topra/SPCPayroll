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

/** Generated Model for HR_NoPayAttribute
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_NoPayAttribute extends PO implements I_HR_NoPayAttribute, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180427L;

    /** Standard Constructor */
    public X_HR_NoPayAttribute (Properties ctx, int HR_NoPayAttribute_ID, String trxName)
    {
      super (ctx, HR_NoPayAttribute_ID, trxName);
      /** if (HR_NoPayAttribute_ID == 0)
        {
			setHR_Attribute_ID (0);
			setHR_Concept_ID (0);
			setHR_NoPayAttribute_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_NoPayAttribute (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_NoPayAttribute[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Balance.
		@param Balance Balance	  */
	public void setBalance (BigDecimal Balance)
	{
		set_Value (COLUMNNAME_Balance, Balance);
	}

	/** Get Balance.
		@return Balance	  */
	public BigDecimal getBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Balance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Base Amt.
		@param BaseAmt Base Amt	  */
	public void setBaseAmt (BigDecimal BaseAmt)
	{
		set_Value (COLUMNNAME_BaseAmt, BaseAmt);
	}

	/** Get Base Amt.
		@return Base Amt	  */
	public BigDecimal getBaseAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BaseAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day Rate.
		@param DayRate Day Rate	  */
	public void setDayRate (BigDecimal DayRate)
	{
		set_Value (COLUMNNAME_DayRate, DayRate);
	}

	/** Get Day Rate.
		@return Day Rate	  */
	public BigDecimal getDayRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DayRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Deduction.
		@param Deduction Deduction	  */
	public void setDeduction (BigDecimal Deduction)
	{
		set_Value (COLUMNNAME_Deduction, Deduction);
	}

	/** Get Deduction.
		@return Deduction	  */
	public BigDecimal getDeduction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Deduction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set HR_NoPayAttribute ID.
		@param HR_NoPayAttribute_ID HR_NoPayAttribute ID	  */
	public void setHR_NoPayAttribute_ID (int HR_NoPayAttribute_ID)
	{
		if (HR_NoPayAttribute_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_NoPayAttribute_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_NoPayAttribute_ID, Integer.valueOf(HR_NoPayAttribute_ID));
	}

	/** Get HR_NoPayAttribute ID.
		@return HR_NoPayAttribute ID	  */
	public int getHR_NoPayAttribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_NoPayAttribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_NoPayLine ID.
		@param HR_NoPayLine_ID HR_NoPayLine ID	  */
	public void setHR_NoPayLine_ID (int HR_NoPayLine_ID)
	{
		if (HR_NoPayLine_ID < 1) 
			set_Value (COLUMNNAME_HR_NoPayLine_ID, null);
		else 
			set_Value (COLUMNNAME_HR_NoPayLine_ID, Integer.valueOf(HR_NoPayLine_ID));
	}

	/** Get HR_NoPayLine ID.
		@return HR_NoPayLine ID	  */
	public int getHR_NoPayLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_NoPayLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set No of Days.
		@param NoOfDays No of Days	  */
	public void setNoOfDays (BigDecimal NoOfDays)
	{
		set_Value (COLUMNNAME_NoOfDays, NoOfDays);
	}

	/** Get No of Days.
		@return No of Days	  */
	public BigDecimal getNoOfDays () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NoOfDays);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.eevolution.model.I_HR_Concept getNoPayConcept() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Concept)MTable.get(getCtx(), org.eevolution.model.I_HR_Concept.Table_Name)
			.getPO(getNoPayConcept_ID(), get_TrxName());	}

	/** Set No Pay Concept.
		@param NoPayConcept_ID No Pay Concept	  */
	public void setNoPayConcept_ID (int NoPayConcept_ID)
	{
		if (NoPayConcept_ID < 1) 
			set_Value (COLUMNNAME_NoPayConcept_ID, null);
		else 
			set_Value (COLUMNNAME_NoPayConcept_ID, Integer.valueOf(NoPayConcept_ID));
	}

	/** Get No Pay Concept.
		@return No Pay Concept	  */
	public int getNoPayConcept_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NoPayConcept_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}