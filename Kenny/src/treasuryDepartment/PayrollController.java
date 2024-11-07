package treasuryDepartment;

import java.util.Scanner;

/**
 * Stores an instance of Payroll that is used to
 * call the Payroll object methods.
 */
public class PayrollController implements PayrollControllerInterface{

	private PayrollController controller;
	
	private Payroll payroll = null;
	
	private PayrollController (PayrollController controller) {
		this.controller = controller;
	}
	
	public void run() {
		
		System.out.println("Welcome to the Payroll System");
		System.out.println("1. Load Department Employee Data List");
		System.out.println("2. Load Payroll Rates");
		System.out.println("3. Process Payroll");
		System.out.println("4. Exit program");
		
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		
		switch (choice) {
			
			case 1:
				System.out.println("Enter path of Department Employee Data List");
				String filePath = scan.nextLine();
				addEmployeeData(filePath);
				break;
			case 2:
				System.out.println("Enter ");
			case 3:
			
			case 4:
				System.out.println("Closing program...");
				break;
			default:
				System.out.println("Incorrect choice. Try again.");
				run();
		}
		scan.close();
	}

	@Override
	public boolean addEmployeeData(String filePath) {
		
		return this.getPayrollInstance().addEmployeeData(filePath);
	}

	@Override
	public boolean addPayrollRates(String filePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createReport(String filepath) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * To define one instance of the class Payroll to 
	 * call Payroll methods
	 * @return
	 */
	private Payroll getPayrollInstance() {
		
		if (payroll == null) {
			payroll = new Payroll();
		}
		return payroll;
	}
}
