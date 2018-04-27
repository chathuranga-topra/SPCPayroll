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

/** Generated Model for HR_NoPayLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_NoPayLine extends PO implements I_HR_NoPayLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180427L;

    /** Standard Constructor */
    public X_HR_NoPayLine (Properties ctx, int HR_NoPayLine_ID, String trxName)
    {
      super (ctx, HR_NoPayLine_ID, trxName);
      /** if (HR_NoPayLine_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_NoPayLine_ID (0);
			setNoOfDays (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_HR_NoPayLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_NoPayLine[")
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

	/** Set HR_NoPayLine ID.
		@param HR_NoPayLine_ID HR_NoPayLine ID	  */
	public void setHR_NoPayLine_ID (int HR_NoPayLine_ID)
	{
		if (HR_NoPayLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_NoPayLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_NoPayLine_ID, Integer.valueOf(HR_NoPayLine_ID));
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

	/** Set NoPay ID.
		@param HR_NoPay_ID NoPay ID	  */
	public void setHR_NoPay_ID (int HR_NoPay_ID)
	{
		if (HR_NoPay_ID < 1) 
			set_Value (COLUMNNAME_HR_NoPay_ID, null);
		else 
			set_Value (COLUMNNAME_HR_NoPay_ID, Integer.valueOf(HR_NoPay_ID));
	}

	/** Get NoPay ID.
		@return NoPay ID	  */
	public int getHR_NoPay_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_NoPay_ID);
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

	/** Set Rate Per Day.
		@param RatePerDay Rate Per Day	  */
	public void setRatePerDay (BigDecimal RatePerDay)
	{
		set_Value (COLUMNNAME_RatePerDay, RatePerDay);
	}

	/** Get Rate Per Day.
		@return Rate Per Day	  */
	public BigDecimal getRatePerDay () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RatePerDay);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}