package src.Manufacturing.src;

import src.Manufacturing.src.interfaces.HeadOfManufacturingInterface;
import src.Manufacturing.src.interfaces.MachineOperations;
import src.Manufacturing.src.interfaces.ManagerInterface;

import java.util.List;

/*
@author
 */
public class ManufacturingManager implements ManagerInterface {

    private List<String> collectedMaterials;
    private SimpleProduct product;

    @Override
    public void collectRawMaterials(int quantity, String material) {
        for(int i = 0; i < quantity; i++){
            collectedMaterials.add(material);
        }
    }

    @Override
    public boolean createProduct() {

        if(!collectedMaterials.isEmpty()){
            this.product = new SimpleProduct(getProduct().getName());
            return true;
        }
        else{
            System.out.println("Incorrect Materials for the Product");
            return false;
        }
    }

    @Override
    public void deliverProduct(HeadOfManufacturingInterface headMan) {

        System.out.println("Delivering to the head of manufacturing");
        headMan.verifyProudct(this.product);
    }

    public List<String> getCollectedMaterials() {
        return collectedMaterials;
    }
    public SimpleProduct getProduct(){
        return product;
    }
}
