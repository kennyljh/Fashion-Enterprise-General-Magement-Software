package src.Design.src;


//import src.App;

import src.Design.src.interfaces.DesignSpecifications;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DesignSpecificationsController {


    private HeadOfDesignTeam headOfDesignTeam;
    private List<DesignSketch> sketches;
    private FinalDesign finalDesign;
    private DesignSketch sketch;
    private CustomDesign customDesign;

    public DesignSpecificationsController() {

        sketches = new ArrayList<>();
        headOfDesignTeam = new HeadOfDesignTeam(sketches); // they ask for sketches

    }


    public void run() throws Exception {


        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Welcome to the Design Management System.");

        while (!exit) {
            System.out.println("1. Add a Design Sketch");
            System.out.println("2. View and Select a Design Sketch");
            System.out.println("3. Set a Final Design for Manufacturing");
            System.out.println("4. Set a Final Design for Marketing");
            System.out.println("5. Set a custom Final Design for Modelling");
            System.out.println("6. Send Final Design to Manufacturing");
            System.out.println("7. Send Final Design to Marketing");
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
                    sendToDesignSketch();
                    break;
                case 2:
                    headOfDesignTeam.viewSketches(sketches);
                    if (sketches.isEmpty()) {
                        break;
                    }
                    System.out.println("Select the Design Sketch to verify");
                    int sketchNumber = scan.nextInt() - 1;
                    scan.nextLine();
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
                        sendToFinalDesign();
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
                        sendToFinalDesign();
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
                        setFinalDesign(finalDesignName, finalRawMaterialsNeeded, finalColorsNeeded, finalSizesNeeded, finalDesignQuantity, finalImageOfDesign);
                        sendToCustomDesign();
                    } else if (response.equals("N")) {
                        System.out.println("No changes are needed in the Final Design");
                        break;
                    } else {
                        System.out.println("Invalid Input. Select Y or N");
                    }

                case 6:
                    //sending to manufacturing


                case 7:
                    //sending to marketing
                    if (finalDesign == null) {
                        System.out.println("No Final Design has been set to export. Verify a design first");
                        return;
                    }
                    try (PrintWriter writer = new PrintWriter("marketing_design.txt")) {
                        writer.write(finalDesign.toString());
                        System.out.println(finalDesign.toString() + "has been exported");
                    } catch (IOException e) {
                        System.out.println("Error exporting file" + e.getMessage());
                        e.printStackTrace();

                    }

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

        System.out.println("Final Design has been set with all specifications");
        finalDesign.displayAllSpecifications();
    }


    public void sendToDesignSketch() {
        String localPath = "src/Design/repository/DesignSketch/";

        //use PoorFileReader
        File dir = new File(localPath);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created at: " + localPath);
            } else {
                System.out.println("Directory could not be created at " + localPath);
                return;
            }
        }
        if (sketches == null) {
            System.out.println("No Final Design has been set to export. Verify a design first");
            return;
        }
        //use Poor File Reader
        try (PrintWriter writer = new PrintWriter("sketch_design.txt")) {
            for (DesignSketch designSketch : sketches) {
                writer.write(designSketch.displayAllSpecifications());
                System.out.println(designSketch.displayAllSpecifications() + "has been exported to " + localPath + "manufacturing_design.txt");
            }
        } catch (IOException e) {
            System.out.println("Error exporting file" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendToFinalDesign() {
        String localPath = "src/Design/repository/FinalDesign/";
        //use PoorFileReader
        File dir = new File(localPath);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created at: " + localPath);
            } else {
                System.out.println("Directory could not be created at " + localPath);
                return;
            }
        }

        if (finalDesign == null) {
            System.out.println("No Final Design has been set to export. Verify a design first");
            return;
        }
        try (PrintWriter writer = new PrintWriter("final_design.txt")) {
            writer.write(finalDesign.displayAllSpecifications());
            System.out.println(finalDesign.displayAllSpecifications() + "has been exported to " + localPath + "final_design.txt");

        } catch (IOException e) {
            System.out.println("Error exporting file" + e.getMessage());
            e.printStackTrace();
        }

    }

    public void sendToCustomDesign() {
        String localPath = "Design/repository/CustomDesign/";
        //use PoorFileReader
        File dir = new File(localPath);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created at: " + localPath);
            } else {
                System.out.println("Directory could not be created at " + localPath);
                return;
            }
        }

        if (customDesign == null) {
            System.out.println("No Final Design has been set to export. Verify a design first");
            return;
        }
        try (PrintWriter writer = new PrintWriter("custom_design.txt")) {
            writer.write(customDesign.displayAllSpecifications());
            System.out.println(customDesign.displayAllSpecifications() + "has been exported to " + localPath + "custom_design.txt");

        } catch (IOException e) {
            System.out.println("Error exporting file" + e.getMessage());
            e.printStackTrace();
        }

    }

}
