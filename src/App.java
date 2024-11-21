package src;

import src.Design.DesignDepartment;
import src.HR.HRDepartment;
import src.Inventory.InventoryDepartment;
import src.Manufacturing.ManufacturingDepartment;
import src.Modeling.ModelingDepartment;
import src.Sales.SalesDepartment;
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
        prompt();
    }

    public static void prompt() throws Exception {
        Scanner s = new Scanner(System.in);

        System.out.println("""
                --WELCOME TO FASHION EMPIRE--
                 You are logged in:\s
                 Which Department would you like to go to?\
                
                1: HR Department\
                
                2: Treasury\
                
                3: Manufacturing\
                
                4: Modeling\
                
                5: Inventory\
                
                6: Design
                
                7: Sales""");
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
                SalesDepartment.start();
                break;
        }
    }
}


