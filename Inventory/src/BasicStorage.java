package Inventory.src;

import Inventory.src.interfaces.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mani Raj
 */
public class BasicStorage implements Storage {
	private String location;
	private Map<Integer, Integer> availableProducts; // Product ID mapped to Quantity
	private List<Order> orderList;

	public BasicStorage() {

	}

	public BasicStorage(String location) {
		this.location = location;
		this.availableProducts = new HashMap<>();
		this.orderList = new ArrayList<>();
	}

	@Override
	public Boolean addOrder(Order order) {
		orderList.add(order);
		return true;
	}

	@Override
	public Boolean updateProductCount(int productId, int quantityChange) {
		availableProducts.put(productId, availableProducts.getOrDefault(productId, 0) + quantityChange);
		return true;
	}

	// Getters
	public String getLocation() {
		return location;
	}

	public Map<Integer, Integer> getAvailableProducts() {
		return availableProducts;
	}
}
