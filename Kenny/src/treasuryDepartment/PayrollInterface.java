package treasuryDepartment;

public interface PayrollInterface {

	boolean addEmployeeData(String filePath);
	
	boolean addPayrollRates(Payroll payroll);
	
	PayrollReport createPayroll(Employee employee);
	
	Payslip createPayslip(Employee employee);
	
	boolean deleteEmployeeData(String employeeId);
	
	PayrollApproval retrieveApproval(String reportID);
}
