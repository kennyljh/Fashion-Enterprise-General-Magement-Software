package src.Inventory.src;

/**
 * @ Mani Raj
 */
public class BasicProductDescription implements BasicInventManage.ProductDescription {

	    private String description;

	    public BasicProductDescription(String description) {
	        this.description = description;
	    }

	    public String getDescription() {
	        return description;
	    }
	}
