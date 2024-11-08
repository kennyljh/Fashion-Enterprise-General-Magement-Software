package src.Inventory.src.interfaces;

import src.Inventory.src.Order;

public interface Storage {
    Boolean addOrder(Order order);
    Boolean updateProductCount(int productId, int quantityChange);
}