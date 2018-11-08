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

/** Generated Model for HR_EmpBankAccount
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_EmpBankAccount extends PO implements I_HR_EmpBankAccount, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181108L;

    /** Standard Constructor */
    public X_HR_EmpBankAccount (Properties ctx, int HR_EmpBankAccount_ID, String trxName)
    {
      super (ctx, HR_EmpBankAccount_ID, trxName);
      /** if (HR_EmpBankAccount_ID == 0)
        {
			setAccountNo (null);
			setC_BPartner_ID (0);
			setHR_Bank_ID (0);
			setHR_EmpBankAccount_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_EmpBankAccount (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_EmpBankAccount[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account No.
		@param AccountNo 
		Account Number
	  */
	public void setAccountNo (String AccountNo)
	{
		set_Value (COLUMNNAME_AccountNo, AccountNo);
	}

	/** Get Account No.
		@return Account Number
	  */
	public String getAccountNo () 
	{
		return (String)get_Value(COLUMNNAME_AccountNo);
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

	public spc.payroll.model.I_HR_BankBranch getHR_BankBranch() throws RuntimeException
    {
		return (spc.payroll.model.I_HR_BankBranch)MTable.get(getCtx(), spc.payroll.model.I_HR_BankBranch.Table_Name)
			.getPO(getHR_BankBranch_ID(), get_TrxName());	}

	/** Set BankBranch.
		@param HR_BankBranch_ID BankBranch	  */
	public void setHR_BankBranch_ID (int HR_BankBranch_ID)
	{
		if (HR_BankBranch_ID < 1) 
			set_Value (COLUMNNAME_HR_BankBranch_ID, null);
		else 
			set_Value (COLUMNNAME_HR_BankBranch_ID, Integer.valueOf(HR_BankBranch_ID));
	}

	/** Get BankBranch.
		@return BankBranch	  */
	public int getHR_BankBranch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_BankBranch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public spc.payroll.model.I_HR_Bank getHR_Bank() throws RuntimeException
    {
		return (spc.payroll.model.I_HR_Bank)MTable.get(getCtx(), spc.payroll.model.I_HR_Bank.Table_Name)
			.getPO(getHR_Bank_ID(), get_TrxName());	}

	/** Set Bank ID.
		@param HR_Bank_ID Bank ID	  */
	public void setHR_Bank_ID (int HR_Bank_ID)
	{
		if (HR_Bank_ID < 1) 
			set_Value (COLUMNNAME_HR_Bank_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Bank_ID, Integer.valueOf(HR_Bank_ID));
	}

	/** Get Bank ID.
		@return Bank ID	  */
	public int getHR_Bank_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Bank_ID);
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

	/** Set HR_EmpBankAccount ID.
		@param HR_EmpBankAccount_ID HR_EmpBankAccount ID	  */
	public void setHR_EmpBankAccount_ID (int HR_EmpBankAccount_ID)
	{
		if (HR_EmpBankAccount_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_EmpBankAccount_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_EmpBankAccount_ID, Integer.valueOf(HR_EmpBankAccount_ID));
	}

	/** Get HR_EmpBankAccount ID.
		@return HR_EmpBankAccount ID	  */
	public int getHR_EmpBankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EmpBankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}