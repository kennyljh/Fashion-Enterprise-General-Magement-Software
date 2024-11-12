package src.Inventory.src.interfaces;

public interface InventoryController {

	void run();
	void registerProduct(int id, String name, double price);
	void registerRetailer(String name, String location);
	void addOrder(int id);
	void updateProductQuantity(int productId, int quantity, String location);
}
