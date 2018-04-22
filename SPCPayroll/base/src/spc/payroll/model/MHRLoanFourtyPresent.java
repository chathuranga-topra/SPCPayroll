package spc.payroll.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MHRLoanFourtyPresent extends X_HR_LoanFourtyPresent{

	public MHRLoanFourtyPresent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRLoanFourtyPresent(Properties ctx, int HR_LoanFourtyPresent_ID, String trxName) {
		super(ctx, HR_LoanFourtyPresent_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/*protected boolean beforeSave(boolean newRecord){
		
		return true;
	}*/
	
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//set HR Loan balances
		BigDecimal amt = new BigDecimal(0);
		MHRLoan loan = new MHRLoan(getCtx(), getHR_Loan_ID(), get_TrxName());
		String sql = "";
		
		//40 present earnings
		sql = "select NVL(sum(amount) , 0) from HR_LoanFourtyPresent where hr_loan_ID = ? "
			+ "and hr_concept_category_id = 1000000";
		amt = DB.getSQLValueBD(get_TrxName(), sql, this.getHR_Loan_ID());
		
		loan.setFourtyPresent(amt);
		
		//set total deduction
		sql = "select NVL(sum(amount) , 0)  from HR_LoanFourtyPresent where hr_loan_ID = ? "
			+ "and hr_concept_category_id = 1000001";
		amt = DB.getSQLValueBD(get_TrxName(), sql, this.getHR_Loan_ID());
		
		loan.setTotalDeduction(amt);
		
		//total earning
		sql = "SELECT NVL(sum(hr_movement.amount) , 0)  FROM HR_LoanFourtyPresent inner join hr_movement on " + 
			"HR_LoanFourtyPresent.hr_movement_id = hr_movement.hr_movement_id  where hr_loan_id = ? " + 
			"and hr_movement.hr_concept_category_id =  ?";
		
		amt = DB.getSQLValueBD(get_TrxName(), sql, this.getHR_Loan_ID() , 1000000);
		
		loan.setTotalEarnings(amt);
		
		loan.save();
		
		return success;
	}
}
