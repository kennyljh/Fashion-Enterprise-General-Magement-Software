/**
 * @author Kenny
 */

package treasuryDepartment;

public interface PayrollInterface {

	boolean addEmployeeData(String filePath);
	
	boolean addPayrollRates(String filePath);
	
	boolean processPayroll(String fileDirectory);
	
	void createPayroll(String fileDirectory);
	
	void createPayslip(String fileDirectory);
	
	PayrollApproval retrieveApproval(String reportID);
}
