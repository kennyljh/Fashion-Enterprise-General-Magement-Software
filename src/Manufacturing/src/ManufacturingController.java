package src.Manufacturing.src;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManufacturingController {

    private ManufacturingManager manager;
    private HeadOfManufacturing headOfManufacturing;
    private Machines machine;

    private boolean productCreated = false;


    public ManufacturingController() {
        this.headOfManufacturing = new HeadOfManufacturing();
        this.machine = new Machines();
        this.manager = new ManufacturingManager();


    }

    public void run() {

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Welcome to Manufacturing System");

        while (!exit) {
            System.out.println("Please enter your choice");
            System.out.println("1. Collect Raw Materials");
            System.out.println("2. Verify Raw Materials");
            System.out.println("3. Create Product");
            System.out.println("4. Verify Product");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Collect the Raw Materials from Storage");
                    Map<String, Integer> collectedMaterials = new HashMap<>();

                    while (true) {
                        System.out.println("Select Type of Raw Material: ");
                        String rawMaterial = sc.nextLine();
                        if (rawMaterial.equals("done")) {
                            break;
                        }

                        System.out.println("Select Quantity of Raw Material: " + rawMaterial);
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        collectedMaterials.put(rawMaterial, quantity);
                        System.out.println("You have " + quantity + " Raw Materials Collected");
                    }
                    manager.collectRawMaterials(collectedMaterials);
                    System.out.println("Raw Materials Collected");
                    collectedMaterials.forEach((rawMaterial, quantity) -> {
                        System.out.println(rawMaterial + ": " + quantity);
                    });
                    break;
                case 2:
                    System.out.println("Verify the Raw Materials from Storage");
                    //display the rawMaterials that were collected
                    headOfManufacturing.viewRawMaterials(manager.getCollectedMaterials());
                    for(Map.Entry<String, Integer> entry : manager.getCollectedMaterials()) {
                        String rawMaterial = entry.getKey();
                        Integer quantity = entry.getValue();
                        boolean isVerified = headOfManufacturing.selectRawMaterial(quantity, rawMaterial);
                        System.out.println("(Y/N) do the raw materials match the specifications?");
                        String verifyRawMaterial = sc.nextLine();
                        if (verifyRawMaterial.equals("Y")) {
                           System.out.println("You have " + quantity + " Raw Materials" + rawMaterial + "Collected");

                        }else if(verifyRawMaterial.equals("N")) {
                            System.out.println("Material " + rawMaterial + " is not verified");
                            break;
                        }
                        else{
                            System.out.println("Invalid input, select Y or N");
                        }
                    }
                    break;
                case 3:

                    if (!machine.isRunning()) {
                        System.out.println("Machine is not running, start the machine first");
                        break;
                    }
                    System.out.println("Machine is running. Manager is creating product...");
                    productCreated = manager.createProduct();
                    if (productCreated) {
                        System.out.println("Product created. Deliver to head of manufacturing...");
                        manager.deliverProduct(headOfManufacturing.verifyProudct(productCreated));
                    } else {
                        System.out.println("Product creation failed, Try again");
                    }

                    break;

                case 4:
                    System.out.println("Verify the product that was created.");
                    //if product is not created break
                    if (!productCreated) {
                        System.out.println("Product is not created");
                        break;
                    }
                    //check to see if the product is up standard and verify it as the head of manufacturing.
                    System.out.println("Verifying the product...");


                default:
            }
        }

        sc.close();
    }
}
