package src.Manufacturing.src;

import src.App;
import src.Security.src.SecurityRequestScheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static src.App.prompt;

public class ManufacturingController {

    private ManufacturingManager manager;
    private HeadOfManufacturing headOfManufacturing;
    private Machines machine;
    private boolean isReady = false;
    private boolean productCreated = false;


    public ManufacturingController() {
        this.headOfManufacturing = new HeadOfManufacturing();
        this.machine = new Machines();
        this.manager = new ManufacturingManager();


    }

    public void run() throws Exception {

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Welcome to Manufacturing System");

        while (!exit) {
            System.out.println("Please enter your choice");
            System.out.println("1. Collect Raw Materials");
            System.out.println("2. Verify Raw Materials");
            System.out.println("3. Create Product");
            SecurityRequestScheduler scheduler = new SecurityRequestScheduler();
            scheduler.optionsPrint();
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine();
            Map<String, Integer> collectedMaterials = new HashMap<>();

            switch (choice) {
                case 1:
                    System.out.println("Collect the Raw Materials from Storage");


                    while (true) {
                        System.out.println("Enter 'done' to exit prompt at any time");
                        System.out.println("Select Type of Raw Material: ");
                        String rawMaterial = sc.nextLine();
                        if (rawMaterial.equals("done")) {
                            break;
                        }

                        System.out.println("Select Quantity of Raw Material: " + rawMaterial);
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        collectedMaterials.put(rawMaterial, quantity);
                        System.out.println("You have " + quantity + " items collected of " + rawMaterial);
                    }
                    manager.collectRawMaterials(collectedMaterials);
                    System.out.println("Raw Materials Collected:");
                    collectedMaterials.forEach((rawMaterial, quantity) -> {
                        System.out.println(quantity + " items of " + rawMaterial);
                    });
                    break;
                case 2:
                    System.out.println("Verify the Raw Materials from Storage");

                    if (manager.getCollectedMaterials() == null || manager.getCollectedMaterials().isEmpty()) {
//                        System.out.println(manager.getCollectedMaterials()); //displays all materials collected from 1
//                        headOfManufacturing.viewRawMaterials(manager.getCollectedMaterials());
                        System.out.println("You have no items collected yet.");
                        break;
                    }
//                    boolean selectedMaterial = false;
                    headOfManufacturing.viewRawMaterials(manager.getCollectedMaterials());
                    for (Map.Entry<String, Integer> entry : manager.getCollectedMaterials().entrySet()) {
                        String rawMaterial = entry.getKey();
                        int quantity = entry.getValue();

                        System.out.println("Do you want to verify " + quantity + " items of " + rawMaterial + "? (Y/N)");
                        String verifyRawMaterial = sc.nextLine();

                        if (verifyRawMaterial.equals("Y")) {
                            headOfManufacturing.selectRawMaterial(quantity, rawMaterial);
                            System.out.println("You have " + quantity + " items of " + rawMaterial + " verified.");
                            collectedMaterials.put(rawMaterial, quantity);
                            isReady = true;

                        } else if (verifyRawMaterial.equals("N")) {
                            System.out.println("Material " + rawMaterial + " is not verified");
                            isReady = false;
                        } else {
                            System.out.println("Invalid input, select Y or N");
                        }
                    }
                    break;
                case 3:

                    if (isReady) {
                        machine.startMachine();
                    }
                    if (!machine.isRunning()) {
                        System.out.println("Make sure to properly verify all the raw materials before beginning to create a product");
                        break;
                    }
                    System.out.println("Machine is running");
                    System.out.println("....................................................");
                    System.out.println("Manager is creating Product...");
                    System.out.println("....................................................");
                    productCreated = manager.createProduct(collectedMaterials);
                    if (productCreated) {
                        System.out.println("Product created. Deliver to head of manufacturing...");
                        String productName = manager.getProductName();
                        SimpleProduct product = new SimpleProduct(productName);
                        manager.deliverProduct(product);
                    } else {
                        System.out.println("Product creation failed, Try again");
                    }
                    break;

//                case 4:
//
//                    if (manager.getProduct() == null) {
//                        System.out.println("You have no items collected yet.");
//                        break;
//                    }
//                    //if product is not created break
//                    if (!productCreated) {
//                        System.out.println("Product is not created");
//                        break;
//                    }
//                    if (!isReady) {
//                        System.out.println("The machine is not ready to start, it needs to be verified first.");
//                        break;
//                    }
//
                case 111:
                    scheduler.addSecurityRequest();
                    break;

                case 112:
                    scheduler.showAllSecurityRequests();
                    break;

                case 113:
                    scheduler.deleteScheduleByID();
                    break;

                case 4:
                    System.out.println("Exit Program");
                    exit = true;
                    App.prompt();
//                    break;

                default:
                    System.out.println("Please enter a valid choice");
            }
        }

        sc.close();
    }
}
