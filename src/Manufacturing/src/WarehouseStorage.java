package src.Manufacturing.src;

import java.util.HashMap;
import java.util.Map;

public class WarehouseStorage {


    //hold the materials for production
    private Map<String, Integer> materialStock;

    public WarehouseStorage() {
        materialStock = new HashMap<>();
    }
    //manager will retrieve(materials) from the warehouse
    public void retrieveRawMaterials(String material, int quantity){
        if(materialStock.containsKey(material)){
            materialStock.put(material, quantity);
            decrementMaterialCount(material, quantity);
        }
        else{
            System.out.println("Material " + material + " does not exist");
        }
    }

    public void decrementMaterialCount(String material, int quantity){
        if(materialStock.containsKey(material)){
            int currentQuantity = materialStock.get(material);
            int newQuantity = currentQuantity - quantity;
            materialStock.put(material, newQuantity);
            System.out.println("Material " + material + " has been decreased to " + newQuantity);
        }
        else{
            System.out.println("Material " + material + " does not exist");
        }
    }

    //update stock amount



}
