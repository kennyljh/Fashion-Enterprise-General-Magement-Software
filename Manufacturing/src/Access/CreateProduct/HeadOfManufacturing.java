/*
@author Doyle Chism
 */
public class HeadOfManufacturing {


    //variables
   private ManufacturingManager manager;
   private WarehouseStorage warehouse;
   private Machines machines;


    //constructor
    public HeadOfManufacturing(ManufacturingManager manager, WarehouseStorage warehouse) {
       this.manager = manager;
       this.warehouse = warehouse;
    }

    //Head of manufacturing tasks that are needed to complete

    //head of manufacturing receives design from the design team

    //head of manufacturing reviews design from the design team
    public void reviewDesign(){
        System.out.println("Reviewing design details");
        //selectMachines

        //selectRawMaterials
    }

    //head of manufacturing verifies raw materials from the manager
    public void reviewRawMaterials(String materials, int quantity){

//        System.out.println("Reviewing raw materials");
//        if(manager.getRawMaterials(materials, quantity)){
//            System.out.println("Manufacturing raw materials");
//        }
//        else{
//            System.out.println("Manufacturing not raw materials");
//        }

    }
    public void verifyRawMaterials(){}
    //head of manufacturing decides from the design what machines to use
    public void selectMachines(){
        System.out.println("Selecting the required machine for production");
        String[] selectedMachine = machines.selectMachine();

        for(String machine : selectedMachine){
            System.out.println("Machines for production: " + machine);
        }
    }


    /*
    THIS IS IMPLMENTED AFTER PRODUCT IS COMPLETE
     */





}
