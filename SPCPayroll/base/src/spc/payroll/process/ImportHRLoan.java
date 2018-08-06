package spc.payroll.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.compiere.model.MBPartner;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.eevolution.model.MHRAttribute;

import spc.payroll.model.MHRLoan;
import spc.payroll.model.MHRLoanSchedule;
import spc.payroll.model.X_HR_LoanGurantee;
import spc.payroll.model.X_I_HRBankLoan;

//spc.payroll.process.ImportHRLoan
public class ImportHRLoan extends SvrProcess{

	@Override
	protected void prepare() { }
	
	@Override
	protected String doIt() throws Exception {
		
		PreparedStatement psmt =  null;
		ResultSet rs = null;
		X_I_HRBankLoan xLoan = null;
		MHRLoan loan = null;
		MBPartner bPartner = null;
		
		String sql = "SELECT * FROM I_HRBankLoan WHERE I_IsImported = 'N'";
				//+ "AND gepf1 is not null";
		
		try
		{
			psmt = DB.prepareStatement(sql, get_TrxName());
			rs = psmt.executeQuery();
			
			int installmentCount = 0;
			MHRLoanSchedule sc = null;Date dateGranted = null; Calendar cal = null;
			BigDecimal ir = null;double monthCapital , interest = 0.00;Date d = null;
			X_HR_LoanGurantee lg = null;
			int installmentpaidCount = 0;
			
			while(rs.next()) {
				
				try{
					xLoan = new X_I_HRBankLoan(getCtx(), rs, get_TrxName());
					
					bPartner = MBPartner.get(getCtx(), xLoan.getBPValue()); 
					if(bPartner == null) {
						xLoan.setDescription("No Bpartner!");
						xLoan.save();
						
						continue;
					}
					
					loan = new MHRLoan(getCtx(), 0, get_TrxName());
					loan.setC_BPartner_ID(bPartner.get_ID());
					loan.setHR_LoanType_ID(xLoan.getHR_LoanType_ID());
					loan.setGrantedDate(xLoan.getGrantedDate());
					loan.setPayrollEffectiveDate(xLoan.getEffectiveFrom());
					loan.setLoanAmount(xLoan.getLoanAmount());
					loan.setInstallmentCount(xLoan.getInstallmentCount());
					
					loan.setDocStatus("DR");
					loan.setDocAction("PR");
					loan.save();
					
					
					installmentCount = xLoan.getInstallmentCount();
					
					dateGranted  = new Date(xLoan.getGrantedDate().getTime());
					cal = Calendar.getInstance();
					cal.setTime(dateGranted);
					
					
					ir = new BigDecimal(7);
					monthCapital = xLoan.getCapitalAmt().doubleValue();  
					interest = xLoan.getInterestAmt().doubleValue();
					
					cal.setTime(dateGranted);
					
					//set loan payroll applicable date :: always next month 25 th
					cal.add(Calendar.MONTH, 1);
					d = new Date(cal.getTime().getTime());
					d.setDate(1);
					cal.setTime(d);
					
					installmentpaidCount = (xLoan.getLoanAmount().subtract(xLoan.getBalance()))
							.divide(xLoan.getCapitalAmt()).intValue();
					
					for(int i = 0;i < installmentCount;i++){
						
						
						sc = new MHRLoanSchedule(getCtx(), 0, get_TrxName());
						
						sc.setCapitalAmt(new BigDecimal(monthCapital).setScale(0, RoundingMode.HALF_UP));
						sc.setInterestAmt(new BigDecimal(interest).setScale(2, RoundingMode.HALF_UP));
						sc.setHR_Loan_ID(loan.get_ID());
						sc.setSeqNo(i+1);
						//set effective date for loan premium applicable for
						cal.setTime(d);
				        cal.add(Calendar.MONTH, i);
				        sc.setEffectiveFrom(new Timestamp(cal.getTime().getTime()));
				        sc.setIsPaid(i + 1 <= installmentpaidCount);
				        sc.setProcessed(true);
				        sc.setMigrated(true);
				        sc.save();
					}
					
					//set guarantees 1
					if(xLoan.getgepf1() !=null && xLoan.getgepf1().length() > 0){
						bPartner = MBPartner.get(getCtx(), xLoan.getgepf1());
						if(bPartner!=null) {
							lg = new X_HR_LoanGurantee(getCtx(), 0, get_TrxName());
							lg.setHR_Loan_ID(loan.get_ID());
							lg.setC_BPartner_ID(bPartner.get_ID());
							lg.setProcessed(true);
							lg.save();
						}
					}
					
					//set guarantees 1
					if(xLoan.getgepf2() !=null && xLoan.getgepf2().length() > 0){
						bPartner = MBPartner.get(getCtx(), xLoan.getgepf2());
						
						if(bPartner!=null) {
							lg = new X_HR_LoanGurantee(getCtx(), 0, get_TrxName());
							lg.setHR_Loan_ID(loan.get_ID());
							lg.setC_BPartner_ID(bPartner.get_ID());
							
							lg.setProcessed(true);
							lg.save();
						}
						
					}
					
					//create a concept atribute for particulart employee
					//If interest for separate concept
					if(loan.getHR_LoanType().getInterestConcept_ID() > 0){
						MHRAttribute inte = new MHRAttribute(this.getCtx() , 0 , this.get_TrxName());
						inte = new MHRAttribute(this.getCtx() , 0 , this.get_TrxName());
						inte.setHR_Concept_ID(loan.getHR_LoanType().getInterestConcept_ID());
						inte.setValidFrom(loan.getPayrollEffectiveDate());
						inte.setC_BPartner_ID(loan.getC_BPartner_ID());
						inte.setColumnType(loan.getHR_LoanType().getHR_Concept().getColumnType());
						inte.set_ValueOfColumn("processed", "Y");
						inte.save();
						//set interest atribute
						loan.setInterestAttribute_ID(inte.get_ID());
					}
					
					//capital
					MHRAttribute atribute = new MHRAttribute(this.getCtx() , 0 , this.get_TrxName());
					atribute = new MHRAttribute(this.getCtx() , 0 , this.get_TrxName());
					atribute.setHR_Concept_ID(loan.getHR_LoanType().getHR_Concept_ID());
					atribute.setValidFrom(loan.getPayrollEffectiveDate());
					atribute.setC_BPartner_ID(loan.getC_BPartner_ID());
					atribute.setColumnType(loan.getHR_LoanType().getHR_Concept().getColumnType());
					atribute.set_ValueOfColumn("processed", "Y");
					atribute.save();
					
					//set capitle atribute
					loan.setHR_Attribute_ID(atribute.get_ID());
					
					loan.save();
					
					xLoan.setI_IsImported(true);
					xLoan.save();
				}catch(Exception ex) {
					xLoan.setDescription(ex.getMessage());
					xLoan.setI_IsImported(false);
					xLoan.save();
					
				}
				
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		return null;
	}

}
