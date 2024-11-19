package src.HR.src;
import src.TextEditor.*;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class fileStorageHR {


    String default_filepath = "C:\\Users\\samue\\coms362\\fashionempire_3620_fall2024\\src\\HR\\repository\\employeeStorage";
    String filepath = default_filepath;
    PoorTextEditor poorJarser = new PoorTextEditor(); //hahahaha

    public fileStorageHR() {}

    public String getFilepath() {
        return filepath;
    }

    public String getDefaultFilepath() {
        return default_filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public PoorTextEditor getPoorJarser() {
        return poorJarser;
    }

    public void setPoorJarser(PoorTextEditor poorJarser) {
        this.poorJarser = poorJarser;
    }

    public void createFile(String filename) {
        try{
            File file = new File(filepath + "/" + filename);
            if(file.createNewFile()) {
                System.out.println("File created" + file.getName());
            }
            else {
                System.out.println("File already exists" + file.getName());
            }
        }
        catch(Exception e) {
            System.out.println("An error occured while creating the file with path: " + filepath + "/" + filename);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteFile(String filename) {
        try{
            File file = new File(filepath + "/" + filename);
            if(file.delete()) {
                System.out.println("File deleted" + file.getName());
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        fileStorageHR storageHR = new fileStorageHR();
        Map<String, Map<String, String>> data = new LinkedHashMap<>();
        Employee employee = new Employee("0", "testdummy", Department.MARKETING, "Senior Design", "Active", 200);
        Employee employee1 = new Employee("1", "testdummy", Department.MARKETING, "Senior Design", "Active", 200);
        Map<String, String> employeeObject = new LinkedHashMap<>();

        employeeObject.put("name", employee.getName());
        employeeObject.put("employeeID", employee.getEmployeeID());
        employeeObject.put("employeeDepartment", employee.getDepartment());
        employeeObject.put("employeePosition", employee.getPosition());
        employeeObject.put("employeeStatus", employee.getEmployementStatus());
        employeeObject.put("employeeSalary", employee.getSalary());
        data.put(employee.getEmployeeID(), employeeObject);
        storageHR.poorJarser.setRepositoryStrings(data);
        storageHR.createFile(storageHR.default_filepath + "\\" + employee.getEmployeeID() + ".txt");
        storageHR.poorJarser.writeToTextFile(storageHR.getFilepath() + "\\" + employee.getEmployeeID() + ".txt");

        data.clear();

        employeeObject.put("name", employee1.getName());
        employeeObject.put("employeeID", employee1.getEmployeeID());
        employeeObject.put("employeeDepartment", employee1.getDepartment());
        employeeObject.put("employeePosition", employee1.getPosition());
        employeeObject.put("employeeStatus", employee1.getEmployementStatus());
        employeeObject.put("employeeSalary", employee1.getSalary());

        data.put(employee1.getEmployeeID(), employeeObject);
        storageHR.poorJarser.setRepositoryStrings(data);
        storageHR.createFile(storageHR.default_filepath + "\\" + employee1.getEmployeeID() + ".txt");
        storageHR.poorJarser.writeToTextFile(storageHR.getFilepath() + "\\" + employee1.getEmployeeID() + ".txt");
        storageHR.poorJarser.processTextFile(storageHR.getFilepath() + "\\" + employee1.getEmployeeID() + ".txt");

    }
}
