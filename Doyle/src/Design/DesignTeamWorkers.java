package Design;

import java.util.List;

public class DesignTeamWorkers {

    private String worker;
    private List<DesignSketch> sketches;

    public DesignTeamWorkers(String worker) {
        this.worker = worker;

    }


    public DesignSketch createSketch(String sketchID, String sketchDescription){

        DesignSketch sketch = new DesignSketch(sketchID, sketchDescription);
        sketches.add(sketch);
        System.out.println(sketch);
        return sketch;
    }
    public List<DesignSketch> getSketches() {
            return sketches;
    }
    public String getWorker() {
        return worker;
    }



}
