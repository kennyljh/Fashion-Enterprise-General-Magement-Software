/**
 * @author Kenny
 */

package src.Treasury.src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.Treasury.src.interfaces.PayrollInterface;
import src.TextEditor.PoorTextEditor;

/*
 * Information Expert that knows Employee and Rates
 */
public class Payroll implements PayrollInterface {

	// department name to filepath repository
	private ArrayList<Employee> employeeRepo = new ArrayList<Employee>();
	private Map<String, EmployeeDescription> employeeDescriptionRepo = new HashMap<>();
	private String payrollData = null;
	private Map<String, PayrollReport> payrollReportRepo = new HashMap<>();
	private Map<String, PayrollDescription> payrollDescriptionRepo = new HashMap<>();

	private double totalTaxDeductions, totalRetirementDeductions;
	
	PoorTextEditor editor = new PoorTextEditor();

	@Override
	public boolean addEmployeeData(String employeeData) {

		editor.processTextFile(employeeData);

		if (editor.getRepository().isEmpty()) {
			return false;
		}

		String[] temp = editor.getArrayNames();
		for (String s : temp) {

			// storing employees to repo
			Employee employee = new Employee(s, editor.retrieveValue(s, "name"),
					editor.retrieveValue(s, "position"),
					editor.retrieveValue(s, "department"),
					editor.getDoubleValue(s, "work hours"),
					editor.getDoubleValue(s, "benefits"),
					editor.getDoubleValue(s, "overtime"),
					editor.getDoubleValue(s, "salary"));

			if (employee.getName() == null || employee.getPosition() == null || employee.getDepartment() == null ||
					employee.getWorkHours() == 0 || employee.getBenefits() == 0 || employee.getOvertime() == 0 ||
					employee.getSalary() == 0){

				return false;
			}
			else {
				employeeRepo.add(employee);

				EmployeeDescription details = new EmployeeDescription(s, editor.retrieveValue(s, "name"),
												editor.retrieveValue(s, "position"), editor.retrieveValue(s, "department"));
				employeeDescriptionRepo.put(s, details);
			}
		}
		return true;
	}

	@Override
	public boolean addPayrollRates(String payrollData) {

		editor.processTextFile(payrollData);
		
		if (editor.getRepository().isEmpty()) {
			System.out.println("Incorrect path to Department Employee Data");
			return false;
		}
		
		this.payrollData = payrollData;
		return true;
	}

	@Override
	public boolean processPayroll(String fileDirectory) {
		
		if (employeeRepo.isEmpty() || payrollData == null) {
			System.out.println("No Employee Data or Payroll Data found\n");
			return false;
		}


		
		File directory = new File(fileDirectory);
		if (!directory.exists()) {
			
			System.out.println("Directory does not exist");
			return false;
		}

		verifyDiscrepancy();
		if (!calculatePayroll()){
			return false;
		}
		
		createPayroll(fileDirectory);
		createPayslip(fileDirectory);

		// a copy of payroll report and payslips to HR
		createPayroll("src/HR/repository/payrollReports/");
		createPayslip("src/HR/repository/payslips");
		return true;
	}

	@Override
	public PayrollApproval retrieveApproval(String reportID) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean calculatePayroll() {
		
		editor.processTextFile(payrollData);
		double salaryRate = editor.getDoubleValue("payrollRate", "salaryRate");
		double benefitsRate = editor.getDoubleValue("payrollRate", "benefitsRate");
		double overtimeRate = editor.getDoubleValue("payrollRate", "overtimeRate");
		double taxRate = editor.getDoubleValue("payrollRate", "taxRate");
		double retirementRate = editor.getDoubleValue("payrollRate", "retirementRate");

		double total;
		
		for (int i = 0; i < employeeRepo.size(); i++) {
			
			double basePay = Double.parseDouble(String.format("%.2f", salaryRate * getPositionBasePay(employeeRepo.get(i).getPosition())));
			double benefitsPay = Double.parseDouble(String.format("%.2f", benefitsRate * employeeRepo.get(i).getBenefits()));
			double overtimePay = Double.parseDouble(String.format("%.2f", overtimeRate * employeeRepo.get(i).getOvertime()));
			double grossPay = Double.parseDouble(String.format("%.2f", basePay + benefitsPay + overtimePay));
			double taxDeduction = Double.parseDouble(String.format("%.2f", grossPay * (taxRate / 100.0)));
			totalTaxDeductions += (grossPay - taxDeduction);
			double retirementDeduction = Double.parseDouble(String.format("%.2f", grossPay * (retirementRate / 100.0)));
			totalRetirementDeductions += (grossPay - retirementDeduction);
			double netPay = Double.parseDouble(String.format("%.2f", grossPay - taxDeduction - retirementDeduction));

			if (netPay < 0){
				System.out.println("Salary for " + employeeRepo.get(i).getName() + " is negative after deductions");
				return false;
			}
			
			PayrollReport subreport = new PayrollReport(basePay, benefitsPay, overtimePay, grossPay, netPay);
			
			if (payrollReportRepo.containsKey(employeeRepo.get(i).getDepartment())) {
				
				PayrollReport report = payrollReportRepo.get(employeeRepo.get(i).getDepartment());
				PayrollReport newReport = new PayrollReport(basePay + report.getTotalBasePay(),
															benefitsPay + report.getTotalBenefitsPay(),
															overtimePay + report.getTotalOvertimePay(),
															grossPay + report.getTotalGrossPay(),
															netPay + report.getTotalNetPay());
				payrollReportRepo.put(employeeRepo.get(i).getDepartment(), newReport);
			}
			else {
				payrollReportRepo.put(employeeRepo.get(i).getDepartment(), subreport);
			}
			employeeRepo.get(i).setSalary(netPay);
		}
		return verifyBudget();
	}
	
