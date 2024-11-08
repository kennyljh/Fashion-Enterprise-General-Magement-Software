package src.Inventory.src;

/**
 * @ Mani Raj
 */

import src.Inventory.src.interfaces.*;

import java.util.Scanner;

/**
 * Handles user interaction for inventory management.
 * Provides options to register products and retailers,
 * and to add or remove product quantities from storage.
 */
public class BasicInventoryController implements InventoryController {

    private BasicInventManage inventory = null;
    private BasicStorage store=null;

    public BasicInventoryController() {}

    /**
     * Runs the inventory management system.
     */
    public void run() {
        System.out.println("Welcome to the src.Inventory Management System");

        Scanner scan = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Register Product");
            System.out.println("2. Register Retailer");
            System.out.println("3. Add Product Quantity");
            System.out.println("4. Remove Product Quantity");
            System.out.println("5. Exit Program");

            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Product ID:");
                    int productId = scan.nextInt();
                    scan.nextLine(); // Consume newline
                    System.out.println("Enter Product Name:");
                    String productName = scan.nextLine();
                    System.out.println("Enter Product Price:");
                    double productPrice = scan.nextDouble();
                    registerProduct(productId, productName, productPrice);
                    break;
                case 2:
                    System.out.println("Enter Retailer ID:");
                    int retailerId = scan.nextInt();
                    scan.nextLine(); // Consume newline
                    System.out.println("Enter Retailer Name:");
                    String retailerName = scan.nextLine();
                    System.out.println("Enter Retailer Location:");
                    String retailerLocation = scan.nextLine();
                    registerRetailer(retailerName, retailerLocation);
                    break;
                case 3:
                    System.out.println("Enter Product ID to add quantity:");
                    int addProductId = scan.nextInt();
                    System.out.println("Enter Quantity to Add:");
                    int addQuantity = scan.nextInt();
                    scan.nextLine(); // Consume newline
                    System.out.println("Enter Storage Location:");
                    String addLocation = scan.nextLine();
                    updateProductQuantity(addProductId, addQuantity, addLocation);
                    break;
                case 4:
                    System.out.println("Enter Product ID to remove quantity:");
                    int removeProductId = scan.nextInt();
                    System.out.println("Enter Quantity to Remove:");
                    int removeQuantity = scan.nextInt();
                    scan.nextLine(); // Consume newline
                    System.out.println("Enter Storage Location:");
                    String removeLocation = scan.nextLine();
                    updateProductQuantity(removeProductId, removeQuantity, removeLocation);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scan.close();
    }

    // Method to register a new product
    public void registerProduct(int id, String name, double price) {
        getInventoryInstance().registerProduct(new Product(id, name, price));
        System.out.println("Product registered successfully: " + name);
    }

    // Method to register a new retailer
    public void registerRetailer(String name, String location) {
        getInventoryInstance().registerRetailer(new BasicRetailer(name, location));
        System.out.println("Retailer registered successfully: " + name);
    }

    // Method to add product quantity
    public void addOrder(int id) {
        boolean success = getStorageInsatnce().addOrder(new Order(id));
        if (success) {
            System.out.println("order added");
        } else {
            System.out.println("Failed to add product quantity. Check if the product or location exists.");
        }
    }

    // Method to remove product quantity
    public void updateProductQuantity(int productId, int quantity, String location) {
        boolean success = getStorageInsatnce().updateProductCount(productId, quantity);
        if (success) {
            System.out.println("updated " + quantity + " units of product ID " + productId + " from storage at " + location);
        } else {
            System.out.println("Failed to remove product quantity. Check if the product or location exists and has sufficient quantity.");
        }
    }

    // Singleton method to get the src.Inventory instance
    private InventoryManagement getInventoryInstance() {
        if (inventory == null) {
            inventory = new BasicInventManage();
        }
        return inventory;
    }

    private Storage getStorageInsatnce()
    {
        if (store == null) {
            store= new BasicStorage();
        }
        return store;
    }
}

