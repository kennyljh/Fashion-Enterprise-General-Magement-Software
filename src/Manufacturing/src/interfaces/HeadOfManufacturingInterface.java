package src.Manufacturing.src.interfaces;

import src.Inventory.src.Product;
import src.Manufacturing.src.CustomProduct;

import java.util.Map;

public interface HeadOfManufacturingInterface {

    void viewRawMaterials(Map<String, String> rawMaterials);
    void selectRawMaterial(String index, String rawMaterials);
    void receiveFinalDesign(Map<String, String> finalDesign);
    void receiveCustomDesign(Map<String, String> customDesign);
    void verifyProduct(Product product);
    void verifyCustomProduct(CustomProduct customProduct);





}
