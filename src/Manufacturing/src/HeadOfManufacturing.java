package src.Manufacturing.src;

import src.Manufacturing.src.interfaces.HeadOfManufacturingInterface;
import src.Manufacturing.src.interfaces.MachineOperations;

import java.util.List;
import java.util.Map;

/*
@author Doyle Chism
 */


public class HeadOfManufacturing implements HeadOfManufacturingInterface {


    private HeadOfManufacturing rawMaterials;

    @Override
    public void viewRawMaterials(Map<String, Integer> rawMaterials) {
        if (rawMaterials == null || rawMaterials.isEmpty()) {
            System.out.println("No raw materials collected, collect raw materials first");
            return;
        }
        int index = 1;
        System.out.println("Select the materials needed");
        for (Map.Entry<String, Integer> entry : rawMaterials.entrySet()) {
            System.out.println(index++ + ". " + entry.getKey() + " collected with Quantity: " + entry.getValue());
        }
    }

    @Override
    public void selectRawMaterial(int materialNumber, String rawMaterials) {

        if (materialNumber >= 0 && materialNumber < rawMaterials.length()) {
            System.out.println("Selected raw material: " + rawMaterials);
        }

    }



}
