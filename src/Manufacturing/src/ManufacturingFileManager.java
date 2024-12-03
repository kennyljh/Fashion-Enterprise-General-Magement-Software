package src.Manufacturing.src;

import src.Design.src.CustomDesign;
import src.Design.src.FinalDesign;
import src.TextEditor.PoorTextEditor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ManufacturingFileManager {

    private final PoorTextEditor editor = new PoorTextEditor();
    private final String repo = "src/Manufacturing/repository";

    public void sendDataToRepo() {
        File f = new File(repo + "final_product.txt");
        if (f.exists()) {
            editor.processTextFile(repo + "final_product.txt");
//            finalDesign = editor.getRepository();
        }
        f = new File(repo + "custom_product.txt");
        if (f.exists()) {
            editor.processTextFile(repo + "custom_product.txt");
//            customDesign = editor.getRepository();
        }
        f = new File(repo + "rawMaterials.txt");
        if (f.exists()) {
            editor.processTextFile(repo + "rawMaterials.txt");
//            rawMaterials = editor.getRepository();
        }

    }

//    public void addFinalDesign(FinalDesign design) {
//        finalDesign.put("Final Design " + design.getDesignName(), design.mapObjects());
//
//        editor.setRepository(finalDesign);
//        editor.writeToTextFile(repo + "final_product.txt");
//    }
//
//    public void addCustomDesign(CustomDesign customDesign) {
//        customDesign.put("Custom Design " + customDesign.getDesignName(), customDesign.mapObjects());
//
//        editor.setRepository(customDesign);
//        editor.writeToTextFile(repo + "custom_product.txt");
//    }
//
//    public void addRawMaterials(RawMaterials materials) {
//        rawMaterials.put("Raw Materials " + materials.getRawMaterials(), materials.mapObjects());
//
//        editor.setRepository(materials);
//        editor.writeToTextFile(repo + "rawMaterials.txt");
//    }
//
//    public Map<String, Object> getRawMaterials() {
//        Map<String, Object> rawMaterials = new HashMap<>();
//        for (Map.Entry<String, Object> entry : rawMaterials.entrySet()) {
//            rawMaterials.put(entry.getKey(), entry.getValue());
//        }
//        return rawMaterials;
//    }
//
//    public Map<String, Object> getFinalDesign() {
//        Map<String, Object> finalDesign = new HashMap<>();
//        for (Map.Entry<String, Object> entry : finalDesign.entrySet()) {
//            finalDesign.put(entry.getKey(), entry.getValue());
//        }
//        return finalDesign;
//    }
//
//    public Map<String, Object> getCustomDesign() {
//        Map<String, Object> customDesign = new HashMap<>();
//        for (Map.Entry<String, Object> entry : customDesign.entrySet()) {
//            customDesign.put(entry.getKey(), entry.getValue());
//        }
//        return customDesign;
//    }

}
