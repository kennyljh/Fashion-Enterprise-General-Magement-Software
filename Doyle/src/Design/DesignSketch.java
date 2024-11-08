package Design;

public class DesignSketch {

  private final String sketchId;
  private final String sketchDescription;

  public DesignSketch(String sketchId, String sketchDescription) {
      this.sketchId = sketchId;
      this.sketchDescription = sketchDescription;
  }
  public String getSketchId() {
      return sketchId;
  }
  public String getSketchDescription() {
      return sketchDescription;
  }
}
