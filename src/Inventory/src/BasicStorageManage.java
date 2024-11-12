package src.Inventory.src;
import java.io.File;
import java.util.*;

import src.TextEditor.PoorTextEditor;
import src.Inventory.src.interfaces.ProductDescription;
import src.Inventory.src.interfaces.Retailer;
import src.Inventory.src.interfaces.StorageManagement;


/**
 * @ Mani Raj
 */
public class BasicStorageManage implements StorageManagement {

	Map<String, Map<String,String>> products;

	private Map<String, Map<String,String>> retailers;

	private String location;

	// name to details
	private Map<String, Map<String,String>> availableProducts;


	private List<Order> orderList;


	PoorTextEditor textEditor = new PoorTextEditor();

	private String repoPath = "src/inventory/repository/";

	public BasicStorageManage(String storageLoc) {

		repoPath= repoPath+storageLoc+"/";


		this.products = new HashMap<String, Map<String,String>>();
		this.retailers = new HashMap<>();
		this.location = storageLoc;
		this.availableProducts = new HashMap<>();
		this.orderList = new ArrayList<>();

		set();
	}


	private void set()
	{

		String pPath=repoPath + "Products.txt";
		String rPath=repoPath + "Retailers.txt";
		String aPPath=repoPath + "AvailableProducts.txt";

		try {
			File pFile=new File(pPath);
			if(pFile.exists()) {
				textEditor.processTextFile(repoPath + "Products.txt");
				products = textEditor.getRepositoryString();
			}
		} catch (Exception e) {
			System.out.println("Error processing Products.txt ");

		}
		try {
			File rFile=new File(rPath);
			if(rFile.exists()) {
				textEditor.processTextFile(rPath);
				products = textEditor.getRepositoryString();
			}
		} catch (Exception e) {
			System.out.println("Error processing Retailers.txt" );

		}
		try {
			File aPPFile=new File(aPPath);
			if(aPPFile.exists()) {
				textEditor.processTextFile(aPPath);
				products = textEditor.getRepositoryString();
			}
		} catch (Exception e) {
			System.out.println("Error processing AvailableProducts.txt " );
		}
	}




	@Override
	public void addOrder(int rid) {

		String id=String.valueOf(rid);
		if(retailers.containsKey(id))
		{


		}
		else{
			System.out.println("Retailer id does not exist. Register the retailer!");
		}
	}


	//update product count in storage
	@Override
	public void addProductCount(int productId, int quantityChange) {
		String pid= String.valueOf(productId);
		if(products.containsKey(pid))
		{
			String pname= products.get(pid).get("name");
			if(availableProducts.containsKey(pname))
			{
				int ncount = Integer.parseInt(availableProducts.get(pname).get("count"));
				ncount=ncount+quantityChange;
				availableProducts.get(pname).put("count",String.valueOf(ncount));
			}else{
				System.out.println("the product with the given id not in the available products." +
						"To add first restore the product details as below");
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter price of the product:");
				double price = sc.nextDouble();
				Product p=new Product(productId,price,quantityChange);
				availableProducts.put(pname,p.getPdetails());
			}


			textEditor.setRepositoryStrings(availableProducts);
			textEditor.writeToTextFile(repoPath+"AvailableProducts.txt");
		}
		else{
			System.out.println("Product Id does not exists. Register Product to add!");
		}

	}

	public void removeProductCount(int productId, int quantityChange) {
		String pid= String.valueOf(productId);
		if(products.containsKey(pid))
		{
			String pname= products.get(pid).get("name");
			if(availableProducts.containsKey(pname))
			{
				int ncount = Integer.parseInt(availableProducts.get(pname).get("count"));
				ncount=ncount-quantityChange;
				if(ncount<0)
				{
					System.out.println("quantity removal exceeds the available quantity");
				}
				else
				{
					if(ncount==0) {
						availableProducts.remove(pname);
						System.out.println("removal finished and "+pname+" no longer available in the stock");
					}
					else{
						availableProducts.get(pname).put("count",String.valueOf(ncount));
					}
					textEditor.setRepositoryStrings(availableProducts);
					textEditor.writeToTextFile(repoPath+"AvailableProducts.txt");
				}

			}else{
				System.out.println("product with requested Id is not available");
			}


		}
		else{
			System.out.println("Product Id does not exists. Register Product to add!");
		}

	}


	@Override
	public String registerProduct(String name, String Desc) {

		ProductDescription pd=new BasicProductDescription(name, Desc);
		String size= String.valueOf(products.size()+1);

		products.put(size,pd.productDetails());

		textEditor.setRepositoryStrings(products);
		textEditor.writeToTextFile(repoPath+"Products.txt");

		pd.print();
		return "Product registered with id:"+size;
	}

	@Override
	public String registerRetailer(String name, String retailerLocation) {

		Retailer rd=new BasicRetailer(name,retailerLocation);
		String size= String.valueOf(retailers.size()+1);

		retailers.put(size,rd.getRDetails());

		textEditor.setRepositoryStrings(retailers);
		textEditor.writeToTextFile(repoPath+"Retailers.txt");
		rd.print();
		return "Retailer registered with id:"+size;
	}

	public Map<String, Map<String,String>> getAvailableProducts() {
		return availableProducts;
	}

	public void print ()
	{

	}

}
