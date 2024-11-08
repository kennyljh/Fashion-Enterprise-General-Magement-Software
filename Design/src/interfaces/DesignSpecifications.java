package Design.src.interfaces;

import java.util.List;

public interface DesignSpecifications {

    void setColor(List<String> colors);
    void setRawMaterials(List<String> rawMaterials);
    void setSizes(List<String> sizes);
    void setQuantities(int quantities);
    List<String> getColors();
    List<String> getRawMaterials();
    List<String> getSizes();
    int getQuantities();


}
