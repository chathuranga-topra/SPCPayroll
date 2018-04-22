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

/** Generated Model for HR_LoanTypeFactors
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_LoanTypeFactors extends PO implements I_HR_LoanTypeFactors, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180420L;

    /** Standard Constructor */
    public X_HR_LoanTypeFactors (Properties ctx, int HR_LoanTypeFactors_ID, String trxName)
    {
      super (ctx, HR_LoanTypeFactors_ID, trxName);
      /** if (HR_LoanTypeFactors_ID == 0)
        {
			setHR_Concept_ID (0);
			setHR_LoanTypeFactors_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_LoanTypeFactors (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_LoanTypeFactors[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set HR_LoanTypeFactors ID.
		@param HR_LoanTypeFactors_ID HR_LoanTypeFactors ID	  */
	public void setHR_LoanTypeFactors_ID (int HR_LoanTypeFactors_ID)
	{
		if (HR_LoanTypeFactors_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LoanTypeFactors_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LoanTypeFactors_ID, Integer.valueOf(HR_LoanTypeFactors_ID));
	}

	/** Get HR_LoanTypeFactors ID.
		@return HR_LoanTypeFactors ID	  */
	public int getHR_LoanTypeFactors_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LoanTypeFactors_ID);
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

	/** Set Percentage %.
		@param Percentage 
		Percent of the entire amount
	  */
	public void setPercentage (BigDecimal Percentage)
	{
		set_Value (COLUMNNAME_Percentage, Percentage);
	}

	/** Get Percentage %.
		@return Percent of the entire amount
	  */
	public BigDecimal getPercentage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Percentage);
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