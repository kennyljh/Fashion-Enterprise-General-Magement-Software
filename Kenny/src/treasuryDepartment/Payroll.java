package treasuryDepartment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tEditor.PoorTextEditor;

/*
 * Information Expert that knows Employee and Rates
 */
public class Payroll implements PayrollInterface{

	// department name to filepath repository
	private Map<String, Object> employeeRepo = new HashMap<>();
	private String payrollData = null;
	
	PoorTextEditor editor = new PoorTextEditor();
	
	@Override
	public boolean addEmployeeData(String employeeData) {
		
		editor.processTextFile(employeeData);
		
		if (editor.getRepository().isEmpty()) {
			return false;
		}
		
		String[] temp = editor.getArrayNames();
		
		// storing department to filepath
		employeeRepo.put(editor.retrieveValue(temp[0], "department"), employeeData);
		System.out.println("Added Employee List for: " + temp[0]);
		return true;
	}

	@Override
	public boolean addPayrollRates(String payrollData) {

		editor.processTextFile(payrollData);
		
		if (editor.getRepository().isEmpty()) {
			return false;
		}
		
		String
		
		return true;
	}

	@Override
	public PayrollReport createPayroll(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payslip createPayslip(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEmployeeData(String employeeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PayrollApproval retrieveApproval(String reportID) {
		// TODO Auto-generated method stub
		return null;
	}




}
