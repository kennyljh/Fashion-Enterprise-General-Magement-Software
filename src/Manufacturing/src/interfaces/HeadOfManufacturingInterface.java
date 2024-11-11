package src.Manufacturing.src.interfaces;

import src.Manufacturing.src.SimpleProduct;

import java.util.List;
import java.util.Map;

public interface HeadOfManufacturingInterface {

    void viewRawMaterials(List<String> rawMaterials);
    void selectRawMaterial(int index, Map<String, Integer> rawMaterials);

    void selectMachine(MachineOperations machine);
    boolean verifyProudct(SimpleProduct product);
}
