package Inventory.src.interfaces;

import Inventory.src.Order;

public interface Storage {
    Boolean addOrder(Order order);
    Boolean updateProductCount(int productId, int quantityChange);
}