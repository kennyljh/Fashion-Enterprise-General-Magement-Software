import Design.DesignDepartment;
import HR.HRDepartment;
import Inventory.InventoryDepartment;
import Manufacturing.ManufacturingDepartment;
import Modeling.ModelingDepartment;
import Treasury.TreasuryDepartment;

import java.util.Scanner;

public class StartingApplication {
    static ModelingDepartment modelingDepartment = new ModelingDepartment();
    static ManufacturingDepartment manufacturingDepartment = new ManufacturingDepartment();
    static HRDepartment hrDepartment = new HRDepartment();
    static DesignDepartment designDepartment = new DesignDepartment();
    static InventoryDepartment inventoryDepartment = new InventoryDepartment();
    static TreasuryDepartment treasuryDepartment = new TreasuryDepartment();

    public static void main(String[] args) {
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


