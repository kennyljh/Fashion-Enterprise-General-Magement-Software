package src.HR.src;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam Gumm
 */
public class employeeRecordManager {
    private final List<Employee> employeeList = new ArrayList<>();

    
    /** 
     * @param employee
     */
    // Add a new employee
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    /**
     * @param employeeName
     */
    //remove employee
    public void removeEmployee(String employeeName) throws Exception {
        if (employeeList.isEmpty()) {
            throw new Exception("Employee list is empty");
        } else {
            for (Employee employee : employeeList) {
                if (employee.getName().equals(employeeName)) {
                    employeeList.remove(employee);
                }
                else {
                    throw new Exception("Employee does not exist");
                }
            }
        }
    }

    /** 
     * @param employeeId
     * @param department
     * @param position
     * @param employmentStatus
     * @param salary
     */
    // Update employee record
    public void updateEmployee(String employeeId, Department department, String position, String employmentStatus, int salary) {
        for (Employee emp : employeeList) {
            if (emp.employeeId.equals(employeeId)) {
                emp.department = department;
                emp.position = position;
                emp.employmentStatus = employmentStatus;
                return;
            }
        }
        System.out.println("Employee not found: " + employeeId);
    }

    // Display employee records
    public void displayRecords() {
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }

    
    /** 
     * @param departmentName
     */
    public void displayEmployeesByDepartment(Department departmentName) {
        for (Employee emp : employeeList) {
            if(emp.department == departmentName) {
                System.out.println(emp);
            }
        }
    }

    public int collateSalariesByDepartment(Department department) {
        int totalSalary = 0;
        for (Employee emp : employeeList) {
            if(emp.department == department) {
                totalSalary += emp.salary;
            }
        }
        return totalSalary;
    }
}
