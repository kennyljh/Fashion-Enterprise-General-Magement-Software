package src;

import src.Design.DesignDepartment;
import src.HR.HRDepartment;
import src.Inventory.InventoryDepartment;
import src.Manufacturing.ManufacturingDepartment;
import src.Modeling.ModelingDepartment;
import src.Treasury.TreasuryDepartment;

import java.util.Scanner;

public class App {
    public static ModelingDepartment modelingDepartment = new ModelingDepartment();
    public static ManufacturingDepartment manufacturingDepartment = new ManufacturingDepartment();
    public static HRDepartment hrDepartment = new HRDepartment();
    public static DesignDepartment designDepartment = new DesignDepartment();
    public static InventoryDepartment inventoryDepartment = new InventoryDepartment();
    public static TreasuryDepartment treasuryDepartment = new TreasuryDepartment();

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);

        System.out.println("--WELCOME TO FASHION EMPIRE--\n You are logged in as: IT SPECIALIST\n\nWhich Department would you like to go to?" +
                "\n1: HR Department" +
                "\n2: Treasury" +
                "\n3: Manufacturing" +
                "\n4: Modeling" +
                "\n5: Inventory" +
                "\n6: Design");
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
        }

    }
}


