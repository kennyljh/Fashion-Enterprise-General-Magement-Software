package src.Inventory.src.interfaces;

import src.Inventory.src.Order;
import src.Inventory.src.Product;

public interface InventoryManagement {
    void registerProduct(Product product);
    void registerRetailer(Retailer retailer);
    void confirmOrder(Order order);
}