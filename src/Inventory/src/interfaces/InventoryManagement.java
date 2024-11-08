package src.Inventory.src.interfaces;

import src.Inventory.src.Product;
import src.Inventory.src.*;

public interface InventoryManagement {
    void registerProduct(Product product);
    void registerRetailer(BasicInventManage.Retailer retailer);
    void confirmOrder(Order order);
}