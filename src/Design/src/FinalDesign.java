package src.Design.src;

import src.Design.src.interfaces.DesignSpecifications;

import java.util.List;

public class FinalDesign implements DesignSpecifications {

    private final String designName;
    private List<String> colors;
    private List<String> rawMaterials;
    private List<String> sizes;
    private int quantity;
    private boolean approved;


    public FinalDesign(String designName) {
        this.designName = designName;
        this.approved = false;
    }


    @Override
    public void setColor(List<String> colors) {
        this.colors = colors;
        System.out.println("Color for the design is: " + colors);
    }

    @Override
    public void setRawMaterials(List<String> rawMaterials) {
        this.rawMaterials = rawMaterials;
        System.out.println("Raw materials for the design is: " + rawMaterials);
    }

    @Override
    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
        System.out.println("Sizes for the design is: " + sizes);
    }

    @Override
    public void setQuantities(int quantities) {
        this.quantity = quantities;
        System.out.println("Quantities for the design is: " + quantities);
    }

    @Override
    public List<String> getColors() {
        return colors;
    }

    @Override
    public List<String> getRawMaterials() {
        return rawMaterials;
    }

    @Override
    public List<String> getSizes() {
        return sizes;
    }

    @Override
    public int getQuantities() {
        return quantity;
    }

    public void displayAllDesignSpecifications() {
        System.out.println("All Final Design Specifications:");
        System.out.println("Design Name: " + designName);
        System.out.println("Colors: " + colors);
        System.out.println("Raw Materials: " + rawMaterials);
        System.out.println("Sizes: " + sizes);
        System.out.println("Quantities: " + quantity);
    }
}