	private double getPositionBasePay(String position) {
		
		double baseSalary = -1;
		
		switch (position) {
		
		case "employee":
			baseSalary =  3000.00;
			break;
		case "manager":
			baseSalary =  6000.00;
			break;
		default:
			System.out.println("No such job position");
		}
		return baseSalary;
	}

	private double getPositionWorkHours(String position){

		double workHourMaximum = -1;

		switch (position) {

			case "employee":
				workHourMaximum = 200;
				break;
			case "manager":
				workHourMaximum = 210;
				break;
			default:
				System.out.println("No such Job position");
		}
		return workHourMaximum;
	}

	private double getPositionOvertime(String position){

		double overtimeMaximum = -1;

		switch (position) {

			case "employee":
				overtimeMaximum = 15;
				break;
			case "manager":
				overtimeMaximum = 15;
				break;
			default:
				System.out.println("No such Job position");
		}
		return overtimeMaximum;
	}
	
	@Override
	public void createPayroll(String fileDirectory) {
		
		BufferedWriter writer = null;
		
		try {
			// report ID creation
			LocalDateTime timeNow = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyyHHmmss");
			String formattedDateTime = timeNow.format(formatter);

			writer = new BufferedWriter(new FileWriter(fileDirectory + "Payroll" + formattedDateTime + ".txt"));

			double benefits = 0;
			double salaries = 0;

			for (Map.Entry<String, PayrollReport> entry : payrollReportRepo.entrySet()) {
				
				String departmentName = entry.getKey();
				PayrollReport report = entry.getValue();
				
				writer.write(departmentName + "\n");
				writer.write("Total Base Pay: " + report.getTotalBasePay() + "$\n");
				writer.write("Total Benefits Pay: " + report.getTotalBenefitsPay() + "$\n");
				writer.write("Total Overtime Pay: " + report.getTotalOvertimePay() + "$\n");
				writer.write("Total Gross Pay: " + report.getTotalGrossPay() + "$\n");
				writer.write("Total Net Pay: " + report.getTotalNetPay() + "$\n");
				writer.write("\n");

				benefits += report.getTotalBenefitsPay();
				salaries += report.getTotalNetPay();
			}
			
			writer.write("========================================================================\n");
			writer.write("Total Salaries Paid: " + salaries + "$\n");

			String payrollID = "Payroll" + formattedDateTime;
			PayrollDescription description = new PayrollDescription(payrollID, salaries, benefits, totalTaxDeductions,
																	totalRetirementDeductions, employeeRepo.size());
			payrollDescriptionRepo.put(payrollID, description);
			
		} catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
	}
	
	@Override
	public void createPayslip(String fileDirectory) {
		
		BufferedWriter writer = null;
		
		for (int i = 0; i < employeeRepo.size(); i++) {
			
			try {
				
				Employee employee = employeeRepo.get(i);
				writer = new BufferedWriter(new FileWriter(fileDirectory + "PaySlip_" + employee.getEmployeeID() + ".txt"));
				
				writer.write("Employee ID: " + employee.getEmployeeID() + "\n");
				writer.write("Name: " + employee.getName() + "\n");
				writer.write("\n");
				writer.write("Salary: " + employee.getSalary() + "$");
				
				
			} catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (writer != null) {
	                    writer.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}
	}

	private void verifyDiscrepancy(){

        for (Employee employee : employeeRepo) {

            if (employee.getWorkHours() > getPositionWorkHours(employee.getPosition())) {

                System.out.println("Employee " + employee.getName() + " from " + employee.getDepartment() + " has discrepancy:");
                System.out.println("Current work hours: " + employee.getWorkHours() + ", Maximum: " + getPositionWorkHours(employee.getPosition()) + "\n");
            } else if (employee.getOvertime() > getPositionOvertime(employee.getPosition())) {

                System.out.println("Employee " + employee.getName() + " from " + employee.getDepartment() + " has discrepancy:");
                System.out.println("Current overtime: " + employee.getOvertime() + ", Maximum: " + getPositionOvertime(employee.getPosition()) + "\n");
            }
        }
	}

	private boolean verifyBudget(){

		double salaries = 0;

		for (Map.Entry<String, PayrollReport> entry : payrollReportRepo.entrySet()) {

			String departmentName = entry.getKey();
			PayrollReport report = entry.getValue();
			salaries += report.getTotalNetPay();
		}

		editor.processTextFile("src/Treasury/repository/budgetData/budget.txt");
		double budget = Double.parseDouble(editor.retrieveValue("budget", "maxBudget"));

		if (salaries > budget){
			System.out.println("Total salaries paid: " + salaries + "$ has exceeded budget: " + budget + "$\n");
			return false;
		}
		return true;
	}
}
