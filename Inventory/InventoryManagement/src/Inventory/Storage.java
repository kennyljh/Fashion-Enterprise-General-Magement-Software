package Inventory;

public interface Storage {
    Boolean addOrder(Order order);
    Boolean updateProductCount(int productId, int quantityChange);
}