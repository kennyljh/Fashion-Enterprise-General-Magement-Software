package src.Inventory.src;

/**
 * @ Mani Raj
 */
public class BasicRetailer implements BasicInventManage.Retailer {
	private String name;
	private String location;

	public BasicRetailer(String name, String location) {
		this.name = name;
		this.location = location;
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}
}
