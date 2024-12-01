package src.Manufacturing.src;

import src.Design.src.CustomDesign;
import src.Manufacturing.src.interfaces.ProductInterface;

public class CustomProduct implements ProductInterface {

    CustomDesign design;
    String description;
    String quantity;
    String category;

    public CustomProduct(CustomDesign design) {
        this.design = design;
    }

    @Override
    public void setName(String name) {
        this.design = new CustomDesign(design.getDesignName());
    }

    @Override
    public String getName() {
        return this.design.getDesignName();
    }


    @Override
    public void setDescription(String description) {
        this.description = description;

    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setQuantity(String quantity) {
        this.quantity = quantity;

    }

    @Override
    public String getQuantity() {
        return quantity;
    }

    @Override
    public void setCategory(String category) {

        this.category = category;
    }

    @Override
    public String getCategory() {
        return category;
    }
}
