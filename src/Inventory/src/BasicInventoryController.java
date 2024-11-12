package src.Inventory.src;
import src.Inventory.src.interfaces.InventoryController;
import src.Inventory.src.interfaces.StorageManagement;


import src.App;
import src.Inventory.src.interfaces.*;

import java.io.File;
import java.util.Scanner;

/**
 * Handles user interaction for inventory management.
 * Provides options to register products and retailers,
 * and to add or remove product quantities from storage.
 *
 * @author Mani Raj
 */

public class BasicInventoryController implements InventoryController {

    private BasicStorageManage storage ;

    private String StorageLocation;
    public BasicInventoryController() {}

    /**
     * Runs the inventory management system.
     */
    public void run() {
        System.out.println("Welcome to the Inventory Management System");


        Scanner scan = new Scanner(System.in);
        boolean isExist = false;

        System.out.println("Enter Storage Location:");
        StorageLocation = scan.nextLine();
        validateStoreLoc(StorageLocation);




        boolean exit = false;
        while (!exit) {
            System.out.println("1. Register New Product");
            System.out.println("2. Register New Retailer");
            System.out.println("3. Register New Storage");
            System.out.println("4. Add Product Quantity");
            System.out.println("5. Remove Product Quantity");
            System.out.println("6. Re-Enter Storage Location");
            System.out.println("7. Exit Program");

            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline

            switch (choice) {
                case 1:

                    System.out.println("Enter Product Name:");
                    String productName = scan.nextLine();

                    System.out.println("Enter Product description:");
                    String productDesc = scan.nextLine();

                    registerProduct(productName,productDesc);
                    break;
                case 2:

                    System.out.println("Enter Retailer Name:");
                    String retailerName = scan.nextLine();
                    System.out.println("Enter Retailer Location:");
                    String retailerLocation = scan.nextLine();
                    registerRetailer(retailerName, retailerLocation);
                    break;

                case 3:
                    System.out.println("Enter Storage Location:");
                    String location = scan.nextLine();
                    File directory = new File("src/inventory/repository/"+location);
                    if (directory.mkdirs()) {
                        StorageLocation=location;
                        System.out.println("Storage location registered successfully!");
                    } else {
                        System.out.println("Failed to register new storage location!");
                    }
                    break;
                case 4:
                    System.out.println("Enter Product ID to add quantity:");
                    int addProductId = scan.nextInt();
                    System.out.println("Enter Quantity to Add:");
                    int addQuantity = scan.nextInt();

                    addProductQuantity(addProductId, addQuantity);
                    break;
                case 5:
                    System.out.println("Enter Product ID to remove quantity:");
                    int removeProductId = scan.nextInt();
                    System.out.println("Enter Quantity to Remove:");
                    int removeQuantity = scan.nextInt();

                    removeProductQuantity(removeProductId, removeQuantity);
                    break;

                case 6:
                    System.out.println("Enter Storage Location:");
                    StorageLocation = scan.nextLine();
                    validateStoreLoc(StorageLocation);
                    break;
                case 7:
                    System.out.println("Exiting program...");
                    exit = true;
                    App.prompt();
//                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scan.close();
    }

    private Boolean validateStoreLoc(String loc)
    {
        File directory = new File("src/inventory/repository/"+loc);
        // Check if the directory exists and is a directory
        if (!(directory.exists() && directory.isDirectory())) {
            System.out.println("The Storage location does not exist. Register the storage location if needed");
            return false;
        }
        return true;
    }


    // Method to register a new product
    public void registerProduct(String name, String desc) {
        getStorageInstance().registerProduct(name,desc);
    }

    // Method to register a new retailer
    public void registerRetailer(String name, String location) {
        getStorageInstance().registerRetailer(name, location);
    }


    public void addOrder(int rid) {

        getStorageInstance().addOrder(rid);

    }


    public void addProductQuantity(int productId, int quantity) {
        getStorageInstance().addProductCount(productId, quantity);
    }

    public void removeProductQuantity(int productId, int quantity) {
        getStorageInstance().removeProductCount(productId, quantity);
    }


    // Singleton method to get the Inventory instance
    private StorageManagement getStorageInstance() {
        if (storage == null) {
            storage = new BasicStorageManage(StorageLocation);
        }
        return storage;
    }

}

