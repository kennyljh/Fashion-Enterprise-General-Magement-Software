package src.Sales.src;

import src.Inventory.src.BasicStorageManage;
import src.Inventory.src.interfaces.StorageManagement;
import src.Sales.SalesCommand;
import src.Sales.src.interfaces.SalesController;
import src.Sales.src.interfaces.SalesManagement;

import java.util.Map;
import java.util.Scanner;

public class BasicSalesController implements SalesController {
    SalesManagement sale=new BasicSalesManage();
    SalesInventoryRequest sir=new SalesInventoryRequest();

    public BasicSalesController()
    {
//        sale =new BasicSalesManage();
//        sir=new SalesInventoryRequest();
    }



    public void run()
    {
        SalesCommand scd=new SalesCommand();

        System.out.println("Welcome to the Sales Management System");

        System.out.println("Enter 'help' to see available commands");
        System.out.println("Enter 'exit' to leave the sales Management System");
        System.out.println("Enter the command for your task");

        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter command: ");
            String command = sc.nextLine();
            scd.perform(command);
        }
    }

    public void changePrice(int price, String pname)
    {
        sir.changePrice(price,pname);
    }

    public void registerRetailer(String name, String location) {
        sale.registerRetailer(name, location);
    }


    public void addOrder(int rid, Map<String, Integer> products) {
        sale.addOrder(rid, products);
    }

    public void printReceipt(int orderId)
    {
       sale.printReceipt(orderId);
    }
    public void returnOrder(int rid, int oid, Map<String, Integer> rproducts, int days)
    {
       sale.returnOrder(rid,oid,rproducts,days);
    }

    public void viewRetailers()
    {
        sale.viewRetailers();
    }

    public void viewAvaProducts()
    {
        sir.viewAvaProducts();
    }

    public void viewRegisteredProducts()
    {
        sir.viewProducts();
    }


//    private SalesManagement getSalesInstance() {
//        if (sale == null) {
//            sale = new BasicSalesManage();
//        }
//        return sale;
//    }

}
