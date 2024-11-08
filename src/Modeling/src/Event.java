package src.Modeling.src;

import src.HR.src.Employee;
import src.Modeling.src.interfaces.IEvent;

public class Event implements IEvent {
    int id;
    Employee[] models;
    Boolean type; //true for photoshoot, false for fashion show
    String celebrity;
    Boolean collab;
    Boolean completionStatus;


    @Override
    public Event addEvent(int id, Employee[] models, Boolean type, String celebrity, Boolean collab) {
        this.id = id;
        this.models = models;
        this.type = type;
        this.celebrity = celebrity;
        this.collab = collab;
        this.completionStatus = false;

        return this;
    }

    @Override
    public void endEvent() {
        this.completionStatus = true;
    }
}
