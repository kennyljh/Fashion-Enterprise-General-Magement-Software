package src.HR.src;


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

    /*
        TODO:
            - make methods to transfer employees to new folders
            - implement this in updateEmployee
     */

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
        //TODO: have counter that increments for employeeID
        Map<String, String> employeeObject = new LinkedHashMap<>();
        employeeObject.put("name", employee.getName());
        employeeObject.put("employeeID", employee.getEmployeeID());
        employeeObject.put("employeeDepartment", employee.getDepartment());
        employeeObject.put("employeePosition", employee.getPosition());
        employeeObject.put("employeeStatus", employee.getEmployementStatus());
        employeeObject.put("employeeSalary", employee.getSalary());
        data.put(employee.getEmployeeID(), employeeObject);
        storageHR.poorJarser.setRepositoryStrings(data);
        //TODO: add path to employeeStorage and specific department
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
        //remove employee from data LinkedHashMap if it contains the id
        if(data.containsKey(employeeID)) {
            try {
                data.remove(employeeID);
                storageHR.poorJarser.setRepositoryStrings(data);
            } catch (Exception e) {
                throw new Exception("Error in removeEmployee when removing found ID from LinkedHashMap<>(): \n" + e.getMessage());
            }
        }

        try {
            //TODO: create path to employeeStorage
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter in Department of Employee");
            String department = scanner.next();
            //TODO: set filepath here with department
            storageHR.deleteFile(employeeID + ".txt");
            scanner.close();
        } catch (Exception e) {
            throw new Exception("Error in removeEmployee when deleting file from repository with file path: "
                    + storageHR.filepath + "\n" + e.getMessage());
        }

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
        /*
        TODO:
            - Add direct path to employeeStorage
            - use soon to be defined method to move employee to new department if necessary

         */
        filepath = storageHR.getFilepath() + "\\" + employeeID + ".txt";

        //read from employee file
        storageHR.poorJarser.processTextFile(filepath);

        //initialize data from current repo
        Map<String, Map<String, String>> data = storageHR.poorJarser.getRepositoryStringMap();

        //make sure data is not null
        if(data == null) {
            throw new Exception("data was not initialized properly: \n");
        }

        //initialize employeeObject to modify
        Map<String, String> employeeObject = data.get(employeeID);
        if(employeeObject == null) {
            employeeObject = new LinkedHashMap<>();
        }
        Scanner scanner = new Scanner(System.in);
        //TODO: moving employee happens here
        System.out.println("Enter employee department: ");
        for(int i = 0; i < Department.values().length; i++) {
            System.out.println(Department.values()[i].name());
        }
        Department department = Department.valueOf(scanner.next().toUpperCase());
        System.out.println("Enter employee position: ");
        String position = scanner.next();
        //TODO: Print out all valid statuses and have error handling
        System.out.println("Enter employee employment status (i.e. onboarding): ");
        String employmentStatus = scanner.next();
        System.out.println("Enter employee salary: ");
        int salary = scanner.nextInt();
        scanner.close();


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
    public void displayCurrentEmployeeRecords() {
        data.values().forEach(System.out::println);
    }

    //TODO: move this to fileStorageHR
    public void displayFileRecords(String folderPath) throws Exception {
        File folder = new File(folderPath);
        //TODO: add direct path to employeeStorage
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
            throw new Exception("File is not a directory");
        }
    }



    /**
     * @param departmentName the Department to iterate through.
     */
    public void displayEmployeesByDepartment(Department departmentName) throws Exception {
        //TODO
    }


    public void retrieveEmployeeByEmployeeID(String employeeID) {
        try {
            //TODO: implement file storage into the loadFileAndPrint path
            storageHR.loadFileAndPrint(storageHR.getFilepath() + "\\" + employeeID + ".txt");
        } catch (Exception e) {
            throw new RuntimeException("Error in retrieveEmployeeByEmployeeID: \n" + e);
        }
    }
}
