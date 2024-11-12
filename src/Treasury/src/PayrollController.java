/**
 * @author Kenny
 */

package src.Treasury.src;

import src.App;
import src.Treasury.src.interfaces.PayrollControllerInterface;

import java.util.Scanner;

/**
 * Stores an instance of Payroll that is used to
 * call the Payroll object methods.
 */
public class PayrollController implements PayrollControllerInterface {

	private PayrollController controller;
	
	private Payroll payroll = null;
	
	private PayrollController (PayrollController controller) {
		this.controller = controller;
	}
	
	public PayrollController() {};
	
	/**
	 * Run the payroll program
	 */
	public void run() throws Exception {
		
		System.out.println("Welcome to the Payroll System");
		
		
		Scanner scan = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {
			
			System.out.println("1. Load Department Employee Data List");
			System.out.println("2. Load Payroll Rates");
			System.out.println("3. Process Payroll & Payslips");
			System.out.println("4. Exit program");
			
			int choice = scan.nextInt();
			scan.nextLine();
			
			switch (choice) {
				
				case 1:
					System.out.println("Enter path of Department Employee Data File:");
					String filePath = scan.nextLine();
					if (addEmployeeData(filePath)) {;
						System.out.println("Department Employee Data added successfully\n");
					}
					else {
						System.out.println("Invalid Employee Data file\n");
					}
					break;
				case 2:
					System.out.println("Enter path of Payroll Rate File:");
					String filePath2 = scan.nextLine();
					if (addPayrollRates(filePath2)) {
						System.out.println("Payroll Rate added successfully\n");
					}
					else {
						System.out.println("Invalid Payroll Rate file\n");
					}
					break;
				case 3:
					System.out.println("Enter path to store Payroll Report & Payslips: (e.g. User\\Documents\\(files stored in here))");
					String filePath3 = scan.nextLine();
					if (processPayroll(filePath3)) {
						System.out.println("Payroll successfully processed and saved\n");
					}
					else {
						System.out.println("Payroll processing failed\n");
					}
					break;
				case 4:
					System.out.println("Closing program...");
					exit = true;
					App.prompt();

				default:
					System.out.println("Incorrect choice. Try again");
			}
		}
		scan.close();
	}

	@Override
	public boolean addEmployeeData(String filePath) {
		
		return this.getPayrollInstance().addEmployeeData(filePath);
	}

	@Override
	public boolean addPayrollRates(String filePath) {
		
		return this.getPayrollInstance().addPayrollRates(filePath);
	}

	@Override
	public boolean processPayroll(String filePath) {
		
		return this.getPayrollInstance().processPayroll(filePath);
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
