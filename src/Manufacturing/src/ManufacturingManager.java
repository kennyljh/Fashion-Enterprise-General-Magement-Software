package src.Manufacturing.src;

import src.Manufacturing.src.interfaces.HeadOfManufacturingInterface;
import src.Manufacturing.src.interfaces.ManagerInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@author
 */
public class ManufacturingManager implements ManagerInterface {

    private Map<String, Integer> collectedMaterials = new HashMap<>();
    private SimpleProduct product;


    @Override
    public boolean createProduct(Map<String, Integer> verifiedMaterials) {

        System.out.println("Creating product with the following materials: ");
        verifiedMaterials.forEach((material, value) -> System.out.println(value + "items of " + material));
        this.product = new SimpleProduct(verifiedMaterials.toString());
        return true;
    }

    @Override
    public void deliverProduct(SimpleProduct product) {

        if (product == null) {
            System.out.println("Product has not been created");
            return;
        }
        System.out.println("Delivered to the head of manufacturing complete!");


    }

    @Override
    public void collectRawMaterials(Map<String, Integer> materials) {
        collectedMaterials.putAll(materials);
        System.out.println("Collected Raw Materials: " + collectedMaterials);
    }

    public Map<String, Integer> getCollectedMaterials() {
        return collectedMaterials;
    }

    public String getProductName() {
        return product.getName();
    }

}
