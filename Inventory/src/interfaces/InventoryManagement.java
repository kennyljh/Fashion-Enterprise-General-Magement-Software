package Inventory.src.interfaces;

import Inventory.src.Product;
import Inventory.src.*;

public interface InventoryManagement {
    void registerProduct(Product product);
    void registerRetailer(BasicInventManage.Retailer retailer);
    void confirmOrder(Order order);
}