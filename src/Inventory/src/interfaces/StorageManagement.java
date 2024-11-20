package src.Inventory.src.interfaces;

import src.Inventory.src.Order;

public interface StorageManagement {
    String registerProduct(String name, String Desc);
    String registerRetailer(String name, String retailerLocation);

    void addOrder(int rid);

    // Boolean modifyOrder(Order order);

    void addProductCount(String pname, int quantityChange);

    void removeProductCount(String pname, int quantityChange);

}