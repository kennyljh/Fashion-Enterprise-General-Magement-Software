package src.Design.src;

import src.Design.src.interfaces.DesignSpecifications;

import java.util.List;

public class FinalDesign implements DesignSpecifications {

    private String designName;
    private String designImage;
    private List<String> colors;
    private List<String> rawMaterials;
    private List<String> sizes;
    private int quantity;


    public FinalDesign(String designName) {
        this.designName = designName;
    }


    @Override
    public void setColor(List<String> colors) {
    this.colors = colors;
    }

    @Override
    public void setRawMaterials(List<String> rawMaterials) {
this.rawMaterials = rawMaterials;
    }

    @Override
    public void setSizes(List<String> sizes) {
this.sizes = sizes;
    }

    @Override
    public void setQuantities(int quantities) {
this.quantity = quantities;
    }

    @Override
    public void setDesignName(String designName) {
this.designName = designName;
    }

    @Override
    public void setDesignImage(String image) {
this.designImage = image;
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

    @Override
    public String getDesignName() {
        return designName;
    }

    @Override
    public String getDesignImage() {
        return designImage;
    }

    public void displayAllFinalSpecifications() {
        System.out.println("All Final Design Specifications:");
        System.out.println("Design Name: " + designName);
        System.out.println("Design Image: " + designImage);
        System.out.println("Design Colors: " + colors);
        System.out.println("Design Raw Materials: " + rawMaterials);
        System.out.println("Design Sizes: " + sizes);
        System.out.println("Design Quantities: " + quantity);
    }
}
