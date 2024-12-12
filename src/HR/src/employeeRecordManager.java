package src.HR.src;


import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Sam Gumm
 */
public class employeeRecordManager {
    Map<String, Map<String, String>> data = new LinkedHashMap<>();
    private final fileStorageHR storageHR = new fileStorageHR();
    valueHandling valueHandler = new valueHandling();

    /*
        TODO:
            - make methods to transfer employees to new folders
            - implement this in updateEmployee
     */

    /**
     * Adds an Employee to record, then persistently stores it in "empID".txt
     */
    // Add a new employee
    public void addEmployee() throws IOException {
        //TODO: have counter that increments for employeeID
        String name;
        String employeeId;
        String initialDep;
        String position;
        String employmentStatus;
        String salary;

        //Name
        System.out.println("Enter employee name: ");

        name = valueHandler.inputValidator(true);

        //ID
        System.out.println("Enter employeeID: ");
        employeeId = valueHandler.inputValidator(true);

        //Department
        Department department;
        System.out.println("Enter employee department: ");
        for(int i = 0; i < Department.values().length; i++) {
            System.out.println(Department.values()[i].name());
        }
        initialDep = valueHandler.inputValidator(true);

        //handling Human Resources
        if (initialDep.contains("Human Resources")
                || initialDep.contains("human resources")) {
            department = Department.HUMAN_RESOURCES;
        } else {
            department = Department.valueOf(initialDep.toUpperCase());
        }

        //Position
        System.out.println("Enter employee position: ");
        position = valueHandler.inputValidator(true);

        //Status
        System.out.println("Enter employee employment status (i.e. onboarding): ");
        employmentStatus = valueHandler.inputValidator(true).toUpperCase();

        //Salary
        System.out.println("Enter employee salary: ");
        salary = valueHandler.inputValidator(true);

        //adding employee
        Map<String, String> employeeObject = new LinkedHashMap<>();
        employeeObject.put("name", name);
        employeeObject.put("employeeID", employeeId);
        employeeObject.put("employeeDepartment", department.toString());
        employeeObject.put("employeePosition", position);
        employeeObject.put("employeeStatus", employmentStatus);
        employeeObject.put("employeeSalary", String.valueOf(salary));
        data.put(employeeId, employeeObject);
        storageHR.poorJarser.setRepositoryStrings(data);
        String filepathToEmployeeStorage = String.valueOf(storageHR.getEmployeeStoragePath(String.valueOf(department)));
        storageHR.poorJarser.writeToTextFile(filepathToEmployeeStorage + "/" + employeeId + ".txt");
    }


    /**
     * Removes employee FILE from employeeStorage repo, uses fileStorageHR filepath to find file.
     * Also attempts to remove Employee from LinkedHashMap record if it exists there as well.
     *
     * @param employeeID the EmployeeID to be removed
     *
     */
    public void removeEmployee(String employeeID) throws Exception {
        //remove employee from data LinkedHashMap if it contains the id
        if(data.containsKey(employeeID)) {
            try {
                data.remove(employeeID);
                storageHR.poorJarser.setRepositoryStrings(data);
            } catch (Exception e) {
                throw new Exception("Error in removeEmployee when removing found ID from LinkedHashMap<>(): \n" + e.getMessage());
            }
        }

        String filepath = String.valueOf(findEmployeeFile(employeeID));
        if(filepath == null) {
            throw new NullPointerException("filepath from removeEmployee is null");
        }

        try {
            storageHR.deleteFile(filepath);
        } catch (Exception e) {
            throw new Exception("Error in removeEmployee when deleting file from repository with file path: "
                    + filepath + "\n" + e.getMessage());
        }
    }

