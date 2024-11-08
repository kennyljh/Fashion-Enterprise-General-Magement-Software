package src.Modeling.src.interfaces;

import src.HR.src.Employee;
import src.Modeling.src.Event;

public interface IEvent {
    Event addEvent(int id, Employee[] models, Boolean type, String celebrity, Boolean collab);

    void endEvent();
}
