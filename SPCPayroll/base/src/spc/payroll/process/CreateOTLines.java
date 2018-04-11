package spc.payroll.process;

import org.compiere.process.SvrProcess;

public class CreateOTLines extends SvrProcess{

	@Override
	protected void prepare() {
		
		System.out.println("Hello Saman");
	}

	@Override
	protected String doIt() throws Exception {
		
		System.out.println("Hello Saman");
		return "Done!";
	}

}
