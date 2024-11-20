package src.Design.src.interfaces;

import src.Design.DesignDepartment;
import src.Design.src.CustomDesign;
import src.Design.src.DesignSketch;
import src.Design.src.FinalDesign;

import java.util.List;

public interface HeadOfDesignInterface {

  void viewSketches(List<DesignSketch> sketches);

  void selectSketch(int sketchIndex, List<DesignSketch> sketchList);

  void receiveCustomDesign(CustomDesign customDesign);

  FinalDesign confirmFinalDesign();

  CustomDesign confirmCustomDesign();
}
