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

/** Generated Model for HR_LoanSchedule
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_LoanSchedule extends PO implements I_HR_LoanSchedule, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180802L;

    /** Standard Constructor */
    public X_HR_LoanSchedule (Properties ctx, int HR_LoanSchedule_ID, String trxName)
    {
      super (ctx, HR_LoanSchedule_ID, trxName);
      /** if (HR_LoanSchedule_ID == 0)
        {
			setCapitalAmt (Env.ZERO);
			setEffectiveFrom (new Timestamp( System.currentTimeMillis() ));
			setHR_LoanSchedule_ID (0);
			setHR_Loan_ID (0);
			setInterestAmt (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_HR_LoanSchedule (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_LoanSchedule[")
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

	public spc.payroll.model.I_HR_LoanEarllySettle getHR_LoanEarllySettle() throws RuntimeException
    {
		return (spc.payroll.model.I_HR_LoanEarllySettle)MTable.get(getCtx(), spc.payroll.model.I_HR_LoanEarllySettle.Table_Name)
			.getPO(getHR_LoanEarllySettle_ID(), get_TrxName());	}

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

	/** Set HR_LoanSchedule ID.
		@param HR_LoanSchedule_ID HR_LoanSchedule ID	  */
	public void setHR_LoanSchedule_ID (int HR_LoanSchedule_ID)
	{
		if (HR_LoanSchedule_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LoanSchedule_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LoanSchedule_ID, Integer.valueOf(HR_LoanSchedule_ID));
	}

	/** Get HR_LoanSchedule ID.
		@return HR_LoanSchedule ID	  */
	public int getHR_LoanSchedule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LoanSchedule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Loan ID.
		@param HR_Loan_ID HR_Loan ID	  */
	public void setHR_Loan_ID (int HR_Loan_ID)
	{
		if (HR_Loan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Loan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Loan_ID, Integer.valueOf(HR_Loan_ID));
	}

	/** Get HR_Loan ID.
		@return HR_Loan ID	  */
	public int getHR_Loan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Loan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Movement getHR_Movement() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Movement)MTable.get(getCtx(), org.eevolution.model.I_HR_Movement.Table_Name)
			.getPO(getHR_Movement_ID(), get_TrxName());	}

	/** Set Payroll Movement.
		@param HR_Movement_ID Payroll Movement	  */
	public void setHR_Movement_ID (int HR_Movement_ID)
	{
		if (HR_Movement_ID < 1) 
			set_Value (COLUMNNAME_HR_Movement_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Movement_ID, Integer.valueOf(HR_Movement_ID));
	}

	/** Get Payroll Movement.
		@return Payroll Movement	  */
	public int getHR_Movement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Movement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interest Amount.
		@param InterestAmt 
		Interest Amount
	  */
	public void setInterestAmt (BigDecimal InterestAmt)
	{
		set_Value (COLUMNNAME_InterestAmt, InterestAmt);
	}

	/** Get Interest Amount.
		@return Interest Amount
	  */
	public BigDecimal getInterestAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InterestAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Interest Movement.
		@param InterestMovement_ID Interest Movement	  */
	public void setInterestMovement_ID (int InterestMovement_ID)
	{
		if (InterestMovement_ID < 1) 
			set_Value (COLUMNNAME_InterestMovement_ID, null);
		else 
			set_Value (COLUMNNAME_InterestMovement_ID, Integer.valueOf(InterestMovement_ID));
	}

	/** Get Interest Movement.
		@return Interest Movement	  */
	public int getInterestMovement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_InterestMovement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Paid.
		@param IsPaid 
		The document is paid
	  */
	public void setIsPaid (boolean IsPaid)
	{
		set_Value (COLUMNNAME_IsPaid, Boolean.valueOf(IsPaid));
	}

	/** Get Paid.
		@return The document is paid
	  */
	public boolean isPaid () 
	{
		Object oo = get_Value(COLUMNNAME_IsPaid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Migrated.
		@param Migrated Migrated	  */
	public void setMigrated (boolean Migrated)
	{
		set_Value (COLUMNNAME_Migrated, Boolean.valueOf(Migrated));
	}

	/** Get Migrated.
		@return Migrated	  */
	public boolean isMigrated () 
	{
		Object oo = get_Value(COLUMNNAME_Migrated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Payment Date.
		@param PayDate 
		Date Payment made
	  */
	public void setPayDate (Timestamp PayDate)
	{
		set_Value (COLUMNNAME_PayDate, PayDate);
	}

	/** Get Payment Date.
		@return Date Payment made
	  */
	public Timestamp getPayDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PayDate);
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