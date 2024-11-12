package src.Manufacturing.src.interfaces;

import src.Manufacturing.src.SimpleProduct;

import java.util.List;
import java.util.Map;

public interface HeadOfManufacturingInterface {

    void viewRawMaterials(Map<String, Integer> rawMaterials);
    void selectRawMaterial(int index, String rawMaterials);

}
