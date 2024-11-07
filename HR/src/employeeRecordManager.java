import java.util.ArrayList;
import java.util.List;

public class employeeRecordManager {
    private List<Employee> employeeList = new ArrayList<>();

    
    /** 
     * @param employee
     */
    // Add a new employee
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
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
    public void displayDepartment(Department departmentName) {
        for (Employee emp : employeeList) {
            if(emp.department == departmentName) {
                System.out.println(emp);
            }
        }
    }
}
