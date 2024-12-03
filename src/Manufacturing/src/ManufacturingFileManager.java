package src.Manufacturing.src;

import src.Design.src.CustomDesign;
import src.Design.src.DesignSketch;
import src.Design.src.FinalDesign;
import src.Design.src.MarketingDesign;
import src.TextEditor.PoorTextEditor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ManufacturingFileManager {

    private final PoorTextEditor editor = new PoorTextEditor();
    private final String manRepo = "Manufacturing/repository/";
    private final String designRepo = "Design/repository/";
    private final String inventoryRepo = "Inventory/repository/";

    private Map<String, Object> sketches = new HashMap<>();
    private Map<String, Object> finalDesign = new HashMap<>();
    private Map<String, Object> customDesign = new HashMap<>();
    private Map<String, Object> marketingDesign = new HashMap<>();


    /*
    Initialize all files based on design type
     */
    public void initialize() {
        loadFromFile("sketches.txt", sketches);
        loadFromFile("finalDesign.txt", finalDesign);
        loadFromFile("customDesign.txt", customDesign);
        loadFromFile("marketingDesign.txt", marketingDesign);
    }

    /*
    load From files and map to the editor
     */
    private void loadFromFile(String fileName, Map<String, Object> maps) {
        File file = new File(designRepo + fileName);
        if (file.exists()) {
            editor.processTextFile(file.getAbsolutePath()); //check path type
            maps.putAll(editor.getRepository());
        } else {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }

    private void loadFromManufacturingFile(String fileName, Map<String, Object> maps) {
        File file = new File(manRepo + fileName);
        if (file.exists()) {
            editor.processTextFile(file.getAbsolutePath());
            maps.putAll(editor.getRepository());
        } else {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }

    /*
    save text type to file
     */
    public void saveToFile(String fileName, Map<String, Object> maps) {

        String newFilePath;
        if (fileName.equals("Products.txt") || fileName.equals("CustomProducts.txt")) {
            newFilePath = inventoryRepo + fileName;
        } else {
            newFilePath = manRepo + fileName;
        }
        File file = new File(newFilePath);
        File parent = file.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            System.err.println("Couldn't create directory: " + parent.getAbsolutePath());
            return;
        }
        Map<String, Object> existingData = new HashMap<>();
        if (file.exists()) {
            editor.processTextFile(file.getAbsolutePath());
            existingData.putAll(editor.getRepository());
        }
        if (fileName.equals("RawMaterials.txt")) {
//            Map<String, Object> rawMaterialsOnly = new HashMap<>();
            maps.forEach((key, value) -> {
                if (value instanceof Map && ((Map<?, ?>) value).containsKey("Quantity")) {
                    existingData.put(key, value);
                }
            });
//            editor.setRepository(rawMaterialsOnly);
        } else if (fileName.equals("Products.txt") || fileName.equals("CustomProducts.txt")) {
            maps.forEach((key, value) -> {
                if (value instanceof Map) {
                    existingData.put(key, value);
                } else {
                    Map<String, String> productData = new HashMap<>();
                    productData.put("Details", value.toString());
                    existingData.put(key, productData);
                }
            });
        } else {
            existingData.putAll(maps);
        }
        editor.setRepository(existingData);
        editor.writeToTextFile(file.getAbsolutePath());
    }

    /*
    save a Design Sketch
     */
    public void saveSketch(DesignSketch sketch) {
        Map<String, String> mappedSketch = sketch.mapObjects();
        System.out.println("Mapped Data: " + mappedSketch);
        sketches.put("Sketch " + sketch.getDesignName(), mappedSketch);
        saveToFile("sketches.txt", sketches);
    }

    /*
    Return the sketch
     */
    public Map<String, Object> getSketch() {
        return new HashMap<>(sketches);
    }

    public void saveFinalDesign(FinalDesign design) {
        finalDesign.put("Final Design " + design.getDesignName(), design.mapObjects());
        saveToFile("finalDesign.txt", finalDesign);
    }

    public Map<String, Object> getRawMaterials() {
        Map<String, Object> rawMaterials = new HashMap<>();
        loadFromManufacturingFile("rawMaterials.txt", rawMaterials);
        return rawMaterials;
    }

    public Map<String, Object> getFinalDesign() {
        Map<String, Object> finalDesigns = new HashMap<>();
        loadFromFile("finalDesign.txt", finalDesigns);
        return finalDesigns;
    }

    public void saveCustomDesign(CustomDesign design) {
        customDesign.put("Custom Design " + design.getDesignName(), design.mapObjects());
        saveToFile("customDesign.txt", customDesign);
    }

    public Map<String, Object> getCustomDesign() {
        Map<String, Object> customDesigns = new HashMap<>();
        loadFromFile("customDesign.txt", customDesigns);
        return customDesigns;
    }

    public void saveMarketingDesign(MarketingDesign design) {
        marketingDesign.put("Marketing Design " + design.getDesignSketchName(), design.mapObjects());
        saveToFile("marketingDesign.txt", marketingDesign);
    }

    public Map<String, Object> getMarketingDesign() {
        return new HashMap<>(marketingDesign);
    }

    public void sendDataToRepo() {
        File f = new File(designRepo + "sketches.txt");
        if (f.exists()) {
            editor.processTextFile(designRepo + "sketches.txt");
            sketches = editor.getRepository();
        }
        f = new File(designRepo + "finalDesign.txt");
        if (f.exists()) {
            editor.processTextFile(designRepo + "finalDesign.txt");
            finalDesign = editor.getRepository();
        }
        f = new File(designRepo + "customDesign.txt");
        if (f.exists()) {
            editor.processTextFile(designRepo + "customDesign.txt");
            customDesign = editor.getRepository();
        }
        f = new File(designRepo + "marketingDesign.txt");
        if (f.exists()) {
            editor.processTextFile(designRepo + "marketingDesign.txt");
            marketingDesign = editor.getRepository();

        }
    }


}
