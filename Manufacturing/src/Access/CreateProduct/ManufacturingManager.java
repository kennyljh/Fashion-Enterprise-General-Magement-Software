import java.util.List;

/*
@author
 */
public class ManufacturingManager {

    private WarehouseStorage warehouse;
    private HeadOfManufacturing headOfManufacturing;
    private List<ManufacturingWorkers> workers;
    private Machines machines;

    public ManufacturingManager(WarehouseStorage warehouse, HeadOfManufacturing headOfManufacturing, List<ManufacturingWorkers> workers) {
        this.warehouse = warehouse;
        this.headOfManufacturing = headOfManufacturing;
        this.workers = workers;
    }
    //collect the raw materials for the Head of Manufacturing
    public void getRawMaterials(String material, int quantity) {
        warehouse.retrieveRawMaterials(material, quantity);
    }
    //decrement rawMaterialCount from the warehouseStorage
    public void decrementRawMaterials(String material, int quantity) {
        warehouse.decrementMaterialCount(material, -quantity);
    }
    //verify machines to use from the head of manufacturing

    //start the machines that the head of manufacturing told to start
    public void startManufacturing() {
        machines.startMachine();
    }
    //supervise the workers to make sure they are working



    /*
    After the product is completed
     */

    //give completed product to the head of manufacturing
}
