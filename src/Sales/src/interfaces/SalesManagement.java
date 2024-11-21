package src.Sales.src.interfaces;

import java.util.Map;

/**
 * @author Mani Raj
 */
public interface SalesManagement {

    public void addOrder(int rid, Map<String, Integer> products);

    public void registerRetailer(String name, String retailerLocation);

    public void returnOrder(int rid, int oid, Map<String, Integer> rproducts, int days);

    public void printReceipt(int orderId);

    public void viewRetailers();

}
