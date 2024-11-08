package Manufacturing.src.interfaces;

public interface RawMaterialHandler {

    void getRawMaterials(String material, int quantity);
    void decrementRawMaterialCount(String material, int quantity);
}
