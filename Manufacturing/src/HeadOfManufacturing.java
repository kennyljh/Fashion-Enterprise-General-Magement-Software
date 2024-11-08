package Manufacturing.src;

import Design.src.interfaces.FinalDesignApproval;
import Design.src.interfaces.DesignSpecifications;
import Design.src.FinalDesign;

import java.util.List;

/*
@author Doyle Chism
 */

//TODO: Dont use the design interface for manufacturing!!
public class HeadOfManufacturing implements FinalDesignApproval {


    //variables
    private ManufacturingManager manager;
    private Machines machines;
    private WarehouseStorage warehouse;
    private DesignSpecifications design;




    //constructor
    public HeadOfManufacturing(ManufacturingManager manager, WarehouseStorage warehouse, Machines machines) {
        this.manager = manager;
        this.warehouse = warehouse;
        this.machines = machines;

    }

    //Head of manufacturing tasks that are needed to complete

    //head of manufacturing receives design from the design team
    private void receiveDesign(FinalDesign design) {
        //assuming there is a design team we are receiving the correct information
       if(isApprovedDesign(true)){
           this.design = design;
           System.out.println("Design Approved");
           reviewDesign();
       }
       else{
           System.out.println("Design Not Approved");
       }
    }


    @Override
    public void approveFinalDesign(FinalDesign finalDesign) {

        if(finalDesign != null){
            finalDesign.isApproved();
            System.out.println("Final Design Approved");
        }
    }

    @Override
    public void isApprovedDesign(FinalDesign finalDesign) {

        return finalDesing.isApproved();
    }

    @Override
    public void setApprovedDesign(FinalDesign finalDesign) {

        if(finalDesign != null && finalDesign.isApproved){
            this.design = finalDesign;
        }
    }

    //head of manufacturing reviews design from the design team
    public void reviewDesign() {
        System.out.println("Reviewing design details for: " + design.getDesignName());

        List<String> requiredMaterials = design.getRawMaterials();
        List<Integer> materialCount = design.getRawMaterialCount();

        System.out.println("Below is a list of the required materials: ");
        for (int i = 0; i < requiredMaterials.size(); i++) {
            System.out.println("- " + requiredMaterials.get(i) + ":" + materialCount.get(i));
        }
        manager.collectRawMaterials(requiredMaterials, materialCount);
        selectMachines();
        ;
    }

    //head of manufacturing verifies raw materials from the manager
    public void reviewRawMaterials(List<String> materials, List<Integer> quantity) {

        System.out.println("Verifying the raw materials collected by the Manager");
        boolean materialsMatchDesign = true;

        for (int i = 0; i < materials.size(); i++) {
            if (!materials.get(i).equals(design.getRawMaterials().get(i))) {
                System.out.println("Incorrect quantity of raw materials: " + materials.get(i));
                materialsMatchDesign = false;
            } else {
                System.out.println("Correct quantity of raw materials: " + materials.get(i));
            }
        }

        if (materialsMatchDesign) {
            System.out.println("All materials are approved by the head of manufacturer");
            manager.startManufacturing();
        } else {
            System.out.println("Some materials are not approved by the head of manufacturer");
        }

    }

    public void verifyRawMaterials() {
        System.out.println("Verifying the raw materials collected by the Manager");
        manager.startManufacturing();
    }

    //head of manufacturing decides from the design what machines to use
    public void selectMachines() {
        System.out.println("Selecting the required machine for production");
        List<String> selectedMachines = machines.selectMachine(design.getRequiredMachineType());

        System.out.println("Machine selected for production:");
        for (String machine : selectedMachines) {
            System.out.println("- " + machine);
        }
        machines.startProduction();

    }




}
