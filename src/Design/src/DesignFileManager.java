package src.Design.src;

import src.TextEditor.PoorTextEditor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DesignFileManager {

    private Map<String, Object> sketches = new HashMap<>();
    private Map<String, Object> finalDesign = new HashMap<>();
    private Map<String, Object> customDesign = new HashMap<>();
    private Map<String, Object> marketingDesign = new HashMap<>();


    private final PoorTextEditor editor = new PoorTextEditor();

    private final String repo = "src/Design/repository/";

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

    public void addSketch(DesignSketch sketch) {
        sketches.put("Sketches " + sketch.getDesignName(), sketch.mapObjects());

        editor.setRepository(sketches);
        editor.writeToTextFile(repo + "sketches.txt");
    }

    /*
    public Map<String, Map<String, String>> getSketches() {
        // Return the sketches map directly
        return new HashMap<>(sketches);
    }
     */
    public Map<String, Object> getSketches() {

        Map<String, Object> newSketch = new HashMap<>();
        for (Map.Entry<String, Object> entry : sketches.entrySet()) {
            newSketch.put(entry.getKey(), entry.getValue());
        }
        return newSketch;

    }

    public void addFinalDesign(FinalDesign design) {
        finalDesign.put("Final Design " + design.getDesignName(), design.mapObjects());

        editor.setRepository(finalDesign);
        editor.writeToTextFile(repo + "finalDesign.txt");
    }

    public Map<String, Object> getFinalDesign() {

        Map<String, Object> newDesign = new HashMap<>();
        for (Map.Entry<String, Object> entry : finalDesign.entrySet()) {
            newDesign.put(entry.getKey(), entry.getValue());
        }
        return newDesign;
    }

    public void addCustomDesign(CustomDesign design) {
        customDesign.put("Custom Design " + design.getDesignName(), design.mapObjects());

        editor.setRepository(customDesign);
        editor.writeToTextFile(repo + "customDesign.txt");
    }

    public Map<String, Object> getCustomDesign() {
        Map<String, Object> newDesign = new HashMap<>();
        for (Map.Entry<String, Object> entry : customDesign.entrySet()) {
            newDesign.put(entry.getKey(), entry.getValue());
        }
        return newDesign;
    }

    public void addMarketingDesign(MarketingDesign design) {
        customDesign.put("Marketing Design " + design.getDesignSketchName(), design.mapObjects());
        editor.setRepository(customDesign);
        editor.writeToTextFile(repo + "marketingDesign.txt");
    }

    public Map<String, Object> getMarketingDesign() {
        Map<String, Object> newDesign = new HashMap<>();
        for (Map.Entry<String, Object> entry : customDesign.entrySet()) {
            newDesign.put(entry.getKey(), entry.getValue());
        }
        return newDesign;
    }

}
