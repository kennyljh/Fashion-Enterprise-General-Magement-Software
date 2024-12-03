package src.Manufacturing.src;

import src.Inventory.src.Product;
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
    public void viewRawMaterials(Map<String, String> rawMaterials) {
        if (rawMaterials == null || rawMaterials.isEmpty()) {
            System.out.println("No raw materials collected, collect raw materials first");
            return;
        }
        int index = 1;
        System.out.println("Select the materials needed");
        for (Map.Entry<String, String> entry : rawMaterials.entrySet()) {
            System.out.println(index++ + ". " + entry.getKey() + " collected with Quantity: " + entry.getValue());
        }
    }

    @Override
    public void selectRawMaterial(String materialNumber, String rawMaterials) {

//        if (materialNumber >= 0 && materialNumber < rawMaterials.length()) {
//            System.out.println("Selected raw material: " + rawMaterials);
//        }

    }

    @Override
    public void receiveFinalDesign(Map<String, String> finalDesign) {


        System.out.println("Receiving Final Design Specifications:");
        for (Map.Entry<String, String> entry : finalDesign.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Final Design has been received successfully.");


    }

    @Override
    public void receiveCustomDesign(Map<String, String> customDesign) {

        System.out.println("Receiving Custom Design Specifications:");
        for (Map.Entry<String, String> entry : customDesign.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Custom Design has been received successfully.");


    }

    @Override
    public void verifyProduct(Product product) {


        System.out.println("Verifying Product: " + product.getId());
//        if (product.isQualityApproved()) {
//            System.out.println("Product " + product.getId() + " passed quality verification.");
//        } else {
//            System.out.println("Product " + product.getId() + " failed quality verification. It needs to be remade.");
//        }


    }

    @Override
    public void verifyCustomProduct(CustomProduct customProduct) {


        System.out.println("Verifying Custom Product: " + customProduct.getName());
//        if (customProduct.isQualityApproved() && customProduct.getCategory()) {
//            System.out.println("Custom Product " + customProduct.getName() + " passed verification.");
//        } else {
//            System.out.println("Custom Product " + customProduct.getName() + " failed verification. Please review specifications.");
//        }


    }

//    @Override
//    public void sendToModelling(CustomProduct product) {
//
//        if (!product.isQualityApproved()) {
//            System.out.println("Cannot send " + product.getName() + " to Modelling. Quality verification required.");
//            return;
//        }
//        System.out.println("Sending " + product.getName() + " to the Modelling Department.");
//    }
//
//    @Override
//    public void sendToInventory(Product product, String quantity) {
//
//
//        if (!product.isQualityApproved()) {
//            System.out.println("Cannot store " + product.getId() + " in Inventory. Quality verification required.");
//            return;
//        }
//        System.out.println("Storing " + product.getId() + " in Inventory with quantity: " + quantity);
//
//
//    }
}