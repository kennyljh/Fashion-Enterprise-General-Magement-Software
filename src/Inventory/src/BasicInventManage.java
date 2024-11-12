package src.Inventory.src;

/**
 * @ Mani Raj
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import src.Inventory.src.interfaces.InventoryManagement;
import txedt.PoorTextEditor;

public class BasicInventManage implements InventoryManagement {
	private List<Product> products;
	private List<Retailer> retailers;
	private List<Storage> storages;

	private String path = "";

	public BasicInventManage() {

		this.products = new ArrayList<>();
		this.retailers = new ArrayList<>();
		this.storages = new ArrayList<>();

	}

	@Override
	public void registerProduct(Product product) {
		products.add(product);
		// Update the text file
		PoorTextEditor textEditor = new PoorTextEditor();
		textEditor.setValue("Product" + product.getId(), "name", product.getName());
		textEditor.setValue("Product" + product.getId(), "price", String.valueOf(product.getPrice()));
		textEditor.writeToTextFile(path );
	}

	@Override
	public void registerRetailer(Retailer retailer) {
		retailers.add(retailer);
		// Update the text file
		PoorTextEditor textEditor = new PoorTextEditor();
		textEditor.setValue("Retailer" + retailer.getName(), "location", retailer.getLocation());
		textEditor.writeToTextFile(path );
	}

	@Override
	public void confirmOrder(Order order) {
		double total = 0;
		for (Map.Entry<Integer, Integer> entry : order.getProductList().entrySet()) {
			Product product = getProductById(entry.getKey());
			if (product != null) {
				total += product.getPrice() * entry.getValue();
			}
		}
		System.out.println("Order ID: " + order.getId() + " Total: $" + total);
		// Update the text file
		PoorTextEditor textEditor = new PoorTextEditor();
		textEditor.setValue("Order" + order.getId(), "total", String.valueOf(total));
		textEditor.writeToTextFile(path );
	}

	private Product getProductById(int productId) {
		for (Product product : products) {
			if (product.getId() == productId) {
				return product;
			}
		}
		return null;
	}
	public void setPath(String filePath)
	{
		path =filePath;
	}

	public static interface InventoryController {

		void registerProduct(int id, String name, double price);
		void registerRetailer(String name, String location);
		void addOrder(int id);
		void updateProductQuantity(int productId, int quantity, String location);
	}

	public static interface InventoryManagement {
		void registerProduct(Product product);
		void registerRetailer(Retailer retailer);
		void confirmOrder(Order order);
	}

	public static interface ProductDescription {

		String getDescription();
	}

	public static interface Retailer {

		String getName();
		String getLocation();

	}

	public static interface Storage {
		Boolean addOrder(Order order);
		Boolean updateProductCount(int productId, int quantityChange);
	}
}
