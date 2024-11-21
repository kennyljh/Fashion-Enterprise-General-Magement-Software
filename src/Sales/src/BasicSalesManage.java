package src.Sales.src;

import src.Sales.SalesCommand;
import src.Sales.src.interfaces.Retailer;
import src.Sales.src.interfaces.SalesManagement;
import src.TextEditor.PoorTextEditor;

import java.io.File;
import java.util.*;

public class BasicSalesManage implements SalesManagement {

    // id to details
    private Map<String, Map<String, String>> retailers;

   // id to details
    private Map<String, Map<String, String>> orders;

    private SalesInventoryRequest sir;
    private String location;
    private String repoPath = "src/Sales/repository/";

    PoorTextEditor textEditor;

    public BasicSalesManage() {
        this.retailers = new HashMap<>();
        this.orders = new HashMap<>();
        location = "Main";

        set();
    }

    private void set() {
        String rPath = repoPath + "Retailers.txt";
        String oPath= repoPath+ "Orders.txt";

        try {
            File rFile = new File(rPath);
            if (rFile.exists()) {
                textEditor = new PoorTextEditor();
                textEditor.processTextFile(rPath);
                retailers = textEditor.getRepositoryString();
            }
        } catch (Exception e) {
            System.out.println("Error processing Retailers.txt");

        }
        try {
            File oFile = new File(rPath);
            if (oFile.exists()) {
                textEditor = new PoorTextEditor();
                textEditor.processTextFile(oPath);
                orders = textEditor.getRepositoryString();
            }
        } catch (Exception e) {
            System.out.println("Error processing Retailers.txt");
        }
    }

    public void addOrder(int rid, Map<String, Integer> products) {

        int size= orders.size();
        sir =new SalesInventoryRequest();
        String id = String.valueOf(rid);
        if (retailers.containsKey(id)) {

          Map<String,String> odetail= sir.addPToOrder(products);
          odetail.put("retailer id", String.valueOf(rid));
          orders.put(String.valueOf(size+1),odetail);

           System.out.println("order placed by id: " + size+1);

           System.out.println("use command to print receipt if needed using the order id");
        } else {
            System.out.println("Retailer id does not exist. Register the retailer!");
        }


    }

    public void returnOrder(int rid, int oid, Map<String, Integer> rproducts, int days) {
        String rId = String.valueOf(rid);
        String oId = String.valueOf(oid);
        if (retailers.containsKey(rId)) {

            if (orders.containsKey(oId)) {
                if(checkReturnTime(days,oid))
                {
                 sir.removePToOrder(rproducts);
                 System.out.println("return successful and products sent to inventory");
                }else{
                    System.out.println("Order passes return period co cannot be returned");
                }

            } else {
                System.out.println("Order id does not exist!");
            }
        } else {
            System.out.println("Retailer id does not exist!");
        }
    }

    public void registerRetailer(String name, String retailerLocation) {

        Retailer rd = new BasicRetailer(name, retailerLocation);
        String size = String.valueOf(retailers.size() + 1);

        retailers.put(size, rd.getRDetails());

        textEditor = new PoorTextEditor();
        textEditor.setRepositoryStrings(retailers);
        textEditor.writeToTextFile(repoPath + "Retailers.txt");
        rd.print();

        System.out.println("Retailer registered with id:" + size);
    }

    public boolean checkReturnTime(int days, int orderId) {
        return days <= 15;
    }


    public void printReceipt(int orderId)
    {
        Map<String, String> odetails = new HashMap<>(orders.get(String.valueOf(orderId)));
        sir.receiptPrint(odetails);
    }


    public void viewRetailers()
    {
        print(retailers, "Registered Retailers");
    }

    public void print(Map<String, Map<String, String>> items, String whichItem) {
        System.out.println("-----------------------------------------------------");
        System.out.println(whichItem);
        for (Map.Entry<String, Map<String, String>> itms : items.entrySet()) {
            System.out.println(itms.getKey() + ":");
            for (Map.Entry<String, String> sitm : itms.getValue().entrySet()) {
                System.out.printf("%-30s %s%n", sitm.getKey(), sitm.getValue());
            }
        }
        System.out.println("-----------------------------------------------------");
    }
}
