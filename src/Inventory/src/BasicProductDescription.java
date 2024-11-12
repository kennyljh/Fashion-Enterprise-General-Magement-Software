package src.Inventory.src;
import src.Inventory.src.interfaces.ProductDescription;
/**
 * @ Mani Raj
 */
public class BasicProductDescription implements ProductDescription {

	    private String description;

	    public BasicProductDescription(String description) {
	        this.description = description;
	    }

	    public String getDescription() {
	        return description;
	    }
	}
