package src.Sales;

import src.App;
import src.Sales.src.BasicSalesController;
import src.Sales.src.interfaces.SalesController;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesCommand {
    Map<String, Runnable> commands = new HashMap<>();
    SalesController sc = new BasicSalesController();
    private void setCommands() {
        Scanner scan = new Scanner(System.in);

        commands.put("Register Retailer", () -> {
            System.out.println("Enter Retailer Name:");
            String retailerName = scan.nextLine();
            System.out.println("Enter Retailer Location:");
            String retailerLocation = scan.nextLine();
            sc.registerRetailer(retailerName, retailerLocation);
        });
        commands.put("Add Order", () -> {
            System.out.println("Enter Retailer id:");
            int rid = scan.nextInt();
            scan.nextLine();
            Map<String, Integer> products = new HashMap<>();
            boolean flag = true;
            while (flag) {
                System.out.println("Enter product name:");
                String pname = scan.nextLine();
                System.out.println("Enter quantity");
                int quan = scan.nextInt();
                scan.nextLine();
                products.put(pname, quan);

                System.out.println("Do you want to still add (yes/no):");
                String response = scan.nextLine();

                if (response.equals("no")) {
                    flag = false;
                }
            }
            sc.addOrder(rid, products);
        });
        commands.put("Print Receipt", () -> {
            System.out.println("Enter Order id:");
            int oid = scan.nextInt();
            scan.nextLine();
            sc.printReceipt(oid);
        });
        commands.put("Change Price", () -> {
            System.out.println("Enter Product Name:");
            String pname = scan.nextLine();
            System.out.println("Enter new price");
            int price = scan.nextInt();
            scan.nextLine();

            sc.changePrice(price, pname);
        });
        commands.put("View Retailers", () -> {
            sc.viewRetailers();
        });
        commands.put("View Registered Products", () -> {
            sc.viewRegisteredProducts();
        });
        commands.put("View Available Products", () -> {
            sc.viewAvaProducts();
        });
        commands.put("Return Products", () -> {
            System.out.println("Enter Retailer id:");
            int rid = scan.nextInt();
            scan.nextLine();
            System.out.println("Enter order id:");
            int oid = scan.nextInt();
            scan.nextLine();
            System.out.println("How many days has it been since the purchase? :");
            int days = scan.nextInt();
            scan.nextLine();
            Map<String, Integer> products = new HashMap<>();
            boolean flag = true;
            while (flag) {
                System.out.println("Enter product name:");
                String pname = scan.nextLine();
                System.out.println("Enter quantity");
                int quan = scan.nextInt();
                scan.nextLine();
                products.put(pname, quan);

                System.out.println("Do you want to still add (yes/no):");
                String response = scan.nextLine();

                if (response.equals("no")) {
                    flag = false;
                }
            }
            sc.returnOrder(rid, oid, products, days);
        });


        commands.put("help", () -> {
            System.out.println(" 'Register Retailer', 'Add Order', 'Print Receipt', 'Change Price', " + "'View Retailers', " +
                    "'View Registered Products','View Available Products', 'Return Products' ");
        });

        commands.put("exit", () -> {
            try {
                App.prompt();
            } catch (Exception e) {

            }
        });
    }
    public void perform(String command) {
        Runnable action = commands.get(command.trim());
        if (action != null) {
            action.run();
        } else {
            System.out.println("Unknown command: " + command);
        }
    }
}
