package src.HR.src;
import src.TextEditor.*;

import java.io.File;
import java.util.Scanner;

public class fileStorageHR {


    String default_filepath = "C:\\Users\\samue\\coms362\\src\\HR\\repository\\employeeStorage";
    String filepath = default_filepath;
    PoorTextEditor poorJarser = new PoorTextEditor(); //haha haha

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

    public void loadFileAndPrint(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
