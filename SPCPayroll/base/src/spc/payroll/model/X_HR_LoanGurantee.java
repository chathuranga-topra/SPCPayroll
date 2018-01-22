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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_LoanGurantee
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_LoanGurantee extends PO implements I_HR_LoanGurantee, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180422L;

    /** Standard Constructor */
    public X_HR_LoanGurantee (Properties ctx, int HR_LoanGurantee_ID, String trxName)
    {
      super (ctx, HR_LoanGurantee_ID, trxName);
      /** if (HR_LoanGurantee_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_LoanGurantee_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_LoanGurantee (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_LoanGurantee[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

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

	/** Set HR_LoanGurantee ID.
		@param HR_LoanGurantee_ID HR_LoanGurantee ID	  */
	public void setHR_LoanGurantee_ID (int HR_LoanGurantee_ID)
	{
		if (HR_LoanGurantee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LoanGurantee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LoanGurantee_ID, Integer.valueOf(HR_LoanGurantee_ID));
	}

	/** Get HR_LoanGurantee ID.
		@return HR_LoanGurantee ID	  */
	public int getHR_LoanGurantee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LoanGurantee_ID);
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
}