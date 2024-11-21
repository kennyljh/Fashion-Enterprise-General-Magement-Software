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
    public void addEmployee(Employee employee) throws IOException {
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
        String filepathToEmployeeStorage = String.valueOf(storageHR.getEmployeeStoragePath(String.valueOf(employee.getDepartment())));
        storageHR.poorJarser.writeToTextFile(filepathToEmployeeStorage + "/" + employee.getEmployeeID() + ".txt");
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

        //make sure data is not null
        //TODO: is this necessary?
        if(data == null) {
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
        Department department = src.HR.src.Department.valueOf(scanner.next().toUpperCase());

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


//    public void retrieveEmployeeByEmployeeID(String employeeID) {
//        try {
//            //TODO: implement file storage into the loadFileAndPrint path
//            storageHR.loadFileAndPrint(storageHR.getFilepath() + "\\" + employeeID + ".txt");
//        } catch (Exception e) {
//            throw new RuntimeException("Error in retrieveEmployeeByEmployeeID: \n" + e);
//        }
//    }
}
