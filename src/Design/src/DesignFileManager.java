package src.Design.src;

import src.TextEditor.PoorTextEditor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignFileManager {

    private Map<String, Object> sketches = new HashMap<>();
    private Map<String, Object> finalDesign = new HashMap<>();
    private Map<String, Object> customDesign = new HashMap<>();
    private Map<String, Object> marketingDesign = new HashMap<>();


    private final PoorTextEditor editor = new PoorTextEditor();
    private final String repo = "Design/repository/";

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
        File file = new File(repo + fileName);
        if (file.exists()) {
            editor.processTextFile(file.getAbsolutePath()); //check path type
            maps.putAll(editor.getRepository());
        }
    }

    /*
    save text type to file
     */
    public void saveToFile(String fileName, Map<String, Object> maps) {

        String newFilePath = repo + fileName;
        File file = new File(newFilePath);

        File parent = file.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            System.err.println("Couldn't create directory: " + parent.getAbsolutePath());
            return;
        }
        editor.setRepository(maps);
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

//    public void deleteSketchFromList(String sketchName) {
//
//        String sketchToRemove = null;
//
//
//        for (Map.Entry<String, Object> entry : sketches.entrySet()) {
//            if (entry.getKey().equals(sketchName) && entry.getValue() instanceof DesignSketch) {
//                sketchToRemove = entry.getKey();
//                break;
//            }
//        }
//
//        // If sketch is found, remove it
//        if (sketchToRemove != null) {
//            sketches.remove(sketchToRemove); // Remove from the list in memory
//            System.out.println("Sketch '" + sketchName + "' has been removed from memory.");
//
//            // Update the file using DesignFileManager
//            deleteSketch(sketchToRemove);
//        } else {
//            System.out.println("Sketch '" + sketchName + "' was not found in memory.");
//        }
//    }

    public void deleteSketch(DesignSketch sketch) {


        if (sketch == null) {
            System.out.println("sketch is null");
            return;
        }
        String designName = sketch.getDesignName();
        String key = "Sketch " + designName;
        File file = new File(repo + key);
        if (!file.exists()) {
            System.out.println("The file does not exist.");
            return;
        }
        List<String> allLines = new ArrayList<>();
        try {
            allLines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        if (sketches.containsKey(key)) {
            sketches.remove(key);
            editor.removeArrayItem(designName);
            saveToFile("sketches.txt", sketches);
            System.out.println("Sketch: " + designName + " has been deleted.");
        } else {
            System.out.println("Sketch: " + designName + " does not exist.");
        }

    }


    public void deleteFinalDesign(FinalDesign finalDesign) {

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

    public Map<String, Object> getFinalDesign() {
        return new HashMap<>(finalDesign);
    }

    public void saveCustomDesign(CustomDesign design) {
        customDesign.put("Custom Design " + design.getDesignName(), design.mapObjects());
        saveToFile("customDesign.txt", customDesign);
    }

    public Map<String, Object> getCustomDesign() {
        return new HashMap<>(customDesign);
    }

    public void saveMarketingDesign(MarketingDesign design) {
        marketingDesign.put("Marketing Design " + design.getDesignSketchName(), design.mapObjects());
        saveToFile("marketingDesign.txt", marketingDesign);
    }

    public Map<String, Object> getMarketingDesign() {
        return new HashMap<>(marketingDesign);
    }

    public void sendDataToRepo() {
        File f = new File(repo + "sketches.txt");
        if (f.exists()) {
            editor.processTextFile(repo + "sketches.txt");
            sketches = editor.getRepository();
        }
        f = new File(repo + "finalDesign.txt");
        if (f.exists()) {
            editor.processTextFile(repo + "finalDesign.txt");
            finalDesign = editor.getRepository();
        }
        f = new File(repo + "customDesign.txt");
        if (f.exists()) {
            editor.processTextFile(repo + "customDesign.txt");
            customDesign = editor.getRepository();
        }
        f = new File(repo + "marketingDesign.txt");
        if (f.exists()) {
            editor.processTextFile(repo + "marketingDesign.txt");
            marketingDesign = editor.getRepository();

        }
    }


}
