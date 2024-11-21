package src.Design.src;


//import src.App;

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
    private DesignSketch sketch;
    private CustomDesign customDesign;
    private MarketingDesign marketingDesign;
    private final DesignFileManager designFileManager = new DesignFileManager();

    public DesignSpecificationsController() {

        sketches = new ArrayList<>();
        headOfDesignTeam = new HeadOfDesignTeam(sketches); // they ask for sketches

    }


    public void run() throws Exception {


        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Welcome to the Design Management System.");

        while (!exit) {
            System.out.println("1. Create a Design Sketch");
            System.out.println("2. View and Select a Design Sketch");
            System.out.println("3. Set a Final Design for Manufacturing");
            System.out.println("4. Set a Final Design for Marketing");
            System.out.println("5. Set a custom Final Design for Modelling");
            System.out.println("6. Create a Marketing Design based on Final Design");
            System.out.println("7. Send Designs to Repository.");
            System.out.println("8. Exit Program");

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
                    int designQuantity = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter image of the design: ");
                    String imageOfDesign = scan.nextLine();
                    //call to helper to set all specifications
                    createDesignSketch(designName, rawMaterialsNeeded, colorsNeeded, sizesNeeded, designQuantity, imageOfDesign);

                    break;
                case 2:
                    headOfDesignTeam.viewSketches(sketches);
                    Map<String, Object> storedSketches = designFileManager.getSketches();
                    if (sketches.isEmpty() || storedSketches.isEmpty()) {
                        System.out.println("No sketches found.");
                        break;
                    }
                    System.out.println("Available Design Sketches: ");
                    int i = 0;
                    for (String sketchName : storedSketches.keySet()) {
                        System.out.println((i + 1) + "." + sketchName);
                    }
                    //ask for input
                    System.out.println("Select the Design Sketch to verify");
                    int sketchNumber = scan.nextInt() - 1;
                    scan.nextLine();

                    if (sketchNumber < 0 || sketchNumber >= sketches.size()) {
                        System.out.println("Invalid sketch number");
                        break;
                    }

                    String selectedSketchName = new ArrayList<>(storedSketches.keySet()).get(sketchNumber);
                    Map<String, Object> selectedSketchDetails = (Map<String, Object>) storedSketches.get(selectedSketchName);

                    selectedSketchDetails.forEach((key, value) -> {
                        System.out.println(key + ": " + value);
                    });

                    headOfDesignTeam.selectSketch(sketchNumber, sketches);
                    System.out.println("(Y/N) Do you want to verify the design sketch?");
                    String verifyDesign = scan.nextLine();
                    if (verifyDesign.equals("Y")) {
                        //call to begin Final Design case 3

                        FinalDesign selectedSketch = headOfDesignTeam.confirmFinalDesign();
                        if (selectedSketch != null) {
                            System.out.println("Sketch Verfied\nDisplay specifications:\n" + selectedSketch.displayAllSpecifications());
                            finalDesign = selectedSketch;
                        } else {
                            System.out.println("No sketch has been selected. Try again.");
                        }
                    } else if (verifyDesign.equals("N")) {
                        System.out.println("Design was not verified");
                    } else {
                        System.out.println("Invalid Input. Select Y or N");
                    }
                    break;
                case 3:

                    if (finalDesign == null) {
                        System.out.println("No Final Design has been set. Verify a design first");
                        break;
                    } else {
                        System.out.println("Design was verified by the Head of Design");
                    }
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
                        int finalDesignQuantity = scan.nextInt();
                        scan.nextLine();
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
                    if (finalDesign == null) {
                        System.out.println("No Final Design has been set. Verify a design first");
                        break;
                    } else {
                        System.out.println("Design was verified by the Head of Design");
                    }
                    System.out.println("Current Final Design Specifications: \n" + finalDesign.displayAllSpecifications());
                    System.out.println("Do you want to modify the current final design?");
                    System.out.println("(Y/N)");
                    String modifyDesign2 = scan.nextLine();
                    if (modifyDesign2.equals("Y")) {
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
                        int finalDesignQuantity = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter image of the design: ");
                        String finalImageOfDesign = scan.nextLine();
                        setFinalDesign(finalDesignName, finalRawMaterialsNeeded, finalColorsNeeded, finalSizesNeeded, finalDesignQuantity, finalImageOfDesign);
                    } else if (modifyDesign2.equals("N")) {
                        System.out.println("No changes are needed in the Final Design");
                        break;
                    } else {
                        System.out.println("Invalid Input. Select Y or N");
                    }
                case 5:
                    if (customDesign == null) {
                        System.out.println("No Final Design has been set. Verify a design first");
                        break;
                    } else {
                        System.out.println("Design was verified by the Head of Design");
                    }
                    System.out.println("Current Final Design Specifications: \n" + customDesign.displayAllSpecifications());
                    System.out.println("Do you want to modify the current final design?");
                    System.out.println("(Y/N)");
                    String response = scan.nextLine();
                    if (response.equals("Y")) {
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
                        int finalDesignQuantity = scan.nextInt();
                        scan.nextLine();
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
                    if (marketingDesign == null) {
                        System.out.println("No Design has been set. Verify a design first");
                        break;
                    } else {
                        System.out.println("Design was verified by the Head of Design");
                    }
                    System.out.println("Current Final Design Specifications: \n" + finalDesign.displayAllSpecifications());
                    System.out.println("Begin setting marketing Design based on the current final Design.");
                    MarketingDesign currentMarketingDesign = new MarketingDesign(finalDesign);
                    System.out.println("Final Design Name is: " + currentMarketingDesign.getDesignSketchName());
                    String marketingName = scan.nextLine();
                    System.out.println("Enter design price: ");
                    String price = scan.nextLine();
                    System.out.println("Enter target Audience: ");
                    String targetAudience = scan.nextLine();
                    System.out.println("Enter design description: ");
                    String description = scan.nextLine();
                    System.out.println("Enter season Type: ");
                    String seasonType = scan.nextLine();

                    setMarketingDesign(marketingName, targetAudience, price, description, seasonType);
                    System.out.println("Marketing Design has been set");

                case 7:
                    //sending to manufacturing
//                    designFileManager.addFinalDesign(finalDesign);
//                    sendToFinalDesign();
                    designFileManager.sendDataToRepo();

                case 8:
                    System.out.println("Exit Program");
                    exit = true;
//                    App.prompt();
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }
        scan.close();
    }

    //lists all the things needed to create a new sketch which will then be reviewed
    public void createDesignSketch(String designName, String rawMaterialsNeeded, String colorsNeeded, String sizesNeeded, int designQuantity, String imageOfDesign) {

        List<String> rawMaterials = List.of(rawMaterialsNeeded.split(","));
        List<String> colors = List.of(colorsNeeded.split(","));
        List<String> sizes = List.of(sizesNeeded.split(","));
        //make sure I am calling this correctly
        sketch = new DesignSketch(designName);
        sketch.setDesignName(designName);
        sketch.setRawMaterials(rawMaterials);
        sketch.setColor(colors);
        sketch.setSizes(sizes);
        sketch.setQuantities(designQuantity);
        sketch.setDesignImage(imageOfDesign);
        sketches.add(sketch);
        sketch.displayAllSpecifications();
        designFileManager.addSketch(sketch);
        System.out.println("Sketch created and added to a list of sketches waiting to be approved");

    }

    public void setFinalDesign(String finalDesignName, String finalRawMaterialsNeeded, String finalColorsNeeded, String finalSizesNeeded, int finalDesignQuantity, String finalImageOfDesign) {

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
        designFileManager.addFinalDesign(finalDesign);
        System.out.println("Final Design has been set with all specifications");
        finalDesign.displayAllSpecifications();

    }

    public void setCustomDesign(String customDesignName, String rawMaterialsNeeded, String colorsNeeded, String sizeNeeded, int designQuantity, String imageOfDesign) {

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
        designFileManager.addCustomDesign(customDesign);
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
        designFileManager.addMarketingDesign(marketingDesign);
        System.out.println("MarketingDesign has been set with all specifications");
        marketingDesign.displayAllSpecifications();

    }


}
