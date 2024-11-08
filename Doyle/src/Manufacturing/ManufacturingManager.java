package Manufacturing;

import java.util.List;

/*
@author
 */
public class ManufacturingManager implements MachineOperations, RawMaterialHandler{

    private WarehouseStorage warehouse;
    private HeadOfManufacturing headOfManufacturing;
    private List<ManufacturingWorkers> workers;
    private MachineOperations machines;

    public ManufacturingManager(WarehouseStorage warehouse, HeadOfManufacturing headOfManufacturing,
                                List<ManufacturingWorkers> workers, MachineOperations machines) {
        this.warehouse = warehouse;
        this.headOfManufacturing = headOfManufacturing;
        this.workers = workers;
    }
    //collect the raw materials for the Head of Manufacturing
    @Override
    public void getRawMaterials(String material, int quantity) {
        System.out.println("material: " + material + "quantity: " + quantity);
        warehouse.retrieveRawMaterials(material, quantity);
    }

    //decrement rawMaterialCount from the warehouseStorage
    @Override
    public void decrementRawMaterialCount(String material, int quantity) {
        System.out.println("material: " + material + "new quantity: " + quantity);
        warehouse.decrementMaterialCount(material, quantity);
    }
    //verify machines to use from the head of manufacturing

    @Override
    public boolean isRunning() {
        return machines.isRunning();
    }

    //start the machines that the head of manufacturing told to start
    @Override
    public void startMachine() {

        if(machines.isRunning()){
            System.out.println("Machine is already running");
        }
        else{
            machines.startMachine();
        }
    }

    @Override
    public void stopMachine() {

        if(machines.isRunning()){
           System.out.println("Machine is already running");
           machines.stopMachine();
        }
        else{
          System.out.println("Machine is already stopped");
        }
    }
    @Override
    public void startProduction(){

        if(machines.isRunning()){
            System.out.println("machine is already running");
        }else{
            machines.startProduction();
        }
    }

    @Override
    public void stopProduction() {

        if(machines.isRunning()){
            System.out.println("machine is already running");
            machines.stopMachine();
        }
        else{
            System.out.println("machine is already stopped");
        }
    }


    //supervise the workers to make sure they are working
    /*
    After the product is completed
     */
    public void beginProduction(int quantity) {
        System.out.println("begin mass production of "+ quantity + "units");

        if(!machines.isRunning()){
            startMachine();
        }
        for(int i=0; i < quantity; i++){
            System.out.println("production of " + i + "units");
            machines.startProduction();
        }
        machines.stopProduction();
        machines.stopMachine();
    }


}
