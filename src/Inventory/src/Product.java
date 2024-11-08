package src.Inventory.src;

/**
 * @ Mani Raj
 */
public class Product {
	  private int id;
	    private String name;
	    private double price;
	    private BasicInventManage.ProductDescription productDescription;

	    public Product(int id, String name, double price) {
	        this.id = id;
	        this.name = name;
	        this.price = price;
	        
	    }
	    
	    public void setProductDescription( BasicInventManage.ProductDescription productDescription)
	    {
	    	this.productDescription = productDescription;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public BasicInventManage.ProductDescription getProductDescription() {
	        return productDescription;
	    }
}
