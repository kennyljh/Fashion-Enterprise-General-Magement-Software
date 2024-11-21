package src;

import src.Design.DesignDepartment;
import src.HR.HRDepartment;
import src.Inventory.InventoryDepartment;
import src.Manufacturing.ManufacturingDepartment;
import src.Marketing.MarketingDepartment;
import src.Modeling.ModelingDepartment;
import src.Treasury.TreasuryDepartment;

import java.util.Scanner;

public class App {
    public static ModelingDepartment modelingDepartment = new ModelingDepartment();
    public static MarketingDepartment marketingDepartment = new MarketingDepartment();
    public static ManufacturingDepartment manufacturingDepartment = new ManufacturingDepartment();
    public static HRDepartment hrDepartment = new HRDepartment();
    public static DesignDepartment designDepartment = new DesignDepartment();
    public static InventoryDepartment inventoryDepartment = new InventoryDepartment();
    public static TreasuryDepartment treasuryDepartment = new TreasuryDepartment();

    public static void main(String[] args) throws Exception {
        prompt();
    }

    public static void prompt() throws Exception {
        Scanner s = new Scanner(System.in);

        System.out.println("--WELCOME TO FASHION EMPIRE--\n" +
                " You are logged in:\n" +
                " Which Department would you like to go to?"
                + "\n 1: HR Department"
                + "\n 2: Treasury"
                + "\n 3: Manufacturing"
                + "\n 4: Modeling"
                + "\n 5: Inventory"
                + "\n 6: Design"
                + "\n 7: Marketing");
        int choice = s.nextInt();

        switch (choice){
            case 1:
                hrDepartment.start();
                break;
            case 2:
                treasuryDepartment.start();
                break;
            case 3:
                manufacturingDepartment.start();
                break;
            case 4:
                modelingDepartment.start();
                break;
            case 5:
                inventoryDepartment.start();
                break;
            case 6:
                designDepartment.start();
                break;
            case 7:
//                marketingDepartment.start();
                break;
        }
    }
}


