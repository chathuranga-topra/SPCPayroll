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

/** Generated Model for HR_LoanFourtyPresent
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_LoanFourtyPresent extends PO implements I_HR_LoanFourtyPresent, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180422L;

    /** Standard Constructor */
    public X_HR_LoanFourtyPresent (Properties ctx, int HR_LoanFourtyPresent_ID, String trxName)
    {
      super (ctx, HR_LoanFourtyPresent_ID, trxName);
      /** if (HR_LoanFourtyPresent_ID == 0)
        {
			setHR_LoanFourtyPresent_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_LoanFourtyPresent (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_LoanFourtyPresent[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public org.eevolution.model.I_HR_Concept_Category getHR_Concept_Category() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Concept_Category)MTable.get(getCtx(), org.eevolution.model.I_HR_Concept_Category.Table_Name)
			.getPO(getHR_Concept_Category_ID(), get_TrxName());	}

	/** Set Concept Category.
		@param HR_Concept_Category_ID 
		Global Payroll Concept Category allows to grouping of Global Concept to reports and queries
	  */
	public void setHR_Concept_Category_ID (int HR_Concept_Category_ID)
	{
		if (HR_Concept_Category_ID < 1) 
			set_Value (COLUMNNAME_HR_Concept_Category_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Concept_Category_ID, Integer.valueOf(HR_Concept_Category_ID));
	}

	/** Get Concept Category.
		@return Global Payroll Concept Category allows to grouping of Global Concept to reports and queries
	  */
	public int getHR_Concept_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Concept_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_LoanFourtyPresent ID.
		@param HR_LoanFourtyPresent_ID HR_LoanFourtyPresent ID	  */
	public void setHR_LoanFourtyPresent_ID (int HR_LoanFourtyPresent_ID)
	{
		if (HR_LoanFourtyPresent_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LoanFourtyPresent_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LoanFourtyPresent_ID, Integer.valueOf(HR_LoanFourtyPresent_ID));
	}

	/** Get HR_LoanFourtyPresent ID.
		@return HR_LoanFourtyPresent ID	  */
	public int getHR_LoanFourtyPresent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LoanFourtyPresent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Loan ID.
		@param HR_Loan_ID HR_Loan ID	  */
	public void setHR_Loan_ID (int HR_Loan_ID)
	{
		if (HR_Loan_ID < 1) 
			set_Value (COLUMNNAME_HR_Loan_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Loan_ID, Integer.valueOf(HR_Loan_ID));
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