package src.Design.src;


//import src.App;

import src.App;
import src.Design.src.interfaces.DesignSpecifications;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DesignSpecificationsController {


    private HeadOfDesignTeam headOfDesignTeam;
    private List<DesignSketch> sketches;
    private FinalDesign finalDesign;
    private CustomDesign customDesign;
    private MarketingDesign marketingDesign;
    private final DesignFileManager designFileManager;

    public DesignSpecificationsController() {
        this.sketches = new ArrayList<>();
        headOfDesignTeam = new HeadOfDesignTeam(sketches); // they ask for sketches
        this.designFileManager = new DesignFileManager();
        designFileManager.initialize();
    }


    public void run() throws Exception {


        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Welcome to the Design Management System.");

        while (!exit) {
            System.out.println("1. Create a Design Sketch");
            System.out.println("2. View and Select a Design Sketch");
            System.out.println("3. Set a Final Design for Manufacturing");
            System.out.println("4. Create and Set a Final Design for Marketing");
            System.out.println("5. Create and Set a custom Design for Modelling");
            System.out.println("6. Exit Program");

            int option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Begin Listing the Specifications for the design sketch.");
                    System.out.println("If entering multiple entries input as such S,M,L with no spaces");
                    System.out.println("Enter Design Name: ");
                    String designName = scan.nextLine();
                    System.out.println("Enter raw materials needed: ");
                    String rawMaterialsNeeded = scan.nextLine();
                    System.out.println("Enter the colors you want for design: ");
                    String colorsNeeded = scan.nextLine();
                    System.out.println("Enter sizes for design: ");
                    String sizesNeeded = scan.nextLine();
                    System.out.println("Enter design  quantity: ");
                    String designQuantity = scan.nextLine();
//                    scan.nextLine();
                    System.out.println("Enter image of the design: ");
                    String imageOfDesign = scan.nextLine();
                    //call to helper to set all specifications
                    createDesignSketch(designName, rawMaterialsNeeded, colorsNeeded, sizesNeeded, designQuantity, imageOfDesign);
                    break;
                case 2:
//                    headOfDesignTeam.viewSketches(sketches);
                    Map<String, Object> storedSketches = designFileManager.getSketch(); //check to see if it retrieves all the sketches
                    if (storedSketches == null || storedSketches.isEmpty()) {
                        System.out.println("No sketches found in repository.");
                        return;
                    }
                    System.out.println("Available Design Sketches: ");
                    List<String> sketchPosition = new ArrayList<>(storedSketches.keySet());
                    for (int i = 0; i < sketchPosition.size(); i++) {
                        System.out.println((i + 1) + ". " + sketchPosition.get(i));
                    }

                    //ask for input
                    System.out.println("Select the Design Sketch to verify by the number");
                    int sketchNumber = scan.nextInt() - 1;
                    scan.nextLine();

                    if (sketchNumber < 0 || sketchNumber >= sketchPosition.size()) {
                        System.out.println("Invalid sketch number");
                        return;
                    }

                    String selectedSketchName = sketchPosition.get(sketchNumber);
//                    DesignSketch selectedSketch = (DesignSketch) storedSketches.get(selectedSketchName);
                    Map<String, Object> selectedSketch = (Map<String, Object>) storedSketches.get(selectedSketchName);

                    if (selectedSketch == null) {
                        System.out.println("No sketch found with name " + selectedSketchName);
                        return;
                    }
                    DesignSketch newSketch = new DesignSketch(selectedSketchName);
                    newSketch.setDesignName((String) selectedSketch.get("DesignName"));
                    newSketch.setRawMaterials(List.of((String) selectedSketch.get("DesignRawMaterials")));
                    newSketch.setColor(List.of((String) selectedSketch.get("DesignColors")));
                    newSketch.setSizes(List.of((String) selectedSketch.get("DesignSizes")));
                    newSketch.setQuantities((String) selectedSketch.get("DesignQuantities"));
                    newSketch.setDesignImage((String) selectedSketch.get("DesignImage"));

                    sketches.add(newSketch);

                    System.out.println("You selected the following sketch: " + selectedSketchName);
                    selectedSketch.forEach((key, value) -> {
                        System.out.println(key + ": " + value);
                    });


//                    headOfDesignTeam.selectSketch(sketchNumber, sketches);
                    System.out.println("(Y/N) Do you want to verify the design sketch?");
                    String verifyDesign = scan.nextLine();
                    if (verifyDesign.equals("Y")) {
                        //call to begin Final Design case 3

//                        finalDesign = new FinalDesign(selectedSketchName);
                        headOfDesignTeam.selectSketch(sketches.indexOf(newSketch), sketches);
                        finalDesign = headOfDesignTeam.confirmFinalDesign();
                        if (finalDesign != null) {
                            designFileManager.saveFinalDesign(finalDesign);
                            System.out.println("Sketch Verfied\nDisplay specifications:\n" + finalDesign.displayAllSpecifications());
                        } else {
                            System.out.println("Failed to confirm final design");
                        }

                    } else if (verifyDesign.equals("N")) {
                        System.out.println("Design was not verified");
                    } else {
                        System.out.println("Invalid Input. Select Y or N");
                    }
                    break;
                case 3:
                    Map<String, Object> storedFinalDesigns = designFileManager.getFinalDesign();
                    if (storedFinalDesigns == null || storedFinalDesigns.isEmpty()) {
                        System.out.println("No sketches found in repository.");
                        return;
                    }
                    System.out.println("Available Final Designs: ");
                    List<String> finalDesignNames = new ArrayList<>(storedFinalDesigns.keySet());
                    for (int i = 0; i < finalDesignNames.size(); i++) {
                        System.out.println((i + 1) + ". " + finalDesignNames.get(i));
                    }

                    System.out.println("Select a Final Design for Manufacturing");
                    int designNumber = scan.nextInt() - 1;
                    scan.nextLine();

                    if (designNumber < 0 || designNumber >= finalDesignNames.size()) {
                        System.out.println("Invalid design number");
                        return;
                    }
                    String selectedFinalDesignName = finalDesignNames.get(designNumber);
                    Map<String, Object> selectedFinalDesign = (Map<String, Object>) storedFinalDesigns.get(selectedFinalDesignName);

                    finalDesign = new FinalDesign(selectedFinalDesignName);
                    finalDesign.setDesignName((String) selectedFinalDesign.get("DesignName"));
                    finalDesign.setRawMaterials(List.of((String) selectedFinalDesign.get("DesignRawMaterials")));
                    finalDesign.setColor(List.of((String) selectedFinalDesign.get("DesignColors")));
                    finalDesign.setSizes(List.of((String) selectedFinalDesign.get("DesignSizes")));
                    finalDesign.setQuantities((String) selectedFinalDesign.get("DesignQuantities"));
                    finalDesign.setDesignImage((String) selectedFinalDesign.get("DesignImage"));


                    System.out.println("Current Final Design Specifications: \n" + finalDesign.displayAllSpecifications());
                    System.out.println("Do you want to modify the current final design?");
                    System.out.println("(Y/N)");
                    String modifyDesign = scan.nextLine();
                    if (modifyDesign.equals("Y")) {
                        System.out.println("Set a Final Design and set specifications of the final design");
                        System.out.println("Enter Design Name: ");
                        String finalDesignName = scan.nextLine();
                        System.out.println("Enter raw materials needed: ");
                        String finalRawMaterialsNeeded = scan.nextLine();
                        System.out.println("Enter the colors you want for design: ");
                        String finalColorsNeeded = scan.nextLine();
                        System.out.println("Enter sizes for design: ");
                        String finalSizesNeeded = scan.nextLine();
                        System.out.println("Enter design  quantity: ");
                        String finalDesignQuantity = scan.nextLine();
                        System.out.println("Enter image of the design: ");
                        String finalImageOfDesign = scan.nextLine();
                        setFinalDesign(finalDesignName, finalRawMaterialsNeeded, finalColorsNeeded, finalSizesNeeded, finalDesignQuantity, finalImageOfDesign);
                    } else if (modifyDesign.equals("N")) {
                        System.out.println("No changes are needed in the Final Design");
                        break;
                    } else {
                        System.out.println("Invalid Input. Select Y or N");
                    }
                case 4:

                    Map<String, Object> storedMarketingDesigns = designFileManager.getFinalDesign();
                    if (storedMarketingDesigns == null || storedMarketingDesigns.isEmpty()) {
                        System.out.println("No sketches found in repository.");
                        return;
                    }
                    System.out.println("Available Final Designs: ");
                    List<String> finalMarketingNames = new ArrayList<>(storedMarketingDesigns.keySet());
                    for (int i = 0; i < finalMarketingNames.size(); i++) {
                        System.out.println((i + 1) + ". " + finalMarketingNames.get(i));
                    }

                    System.out.println("Select a Final Design for Marketing");
                    int marketingNumber = scan.nextInt() - 1;
                    scan.nextLine();

                    if (marketingNumber < 0 || marketingNumber >= finalMarketingNames.size()) {
                        System.out.println("Invalid design number");
                        return;
                    }
                    String selectedMarketingDesignName = finalMarketingNames.get(marketingNumber);
                    Map<String, Object> selectedMarketingDesign = (Map<String, Object>) storedMarketingDesigns.get(selectedMarketingDesignName);

                    finalDesign = new FinalDesign(selectedMarketingDesignName);
                    finalDesign.setDesignName((String) selectedMarketingDesign.get("DesignName"));
                    finalDesign.setRawMaterials(List.of((String) selectedMarketingDesign.get("DesignRawMaterials")));
                    finalDesign.setColor(List.of((String) selectedMarketingDesign.get("DesignColors")));
                    finalDesign.setSizes(List.of((String) selectedMarketingDesign.get("DesignSizes")));
                    finalDesign.setQuantities((String) selectedMarketingDesign.get("DesignQuantities"));
                    finalDesign.setDesignImage((String) selectedMarketingDesign.get("DesignImage"));
                    System.out.println("Current Final Design Specifications: \n" + finalDesign.displayAllSpecifications());

                    marketingDesign = new MarketingDesign(finalDesign);

                    System.out.println("Do you want to begin setting the marketing Design?");
                    System.out.println("(Y/N)");
                    String modifyDesign2 = scan.nextLine();
                    if (modifyDesign2.equals("Y")) {
                        System.out.println("Set Marketing Specifications based on Final Design: ");
                        System.out.println("Enter Marketing Design Name: ");
                        String marketingDesignName = scan.nextLine();
                        System.out.println("Enter Target Audience: ");
                        String audience = scan.nextLine();
                        System.out.println("Enter Marketing Price: ");
                        String price = scan.nextLine();
                        System.out.println("Enter Design Description: ");
                        String description = scan.nextLine();
                        System.out.println("Enter Season Type: ");
                        String seasonType = scan.nextLine();
                        setMarketingDesign(marketingDesignName, audience, price, description, seasonType);
//                        designFileManager.saveMarketingDesign(marketingDesign);
                        System.out.println("Saved Marketing Design Specifications: \n" + marketingDesign.displayAllSpecifications());


                    } else if (modifyDesign2.equals("N")) {
                        System.out.println("No changes are needed in the Final Design");
                        break;
                    } else {
                        System.out.println("Invalid Input. Select Y or N");
                    }
                case 5:


                    System.out.println("Do you want to create a custom Design for modelling?");
                    System.out.println("(Y/N)");
                    String response = scan.nextLine();
                    if (response.equals("Y")) {
                        System.out.println("Set a Custom Design and set specifications for the final design");
                        System.out.println("Enter Design Name: ");
                        String finalDesignName = scan.nextLine();
                        System.out.println("Enter raw materials needed: ");
                        String finalRawMaterialsNeeded = scan.nextLine();
                        System.out.println("Enter the colors you want for design: ");
                        String finalColorsNeeded = scan.nextLine();
                        System.out.println("Enter sizes for design: ");
                        String finalSizesNeeded = scan.nextLine();
                        System.out.println("Enter design  quantity: ");
                        String finalDesignQuantity = scan.nextLine();
                        System.out.println("Enter image of the design: ");
                        String finalImageOfDesign = scan.nextLine();
                        //have to change to set custom design
                        setCustomDesign(finalDesignName, finalRawMaterialsNeeded, finalColorsNeeded, finalSizesNeeded, finalDesignQuantity, finalImageOfDesign);

                    } else if (response.equals("N")) {
                        System.out.println("No changes are needed in the Final Design");
                        break;
                    } else {
                        System.out.println("Invalid Input. Select Y or N");
                    }

                case 6:
                    System.out.println("Exit Program");
                    exit = true;
                    App.prompt();
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }
        scan.close();
    }

    //lists all the things needed to create a new sketch which will then be reviewed
    public void createDesignSketch(String designName, String rawMaterialsNeeded, String colorsNeeded, String sizesNeeded, String designQuantity, String imageOfDesign) {

        List<String> rawMaterials = List.of(rawMaterialsNeeded.split(","));
        List<String> colors = List.of(colorsNeeded.split(","));
        List<String> sizes = List.of(sizesNeeded.split(","));
        //make sure I am calling this correctly
        DesignSketch sketch = new DesignSketch(designName);
        sketch.setDesignName(designName);
        sketch.setRawMaterials(rawMaterials);
        sketch.setColor(colors);
        sketch.setSizes(sizes);
        sketch.setQuantities(designQuantity);
        sketch.setDesignImage(imageOfDesign);
        sketches.add(sketch);
        sketch.displayAllSpecifications();
        designFileManager.saveSketch(sketch);
        System.out.println("Sketch created and added to a list of sketches waiting to be approved");

    }

    public void setFinalDesign(String finalDesignName, String finalRawMaterialsNeeded, String finalColorsNeeded, String finalSizesNeeded, String finalDesignQuantity, String finalImageOfDesign) {

        List<String> rawMaterials = List.of(finalRawMaterialsNeeded.split(","));
        List<String> colors = List.of(finalColorsNeeded.split(","));
        List<String> sizes = List.of(finalSizesNeeded.split(","));

        finalDesign = new FinalDesign(finalDesignName);

        finalDesign.setDesignName(finalDesignName);
        finalDesign.setRawMaterials(rawMaterials);
        finalDesign.setColor(colors);
        finalDesign.setSizes(sizes);
        finalDesign.setQuantities(finalDesignQuantity);
        finalDesign.setDesignImage(finalImageOfDesign);
        designFileManager.saveFinalDesign(finalDesign);
        System.out.println("Final Design has been set with all specifications");
        finalDesign.displayAllSpecifications();

    }

    public void setCustomDesign(String customDesignName, String rawMaterialsNeeded, String colorsNeeded, String sizeNeeded, String designQuantity, String imageOfDesign) {

        List<String> rawMaterials = List.of(rawMaterialsNeeded.split(","));
        List<String> colors = List.of(colorsNeeded.split(","));
        List<String> sizes = List.of(sizeNeeded.split(","));

        customDesign = new CustomDesign(customDesignName);
        customDesign.setRawMaterials(rawMaterials);
        customDesign.setColor(colors);
        customDesign.setSizes(sizes);
        customDesign.setQuantities(designQuantity);
        customDesign.setDesignImage(imageOfDesign);
        customDesign.displayAllSpecifications();
        designFileManager.saveCustomDesign(customDesign);
        System.out.println("Custom Design has been set with all specifications");
        customDesign.displayAllSpecifications();

    }

    public void setMarketingDesign(String sketch, String targetAudience, String price, String description, String seasonType) {

        marketingDesign = new MarketingDesign(finalDesign);
        marketingDesign.setDesignSketchName(sketch);
        marketingDesign.setPrice(price);
        marketingDesign.setProductDescription(description);
        marketingDesign.setSeasonType(seasonType);
        marketingDesign.setTargetAudience(targetAudience);
        designFileManager.saveMarketingDesign(marketingDesign);
        System.out.println("MarketingDesign has been set with all specifications");
        marketingDesign.displayAllSpecifications();

    }


}
