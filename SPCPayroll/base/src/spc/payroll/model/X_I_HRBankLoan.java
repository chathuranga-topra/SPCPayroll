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

/** Generated Model for I_HRBankLoan
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_I_HRBankLoan extends PO implements I_I_HRBankLoan, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180802L;

    /** Standard Constructor */
    public X_I_HRBankLoan (Properties ctx, int I_HRBankLoan_ID, String trxName)
    {
      super (ctx, I_HRBankLoan_ID, trxName);
      /** if (I_HRBankLoan_ID == 0)
        {
			setI_HRBankLoan_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_HRBankLoan (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_HRBankLoan[")
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

	/** Set GrantedDate.
		@param GrantedDate GrantedDate	  */
	public void setGrantedDate (Timestamp GrantedDate)
	{
		set_Value (COLUMNNAME_GrantedDate, GrantedDate);
	}

	/** Get GrantedDate.
		@return GrantedDate	  */
	public Timestamp getGrantedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_GrantedDate);
	}

	public spc.payroll.model.I_HR_LoanType getHR_LoanType() throws RuntimeException
    {
		return (spc.payroll.model.I_HR_LoanType)MTable.get(getCtx(), spc.payroll.model.I_HR_LoanType.Table_Name)
			.getPO(getHR_LoanType_ID(), get_TrxName());	}

	/** Set LoanType.
		@param HR_LoanType_ID LoanType	  */
	public void setHR_LoanType_ID (int HR_LoanType_ID)
	{
		if (HR_LoanType_ID < 1) 
			set_Value (COLUMNNAME_HR_LoanType_ID, null);
		else 
			set_Value (COLUMNNAME_HR_LoanType_ID, Integer.valueOf(HR_LoanType_ID));
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

	/** Set I_HRBankLoan ID.
		@param I_HRBankLoan_ID I_HRBankLoan ID	  */
	public void setI_HRBankLoan_ID (int I_HRBankLoan_ID)
	{
		if (I_HRBankLoan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_HRBankLoan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_HRBankLoan_ID, Integer.valueOf(I_HRBankLoan_ID));
	}

	/** Get I_HRBankLoan ID.
		@return I_HRBankLoan ID	  */
	public int getI_HRBankLoan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_HRBankLoan_ID);
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

	/** Set Installment Count.
		@param InstallmentCount Installment Count	  */
	public void setInstallmentCount (int InstallmentCount)
	{
		set_Value (COLUMNNAME_InstallmentCount, Integer.valueOf(InstallmentCount));
	}

	/** Get Installment Count.
		@return Installment Count	  */
	public int getInstallmentCount () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_InstallmentCount);
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

	/** Set LoanAmount.
		@param LoanAmount LoanAmount	  */
	public void setLoanAmount (BigDecimal LoanAmount)
	{
		set_Value (COLUMNNAME_LoanAmount, LoanAmount);
	}

	/** Get LoanAmount.
		@return LoanAmount	  */
	public BigDecimal getLoanAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LoanAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set gepf1.
		@param gepf1 
		Alphanumeric identifier of the entity
	  */
	public void setgepf1 (String gepf1)
	{
		set_Value (COLUMNNAME_gepf1, gepf1);
	}

	/** Get gepf1.
		@return Alphanumeric identifier of the entity
	  */
	public String getgepf1 () 
	{
		return (String)get_Value(COLUMNNAME_gepf1);
	}

	/** Set gepf2.
		@param gepf2 
		Alphanumeric identifier of the entity
	  */
	public void setgepf2 (String gepf2)
	{
		set_Value (COLUMNNAME_gepf2, gepf2);
	}

	/** Get gepf2.
		@return Alphanumeric identifier of the entity
	  */
	public String getgepf2 () 
	{
		return (String)get_Value(COLUMNNAME_gepf2);
	}
}