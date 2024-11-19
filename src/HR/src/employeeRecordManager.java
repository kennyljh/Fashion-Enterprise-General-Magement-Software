package src.HR.src;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Sam Gumm
 */
public class employeeRecordManager {
    Map<String, Map<String, String>> data = new LinkedHashMap<>();
    private final fileStorageHR storageHR;

    public employeeRecordManager(fileStorageHR storageHR) {
        this.storageHR = storageHR;
    }

    /**
     * Adds an Employee to record, then persistently stores it in "empID".txt
     *
     * @param employee the Employee object to be added
     */
    // Add a new employee
    public void addEmployee(Employee employee) {
        Map<String, String> employeeObject = new LinkedHashMap<>();
        employeeObject.put("name", employee.getName());
        employeeObject.put("employeeID", employee.getEmployeeID());
        employeeObject.put("employeeDepartment", employee.getDepartment());
        employeeObject.put("employeePosition", employee.getPosition());
        employeeObject.put("employeeStatus", employee.getEmployementStatus());
        employeeObject.put("employeeSalary", employee.getSalary());
        data.put(employee.getEmployeeID(), employeeObject);
        storageHR.poorJarser.setRepositoryStrings(data);
        storageHR.poorJarser.writeToTextFile(storageHR.getFilepath() + "\\" + employee.getEmployeeID() + ".txt");
    }

    /**
     * Removes employee FILE from employeeStorage repo, uses fileStorageHR filepath to find file.
     * Also attempts to remove Employee from LinkedHashMap record if it exists there as well.
     *
     * @param employeeID the EmployeeID to be removed
     *
     */
    public void removeEmployee(String employeeID) throws Exception {
        if(!data.containsKey(employeeID)) {
            System.out.println("Employee is not in record");
        }
        else {
            try {
                data.remove(employeeID);
                storageHR.poorJarser.setRepositoryStrings(data);
            } catch (Exception e) {
                System.out.println("Error in removeEmployee when removing from LinkedHashMap<>()");
                e.printStackTrace();
            }
        }

        try {
            storageHR.deleteFile(storageHR.default_filepath + "\\" + employeeID + ".txt");
        } catch (Exception e) {
            System.out.println("Error in removeEmployee when deleting file from repository");
            e.printStackTrace();
        }
    }

    /**
     * Takes the EmployeeID of the Employee object to be changed, then checks the LinkedHashMap
     * for the ID; if it finds the ID, the associated Employee is altered and re-uploaded to file,
     * otherwise, an error is thrown.
     *
     * @param employeeID the associated ID of the Employee
     * @param department the department of the Employee
     * @param position the position of the Employee
     * @param employmentStatus the status of the Employee
     * @param salary the salary of the Employee
     */
    public void updateEmployee(String employeeID, Department department, String position, String employmentStatus, int salary) {
        if (!data.containsKey(employeeID)) {
            System.out.println("Employee not found: " + employeeID);
            return;
        }
        Map<String, String> employeeObject = data.get(employeeID);
        employeeObject.put("employeeDepartment", String.valueOf(department));
        employeeObject.put("employeePosition", position);
        employeeObject.put("employeeStatus", employmentStatus);
        employeeObject.put("employeeSalary", String.valueOf(salary));
        data.put(employeeID, employeeObject);
        storageHR.poorJarser.setRepositoryStrings(data);
        storageHR.poorJarser.writeToTextFile(storageHR.default_filepath + "\\" + employeeID + ".txt");

    }

    /**
     * Display all records currently in the LinkedHashMap "data"
     */
    public void displayRecords() {
        data.values().forEach(System.out::println);
    }


    /**
     * @param departmentName the Department to iterate through.
     */
    public void displayEmployeesByDepartment(Department departmentName) {
        data.values().stream()
                .filter(emp -> departmentName.toString().equals(emp.get("employeeDepartment")))
                .forEach(System.out::println);
    }
}
