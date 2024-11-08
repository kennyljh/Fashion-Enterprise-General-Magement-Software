/**
 * @author Kenny
 */

package Treasury;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	private ArrayList<Employee> employeeRepo = new ArrayList<Employee>();
	private String payrollData = null;
	private Map<String, PayrollReport> payrollReportRepo = new HashMap<>();
	
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
			employeeRepo.add(employee);
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
			return false;
		}
		
		File directory = new File(fileDirectory);
		if (!directory.exists()) {
			
			System.out.println("Directory does not exist");
			return false;
		}
		
		calculatePayroll();
		
		createPayroll(fileDirectory);
		createPayslip(fileDirectory);
		return true;
	}

	@Override
	public PayrollApproval retrieveApproval(String reportID) {
		// TODO Auto-generated method stub
		return null;
	}

	private void calculatePayroll() {
		
		editor.processTextFile(payrollData);
		double salaryRate = editor.getDoubleValue("payrollRate", "salaryRate");
		double benefitsRate = editor.getDoubleValue("payrollRate", "benefitsRate");
		double overtimeRate = editor.getDoubleValue("payrollRate", "overtimeRate");
		double taxRate = editor.getDoubleValue("payrollRate", "taxRate");
		double retirementRate = editor.getDoubleValue("payrollRate", "retirementRate");
		
		for (int i = 0; i < employeeRepo.size(); i++) {
			
			double basePay = Double.parseDouble(String.format("%.2f", salaryRate * getPositionBasePay(employeeRepo.get(i).getPosition())));
			double benefitsPay = Double.parseDouble(String.format("%.2f", benefitsRate * employeeRepo.get(i).getBenefits()));
			double overtimePay = Double.parseDouble(String.format("%.2f", overtimeRate * employeeRepo.get(i).getOvertime()));
			double grossPay = Double.parseDouble(String.format("%.2f", basePay + benefitsPay + overtimePay));
			double taxDeduction = Double.parseDouble(String.format("%.2f", grossPay * (taxRate / 100.0)));
			double retirementDeduction = Double.parseDouble(String.format("%.2f", grossPay * (retirementRate / 100.0)));
			double netPay = Double.parseDouble(String.format("%.2f", grossPay - taxDeduction - retirementDeduction));
			
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
	
	@Override
	public void createPayroll(String fileDirectory) {
		
		BufferedWriter writer = null;
		
		try {
			
			LocalDateTime timeNow = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyyHHmmss");
			String formattedDateTime = timeNow.format(formatter);
			
			writer = new BufferedWriter(new FileWriter(fileDirectory + "Payroll" + formattedDateTime + ".txt"));
			
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
				
				salaries += report.getTotalNetPay();
			}
			
			writer.write("========================================================================\n");
			writer.write("Total Salaries Paid: " + salaries + "$\n");
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
}
