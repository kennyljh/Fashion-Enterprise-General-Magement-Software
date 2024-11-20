package src.Design.src;

import src.Design.src.interfaces.*;

import java.util.List;

public class HeadOfDesignTeam implements HeadOfDesignInterface {


    private List<DesignSketch> allSketches;
    private DesignSketch selectedSketch;
    private CustomDesign customDesign;


    public HeadOfDesignTeam(List<DesignSketch> sketches) {
        this.allSketches = sketches;
    }


    @Override
    public void viewSketches(List<DesignSketch> sketches) {

        if (sketches.isEmpty()) {
            System.out.println("No sketch selected");
            return;
        }
        System.out.println("Select a sketch");
        for (int i = 0; i < sketches.size(); i++) {
            System.out.println((i + 1) + ". " + sketches.get(i).getDesignName());
        }
    }

    @Override
    public void selectSketch(int sketchIndex, List<DesignSketch> sketches) {

        if (sketchIndex >= 0 && sketchIndex < sketches.size()) {
            selectedSketch = sketches.get(sketchIndex);
            System.out.println("Selcted sketch was: " + selectedSketch.getDesignName());
        } else {
            System.out.println("Incorrect Sketch selected, Try Again");
        }
    }

    @Override
    public void receiveCustomDesign(CustomDesign customDesign) {

        if (customDesign == null) {
            System.out.println("Custom design was not received");
            return;
        }
        this.customDesign = customDesign;
        System.out.println("Custom design received: " + customDesign.getDesignName());
        customDesign.displayAllSpecifications();

    }


    @Override
    public FinalDesign confirmFinalDesign() {

        if (selectedSketch == null) {
            System.out.println("No sketch selected");
            return null;
        }
        FinalDesign finalDesign = new FinalDesign(selectedSketch.getDesignName());

        finalDesign.setDesignName(selectedSketch.getDesignName());
        finalDesign.setColor(selectedSketch.getColors());
        finalDesign.setDesignImage(selectedSketch.getDesignImage());
        finalDesign.setSizes(selectedSketch.getSizes());
        finalDesign.setQuantities(selectedSketch.getQuantities());
        finalDesign.setRawMaterials(selectedSketch.getRawMaterials());
        finalDesign.displayAllSpecifications();

        return finalDesign;
    }

    @Override
    public CustomDesign confirmCustomDesign() {

        if (customDesign == null) {
            System.out.println("No custom design selected");
            return null;
        }
        CustomDesign custom = new CustomDesign(customDesign.getDesignName());

        custom.setDesignName(customDesign.getDesignName());
        custom.setColor(customDesign.getColors());
        custom.setDesignImage(customDesign.getDesignImage());
        custom.setSizes(customDesign.getSizes());
        custom.setQuantities(customDesign.getQuantities());
        custom.setRawMaterials(customDesign.getRawMaterials());
        custom.displayAllSpecifications();

        return custom;
    }

}
