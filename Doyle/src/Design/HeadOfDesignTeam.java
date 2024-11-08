package Design;

import java.util.List;

public class HeadOfDesignTeam implements FinalDesignApproval {

    private List<DesignSketch> sketches;
    private FinalDesign finalDesign;

    public HeadOfDesignTeam(List<DesignSketch> sketches, FinalDesign finalDesign) {
        this.sketches = sketches;
        this.finalDesign = finalDesign;
    }

    @Override
    public void approveFinalDesign(FinalDesign finalDesign) {

        if(finalDesign != null) {
           System.out.println(sketches);
         finalDesign.setApproved(true);
         this.finalDesign = finalDesign;
    }
        else{
            System.out.println("No final Design approved");
        }



    @Override
    public boolean isApproved(FinalDesign finalDesign) {
            if(finalDesign != null && finalDesign.setApproved(true)) {
                finalDesign.displayAllDesignSpecifications();
                return true;
            }
            else{
            System.out.println("No final Design approved");
            return false;
            }
        }

    }




    public void setDesignSpecifications(DesignSpecifications design) {

        design.setColor(design.getColors());
        design.setRawMaterials(design.getRawMaterials());
        design.setSizes(design.getSizes());
        design.setQuantities(design.getQuantities());

        System.out.println("Head of Design has set all the specifications" + design);
    }



}
