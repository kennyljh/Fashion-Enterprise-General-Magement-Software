package src.Manufacturing.src;

import src.Design.src.CustomDesign;
import src.Design.src.FinalDesign;
import src.Manufacturing.src.interfaces.ProductInterface;

public class Product implements ProductInterface {

    FinalDesign design;
    String description;
    String quantity;
    String category;

    public Product(FinalDesign design) {
        this.design = design;
    }

    @Override
    public void setName(String name) {

        this.design = new FinalDesign(design.getDesignName());
    }

    @Override
    public String getName() {
        return design.getDesignName();
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