    /**
     *
     * @param employeeID String ID of the employee to be moved
     * @param department Department object of the department to move the employee to
     * @throws IOException if file input is invalid
     */
    private void moveEmployee(String employeeID, Department department) throws IOException {
        // Find the current employee file path
        Path currentEmployeeFile = findEmployeeFile(employeeID);
        if (currentEmployeeFile == null || !Files.exists(currentEmployeeFile)) {
            throw new FileNotFoundException("Employee file not found: " + employeeID + ".txt");
        }

        // Get the path to the new Department folder
        Path newDepartmentFolder = storageHR.getEmployeeStoragePath(department.toString().toUpperCase());
        if (!Files.exists(newDepartmentFolder)) {
            throw new FileNotFoundException("Department folder not found: " + department.toString().toUpperCase());
        }

        // Construct the destination path for the candidate file
        Path destinationFile = newDepartmentFolder.resolve(employeeID + ".txt");

        // Move the candidate file to the new status folder
        Files.move(currentEmployeeFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Employee was successfully moved to " + destinationFile);
    }

    /**
     * Takes the EmployeeID of the Employee object to be changed, then checks the LinkedHashMap
     * for the ID; if it finds the ID, the associated Employee is altered and re-uploaded to file,
     * otherwise, an error is thrown.
     *
     * @param employeeID the associated ID of the Employee
     */
    public void updateEmployee(String employeeID) throws Exception {
        String filepath;

        filepath = Objects.requireNonNull(findEmployeeFile(employeeID)).toString();

        //read from Employee file
        storageHR.poorJarser.processTextFile(filepath);

        //initialize data from current repo
        Map<String, Map<String, String>> data = storageHR.poorJarser.getRepositoryStringMap();

        //make sure data is not initialized with nothing
        if(data.isEmpty()) {
            throw new Exception("data was not initialized properly: \n");
        }

        //initialize candidateObject to modify
        Map<String, String> employeeObject = data.get(employeeID);
        if(employeeObject == null) {
            employeeObject = new LinkedHashMap<>();
        }
        removeEmployee(employeeID);
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < Department.values().length; i++) {
            System.out.println(Department.values()[i].name());
        }

        System.out.println("Enter new employee Department: ");
        Department department = Department.valueOf(scanner.next().toUpperCase());

        System.out.println("Enter in new employee position: ");
        String position = scanner.next();

        System.out.println("Enter in new employee Status: ");
        String status = scanner.next();

        System.out.println("Enter in new employee salary: ");
        String salary = scanner.next();

        employeeObject.put("employeeID", employeeID);
        employeeObject.put("name", employeeObject.get("name"));
        employeeObject.put("department", department.toString());
        employeeObject.put("position", position);
        employeeObject.put("status", status);
        employeeObject.put("salary", salary);

        data.put(employeeID, employeeObject);

        storageHR.poorJarser.setRepositoryStrings(data);
        storageHR.poorJarser.writeToTextFile(filepath);

        moveEmployee(employeeID, department);
    }

    /**
     * @param departmentName the Department to iterate through.
     */
    public void displayEmployeesByDepartment(Department departmentName) throws Exception {
        try {
            String statusDirName = String.valueOf(departmentName).toUpperCase();

            Path statusDir = storageHR.getEmployeeStoragePath(statusDirName);

            if(statusDir == null) {
                throw new NullPointerException("statusDir is null");
            }

            if(!Files.exists(statusDir) || !Files.isDirectory(statusDir)) {
                System.out.println("No candidates found with status: " + departmentName);
                return;
            }

            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(statusDir, "*.txt")) {
                boolean employeesFound = false;
                for (Path path : directoryStream) {
                    employeesFound = true;
                    storageHR.loadFileAndPrint(path.toString()); // Pass full path as string
                }
                if(!employeesFound) {
                    System.out.println("No candidates found with status: " + departmentName);
                }
            }
        } catch (Exception e) {
            throw new Exception("displayEmployeesByDepartment failed: \n" + e.getMessage(), e);
        }
    }

    /**
     *
     * @param employeeID String ID of the employee to be found
     * @return a Path object containing the path to the employee File
     * @throws IOException if file encountered is invalid
     */
    public Path findEmployeeFile(String employeeID) throws IOException {
        Path base = storageHR.getDefault_filepath_employeeStorage();
        String employeeFileName = employeeID + ".txt";

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(base)) {
            for (Path statusDir : directoryStream) {
                if (Files.isDirectory(statusDir)) { // Ensure it's a directory
                    Path employeeFile = statusDir.resolve(employeeFileName);
                    if (Files.exists(employeeFile)) {
                        return employeeFile;
                    }
                }
            }
        }
        return null; // Return null if the candidate file is not found
    }

    /**
     *
     * @param employeeID String ID of the employee to be returned
     * @return complete Employee object created from employee File
     * @throws Exception if text file is invalid
     */
    public Employee getEmployee(String employeeID) throws Exception {
        String filepath;

        filepath = Objects.requireNonNull(findEmployeeFile(employeeID)).toString();
        System.out.println(filepath);
        //read from Employee file
        storageHR.poorJarser.processTextFile(filepath);

        Map<String, Map<String, String>> data = storageHR.poorJarser.getRepositoryStringMap();

        if(data.isEmpty()) {
            throw new Exception("data was not initialized properly: \n");
        }


        //initialize data from current repo
        Map<String, String> employeeObject = data.get(employeeID);

        assert employeeObject != null;

        String name = employeeObject.get("name");
        String empID = employeeObject.get("employeeID");
        Department department = Department.valueOf(employeeObject.get("employeeDepartment").toUpperCase());
        String position = employeeObject.get("employeePosition");
        String status = employeeObject.get("employeeStatus");
        int salary = Integer.parseInt(employeeObject.get("employeeSalary"));

        return new Employee(empID, name, department, position, status, salary);
    }
}
