package src.Manufacturing.src;

import src.Design.src.interfaces.FinalDesignApproval;
import src.Design.src.interfaces.DesignSpecifications;
import src.Design.src.FinalDesign;
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
    public void viewRawMaterials(List<String> rawMaterials) {
        if (rawMaterials == null || rawMaterials.isEmpty()) {
            System.out.println("No raw materials found");
            return;
        }
        System.out.println("Select the materials needed");
        for (int i = 0; i < rawMaterials.size(); i++) {
            System.out.println((i + 1) + ". " + rawMaterials.get(i));
        }
    }

    @Override
    public void selectRawMaterial(int index, Map<String, Integer> rawMaterials) {

        if(index >= 0 && index < rawMaterials.size()) {
            rawMaterials.set(index, rawMaterials.get(index));
            System.out.println("Selected raw material: " + rawMaterials.get(index));
        }
    }

    @Override
    public void selectMachine(MachineOperations machine) {

    }

    @Override
    public boolean verifyProudct(SimpleProduct product) {
        return false;
    }



}
