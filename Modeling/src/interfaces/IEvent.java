package Modeling.src.interfaces;

import HR.src.Employee;
import Modeling.src.Event;

public interface IEvent {
    Event addEvent(int id, Employee[] models, Boolean type, String celebrity, Boolean collab);

    void endEvent();
}
