package src.Inventory.src;

import java.util.HashMap;
import java.util.Map;

import txedt.PoorTextEditor;

/**
 * @ Mani Raj
 */
public class Order {
	private int id;
	private Map<Integer, Integer> productList; // productId mapped to quantity

	private String path="";
	
	public Order(int id) {
		this.id = id;
		this.productList = new HashMap<>();
	}

	public void addProduct(int productId, int quantity) {
		productList.put(productId, quantity);
		// Update the text file
		PoorTextEditor textEditor = new PoorTextEditor();
		textEditor.setValue("Order" + id, "product" + productId, String.valueOf(quantity));
		textEditor.writeToTextFile(path);
	}

	public void deleteProduct(int productId) {
		productList.remove(productId);
		// Update the text file
		PoorTextEditor textEditor = new PoorTextEditor();
		textEditor.setValue("Order" + id, "product" + productId, null);
		textEditor.writeToTextFile(path);
	}

	public Map<Integer, Integer> getProductList() {
		return productList;
	}

	public void setPath(String filePath)
	{
		path =filePath;
	}
	public int getId() {
		return id;
	}
}