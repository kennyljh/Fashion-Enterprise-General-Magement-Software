package src.Design.src;

import src.Design.src.interfaces.*;

import java.util.List;

public class HeadOfDesignTeam implements HeadOfDesignInterface {


    private List<DesignSketch> allSketches;
    private DesignSketch selectedSketch;


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
            System.out.println((i) + ". " + sketches.get(i).toString());
        }
    }

    @Override
    public void selectSketch(int sketchIndex, List<DesignSketch> sketches) {

        if (sketchIndex > 0 && sketchIndex < sketches.size()) {
            selectedSketch = sketches.get(sketchIndex);
            System.out.println("Selcted sketch was: " + selectedSketch.toString());
        } else {
            System.out.println("Incorrect Sketch selected, Try Again");
        }
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
        finalDesign.displayAllFinalSpecifications();

        return finalDesign;
    }

}
