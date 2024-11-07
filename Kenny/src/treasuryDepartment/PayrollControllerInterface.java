package treasuryDepartment;

/**
 * Stores an instance of Payroll that is used to
 * call the Payroll object methods.
 */
public interface PayrollControllerInterface {

	boolean addEmployeeData(String filePath);
	
	boolean addPayrollRates(String filePath);
	
	boolean createReport(String filepath);
}
