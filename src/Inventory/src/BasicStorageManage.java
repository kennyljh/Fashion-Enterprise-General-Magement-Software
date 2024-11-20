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

    Map<String, Map<String, String>> products;

    private Map<String, Map<String, String>> retailers;

    private String location;

    // name to details
    private Map<String, Map<String, String>> availableProducts;


    private List<Order> orderList;


    PoorTextEditor textEditor = new PoorTextEditor();

    private String repoPath = "src/inventory/repository/";

    public BasicStorageManage(String storageLoc) {

        repoPath = repoPath + storageLoc + "/";


        this.products = new HashMap<String, Map<String, String>>();
        this.retailers = new HashMap<>();
        this.location = storageLoc;
        this.availableProducts = new HashMap<>();
        this.orderList = new ArrayList<>();

        set();
    }

	public BasicStorageManage() {

		repoPath = repoPath + "Main" + "/";

		this.products = new HashMap<String, Map<String, String>>();
		this.retailers = new HashMap<>();
		this.location = "Main";
		this.availableProducts = new HashMap<>();
		this.orderList = new ArrayList<>();
		set();
	}


    private void set() {

        String pPath = repoPath + "Products.txt";
        String rPath = repoPath + "Retailers.txt";
        String aPPath = repoPath + "AvailableProducts.txt";

        try {
            File pFile = new File(pPath);
            if (pFile.exists()) {
                textEditor.processTextFile(repoPath + "Products.txt");
                products = textEditor.getRepositoryString();
            }
        } catch (Exception e) {
            System.out.println("Error processing Products.txt ");

        }
        try {
            File rFile = new File(rPath);
            if (rFile.exists()) {
                textEditor.processTextFile(rPath);
                products = textEditor.getRepositoryString();
            }
        } catch (Exception e) {
            System.out.println("Error processing Retailers.txt");

        }
        try {
            File aPPFile = new File(aPPath);
            if (aPPFile.exists()) {
                textEditor.processTextFile(aPPath);
                products = textEditor.getRepositoryString();
            }
        } catch (Exception e) {
            System.out.println("Error processing AvailableProducts.txt ");
        }
    }


    @Override
    public void addOrder(int rid) {

        String id = String.valueOf(rid);
        if (retailers.containsKey(id)) {


        } else {
            System.out.println("Retailer id does not exist. Register the retailer!");
        }
    }


    //update product count in storage
    @Override
    public void addProductCount(String pname, int quantityChange) {

        if (products.containsKey(pname)) {
            if (availableProducts.containsKey(pname)) {
                int ncount = Integer.parseInt(availableProducts.get(pname).get("count"));
                ncount = ncount + quantityChange;
                availableProducts.get(pname).put("count", String.valueOf(ncount));
            } else {
                System.out.println("the requested product not in the available products." +
                        "To add first restore the product details as below");
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter price of the product:");
                double price = sc.nextDouble();
                Product p = new Product(price, quantityChange);
                availableProducts.put(pname, p.getPdetails());
            }

            textEditor.setRepositoryStrings(availableProducts);
            textEditor.writeToTextFile(repoPath + "AvailableProducts.txt");
        } else {
            System.out.println("Product does not exists. Register Product to add!");
        }

    }

    public void removeProductCount(String pname, int quantityChange) {

        if (products.containsKey(pname)) {
            if (availableProducts.containsKey(pname)) {
                int ncount = Integer.parseInt(availableProducts.get(pname).get("count"));
                ncount = ncount - quantityChange;
                if (ncount < 0) {
                    System.out.println("quantity removal exceeds the available quantity");
                } else {
                    if (ncount == 0) {
                        availableProducts.remove(pname);
                        System.out.println("removal finished and " + pname + " no longer available in the stock");
                    } else {
                        availableProducts.get(pname).put("count", String.valueOf(ncount));
                    }
                    textEditor.setRepositoryStrings(availableProducts);
                    textEditor.writeToTextFile(repoPath + "AvailableProducts.txt");
                }

            } else {
                System.out.println("requested product is not available");
            }
        } else {
            System.out.println("Product does not exists. Register Product!");
        }
    }


    @Override
    public String registerProduct(String name, String Desc) {

        ProductDescription pd = new BasicProductDescription(Desc);

        products.put(name, pd.productDetails());

        textEditor.setRepositoryStrings(products);
        textEditor.writeToTextFile(repoPath + "Products.txt");

        pd.print();
        return  name +"registered ";
    }

    @Override
    public String registerRetailer(String name, String retailerLocation) {

        Retailer rd = new BasicRetailer(name, retailerLocation);
        String size = String.valueOf(retailers.size() + 1);

        retailers.put(size, rd.getRDetails());

        textEditor.setRepositoryStrings(retailers);
        textEditor.writeToTextFile(repoPath + "Retailers.txt");
        rd.print();
        return "Retailer registered with id:" + size;
    }

    public Map<String, Map<String, String>> getAvailableProducts() {
        return availableProducts;
    }

    public void print() {

    }


    /**
     * Map<String, String> productShipped  Key: Product Name, value: Quantity
     *
     * @param productShipped
     */
    public void unLoadShipment(Map<String, Map<String,String>> productShipped) {
        String pname;
		String Desc;
        int quantity;

		Map<String,String> pdetail;

        for (Map.Entry<String, Map<String,String>> entry : productShipped.entrySet()) {

            pname = entry.getKey();
			pdetail=entry.getValue();
            quantity = Integer.parseInt(pdetail.get("quantity"));
			Desc= pdetail.get("description");

            if (!products.containsKey(pname)) {
                registerProduct(pname, Desc);
            }

            if (availableProducts.containsKey(pname)) {
                int ncount = Integer.parseInt(availableProducts.get(pname).get("count"));
                ncount = ncount + quantity;
                availableProducts.get(pname).put("count", String.valueOf(ncount));
            } else {
                //default price could be changed
                double price = 10.0;
                Product p = new Product(price, quantity);
                availableProducts.put(pname, p.getPdetails());
            }

        }

        textEditor.setRepositoryStrings(availableProducts);
        textEditor.writeToTextFile(repoPath + "AvailableProducts.txt");
    }


}
