package src.Inventory.src;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import src.Inventory.src.interfaces.Retailer;
import src.Inventory.src.interfaces.Storage;
import src.TextEditor.PoorTextEditor;
import src.Inventory.src.interfaces.InventoryManagement;


/**
 * @ Mani Raj
 */
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
		textEditor.setValue("src.Inventory.src.Product" + product.getId(), "name", product.getName());
		textEditor.setValue("src.Inventory.src.Product" + product.getId(), "price", String.valueOf(product.getPrice()));
		textEditor.writeToTextFile(path );
	}

	@Override
	public void registerRetailer(Retailer retailer) {
		retailers.add(retailer);
		// Update the text file
		PoorTextEditor textEditor = new PoorTextEditor();
		textEditor.setValue("src.Inventory.src.interfaces.Retailer" + retailer.getName(), "location", retailer.getLocation());
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
		System.out.println("src.Inventory.src.Order ID: " + order.getId() + " Total: $" + total);
		// Update the text file
		PoorTextEditor textEditor = new PoorTextEditor();
		textEditor.setValue("src.Inventory.src.Order" + order.getId(), "total", String.valueOf(total));
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
}
