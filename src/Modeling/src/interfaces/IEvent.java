package src.Modeling.src.interfaces;

import java.util.Map;

public interface IEvent {
    int getId();
    String getType();
    String getCelebrity();
    String getCollab();
    String getCompletion();
//    void endEvent();
    Map<String, String> toMap();
}
