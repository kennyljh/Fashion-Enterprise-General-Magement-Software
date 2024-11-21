package src.Sales.src.interfaces;

import java.util.Map;

public interface SalesController {

    public void run();
    public void registerRetailer(String name, String location);
    public void addOrder(int rid, Map<String, Integer> products);
    public void printReceipt(int orderId);
    public void returnOrder(int rid, int oid, Map<String, Integer> rproducts, int days);
    public void viewRetailers();
    public void viewAvaProducts();
    public void viewRegisteredProducts();
    public void changePrice(int price, String pname);
}
