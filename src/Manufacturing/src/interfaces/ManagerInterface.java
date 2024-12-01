package src.Manufacturing.src.interfaces;

import src.Manufacturing.src.Product;

import java.util.Map;

public interface ManagerInterface {

    void collectRawMaterials(Map<String, Integer> materials);
    Map<String, Integer> getCollectedMaterials();
    boolean createProduct(Map<String, Integer> materials);
   void deliverProduct(Product product);

}
