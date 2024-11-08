package Inventory;

public interface InventoryManagement {
    void registerProduct(Product product);
    void registerRetailer(Retailer retailer);
    void confirmOrder(Order order);
}