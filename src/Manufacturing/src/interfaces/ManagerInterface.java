package src.Manufacturing.src.interfaces;

import java.util.List;
import java.util.Map;

public interface ManagerInterface {

    void collectRawMaterials(Map<String, Integer> materials);
    void getCollectedMaterials();
    boolean createProduct();
   void deliverProduct(HeadOfManufacturingInterface headMan);

}
