package src.HR.src;
import src.TextEditor.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class fileStorageHR {

    //TODO: change this to use system specific default path, have it initialized to null
    String default_filepath_employeeStorage = "C:\\Users\\samue\\coms362\\src\\HR\\repository\\employeeStorage";
    String default_filepath_candidateStorage = "C:\\Users\\samue\\coms362\\src\\HR\\repository\\candidateStorage";
    String filepath = default_filepath_employeeStorage;
    PoorTextEditor poorJarser = new PoorTextEditor(); //haha haha

    public fileStorageHR() {}

    public String getFilepath() {
        return filepath;
    }

    public String getDefault_filepath_employeeStorage() {
        return default_filepath_employeeStorage;
    }

    public String getDefault_filepath_candidateStorage() { return default_filepath_candidateStorage; }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Deletes a given file, expects to be given the filepath however
     * @param filename name of File to be deleted, must contain file extension
     */
    public void deleteFile(String filename) throws Exception {
        try {
            //TODO: needs to take only filename, universal method
            Path filePath = Paths.get(getFilepath(), filename);
            File file = filePath.toFile();
            System.out.println("Attempting to delete: " + file.getAbsolutePath());

            if (file.exists()) {
                if (file.delete()) {
                    System.out.println(filename + " deleted successfully");
                } else {
                    throw new Exception("Failed to delete " + filename);
                }
            } else {
                throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    /**
     * @param folderName the name of the Department folder to be accessed
     * @return returns a String representation of the folderPath to the Department Folder
     * @throws IOException error checking
     */
    public Path getEmployeeStoragePath(String folderName) throws IOException {
        Path folderPath = Paths.get("src", "HR", "repository", "employeeStorage", folderName.toUpperCase());
        if (!Files.exists(folderPath)) {
            throw new FileNotFoundException("Folder not found: " + folderName.toUpperCase());
        }

        return folderPath.toAbsolutePath();
    }

    /**
     *
     * @param folderName the specific Status folder to set the path to
     * @return returns a Path object that contains the absolute path of the defined folder
     * @throws IOException error checking
     */
    public Path getCandidateStoragePath(String folderName) throws IOException {
        Path folderPath = Paths.get("src", "HR", "repository", "candidateStorage", folderName.toUpperCase());
        if (!Files.exists(folderPath)) {
            throw new FileNotFoundException("Folder not found: " + folderName.toUpperCase());
        }

        return folderPath.toAbsolutePath();
    }

    /**
     *
     * @param filename the name of the file to be printed out to console, uses fileStorageHR filepath for parent directory
     * @throws Exception error checking
     */
    public void loadFileAndPrint(String filename) throws Exception {
        try {
            System.out.println("Attempting to load: " + filename);
            File file = new File(getFilepath(), filename);
            System.out.println("Loading file: " + file.getAbsolutePath());
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (Exception e) {
            throw new Exception("Error encountered in loadFileAndPrint with filename: "
                    + filename + " and filepath: " + filepath + "\n" + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {

        //TODO: insert more testing here
        fileStorageHR hr = new fileStorageHR();
        String filepathEmployee = hr.getEmployeeStoragePath("engineering").toString();
        String filepathCandidate = hr.getCandidateStoragePath("applied").toString();
        hr.setFilepath(filepathCandidate);
        System.out.println(hr.getFilepath());
        hr.setFilepath(filepathEmployee);
        System.out.println(hr.getFilepath());
        hr.loadFileAndPrint("0.txt");
    }
}
