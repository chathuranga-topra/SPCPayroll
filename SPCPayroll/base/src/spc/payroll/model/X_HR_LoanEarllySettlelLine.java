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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_LoanEarllySettlelLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_LoanEarllySettlelLine extends PO implements I_HR_LoanEarllySettlelLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180404L;

    /** Standard Constructor */
    public X_HR_LoanEarllySettlelLine (Properties ctx, int HR_LoanEarllySettlelLine_ID, String trxName)
    {
      super (ctx, HR_LoanEarllySettlelLine_ID, trxName);
      /** if (HR_LoanEarllySettlelLine_ID == 0)
        {
			setCapitalAmt (Env.ZERO);
			setEffectiveFrom (new Timestamp( System.currentTimeMillis() ));
			setHR_LoanEarllySettlelLine_ID (0);
			setOriginalInterest (Env.ZERO);
			setRemainingDays (0);
			setRevisedInterest (Env.ZERO);
			setSeqNo (0);
        } */
    }

    /** Load Constructor */
    public X_HR_LoanEarllySettlelLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_LoanEarllySettlelLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Capital Amount.
		@param CapitalAmt Capital Amount	  */
	public void setCapitalAmt (BigDecimal CapitalAmt)
	{
		set_Value (COLUMNNAME_CapitalAmt, CapitalAmt);
	}

	/** Get Capital Amount.
		@return Capital Amount	  */
	public BigDecimal getCapitalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CapitalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set EffectiveFrom.
		@param EffectiveFrom EffectiveFrom	  */
	public void setEffectiveFrom (Timestamp EffectiveFrom)
	{
		set_Value (COLUMNNAME_EffectiveFrom, EffectiveFrom);
	}

	/** Get EffectiveFrom.
		@return EffectiveFrom	  */
	public Timestamp getEffectiveFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EffectiveFrom);
	}

	/** Set Earlly Settlement.
		@param HR_LoanEarllySettle_ID Earlly Settlement	  */
	public void setHR_LoanEarllySettle_ID (int HR_LoanEarllySettle_ID)
	{
		if (HR_LoanEarllySettle_ID < 1) 
			set_Value (COLUMNNAME_HR_LoanEarllySettle_ID, null);
		else 
			set_Value (COLUMNNAME_HR_LoanEarllySettle_ID, Integer.valueOf(HR_LoanEarllySettle_ID));
	}

	/** Get Earlly Settlement.
		@return Earlly Settlement	  */
	public int getHR_LoanEarllySettle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LoanEarllySettle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_LoanEarllySettlelLine ID.
		@param HR_LoanEarllySettlelLine_ID HR_LoanEarllySettlelLine ID	  */
	public void setHR_LoanEarllySettlelLine_ID (int HR_LoanEarllySettlelLine_ID)
	{
		if (HR_LoanEarllySettlelLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LoanEarllySettlelLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LoanEarllySettlelLine_ID, Integer.valueOf(HR_LoanEarllySettlelLine_ID));
	}

	/** Get HR_LoanEarllySettlelLine ID.
		@return HR_LoanEarllySettlelLine ID	  */
	public int getHR_LoanEarllySettlelLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LoanEarllySettlelLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Original Interest.
		@param OriginalInterest Original Interest	  */
	public void setOriginalInterest (BigDecimal OriginalInterest)
	{
		set_Value (COLUMNNAME_OriginalInterest, OriginalInterest);
	}

	/** Get Original Interest.
		@return Original Interest	  */
	public BigDecimal getOriginalInterest () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OriginalInterest);
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

	/** Set Remaining Days.
		@param RemainingDays Remaining Days	  */
	public void setRemainingDays (int RemainingDays)
	{
		set_Value (COLUMNNAME_RemainingDays, Integer.valueOf(RemainingDays));
	}

	/** Get Remaining Days.
		@return Remaining Days	  */
	public int getRemainingDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RemainingDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Revised Interest.
		@param RevisedInterest Revised Interest	  */
	public void setRevisedInterest (BigDecimal RevisedInterest)
	{
		set_Value (COLUMNNAME_RevisedInterest, RevisedInterest);
	}

	/** Get Revised Interest.
		@return Revised Interest	  */
	public BigDecimal getRevisedInterest () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RevisedInterest);
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