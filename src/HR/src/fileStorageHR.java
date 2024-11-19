package src.HR.src;
import src.TextEditor.*;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class fileStorageHR {


    String default_filepath = "C:\\Users\\samue\\coms362\\src\\HR\\repository\\employeeStorage";
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

    /**
     * Deletes a given file, expects to be given the filepath however
     * @param filename name of File to be deleted, must contain file extension
     */
    public void deleteFile(String filename) {
        try{
            File file = new File(filename);
            if(file.exists()) {
                if(file.delete()) {
                    System.out.println(filename + " deleted successfully");
                }
                else {
                    System.out.println(filename + " could not be deleted");
                }
            } else {
                System.out.println(filename + " could not be found");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        fileStorageHR storageHR = new fileStorageHR();
//        Map<String, Map<String, String>> data = new LinkedHashMap<>();
//        Employee employee = new Employee("0", "testdummy", Department.MARKETING, "Senior Design", "Active", 200);
//        Employee employee1 = new Employee("1", "testdummy", Department.MARKETING, "Senior Design", "Active", 200);
//        Map<String, String> employeeObject = new LinkedHashMap<>();
//
//        employeeObject.put("name", employee.getName());
//        employeeObject.put("employeeID", employee.getEmployeeID());
//        employeeObject.put("employeeDepartment", employee.getDepartment());
//        employeeObject.put("employeePosition", employee.getPosition());
//        employeeObject.put("employeeStatus", employee.getEmployementStatus());
//        employeeObject.put("employeeSalary", employee.getSalary());
//        data.put(employee.getEmployeeID(), employeeObject);
//        storageHR.poorJarser.setRepositoryStrings(data);
//        storageHR.poorJarser.writeToTextFile(storageHR.getFilepath() + "\\" + employee.getEmployeeID() + ".txt");
//        storageHR.deleteFile(storageHR.getFilepath() + "\\" + employee.getEmployeeID() + ".txt");
//
//    }
}
