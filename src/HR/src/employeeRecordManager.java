package src.HR.src;

import javax.swing.text.Position;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

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
    public void removeEmployee(String employeeID) {
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
     */
    public void updateEmployee(String employeeID) {
        String filepath = storageHR.getFilepath() + "\\" + employeeID + ".txt";

        //read from employee file
        storageHR.poorJarser.processTextFile(filepath);

        //initialize data from current repo
        Map<String, Map<String, String>> data = storageHR.poorJarser.getRepositoryStringMap();

        //make sure it is not null
        if(data == null) {
            data = new LinkedHashMap<>();
        }

        //initialize employeeObject to modify
        Map<String, String> employeeObject = data.get(employeeID);
        if(employeeObject == null) {
            employeeObject = new LinkedHashMap<>();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter employee department: ");
        for(int i = 0; i < Department.values().length; i++) {
            System.out.println(Department.values()[i].name());
        }
        Department department = Department.valueOf(scanner.next().toUpperCase());
        System.out.println("Enter employee position: ");
        String position = scanner.next();
        System.out.println("Enter employee employment status (i.e. onboarding): ");
        String employmentStatus = scanner.next();
        System.out.println("Enter employee salary: ");
        int salary = scanner.nextInt();


        employeeObject.put("employeeDepartment", department.toString());
        employeeObject.put("employeePosition", position);
        employeeObject.put("employeeStatus", employmentStatus);
        employeeObject.put("employeeSalary", String.valueOf(salary));
        data.put(employeeID, employeeObject);


        storageHR.poorJarser.setRepositoryStrings(data);
        storageHR.poorJarser.writeToTextFile(filepath);

    }

    /**
     * Display all records currently in the LinkedHashMap "data"
     */
    public void displayRecords() {
        data.values().forEach(System.out::println);
    }

    public void displayFileRecords() throws FileNotFoundException {
        String folderPath = storageHR.getFilepath();
        File folder = new File(folderPath);

        if(folder.isDirectory()) {
            File[] files = folder.listFiles();
            int i = 0;
            do {
                assert files != null;
                File file = files[i];
                if(file.isFile() && file.getName().endsWith(".txt")) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                i++;
            } while (i < files.length);
        }
        else {
            System.out.println("File is not a directory");
        }
    }

    /**
     * @param departmentName the Department to iterate through.
     */
    public void displayEmployeesByDepartment(Department departmentName) {
        data.values().stream()
                .filter(emp -> departmentName.toString().equals(emp.get("employeeDepartment")))
                .forEach(System.out::println);
    }

    public void displayEmployeesByPosition(Position position) {
        data.values().stream()
                .filter(emp -> position.toString().equals(emp.get("employeePosition")))
                .forEach(System.out::println);
    }

    public void retrieveEmployeeByEmployeeID(String employeeID) {
        storageHR.loadFileAndPrint(storageHR.default_filepath + "\\" + employeeID + ".txt");
    }
}
