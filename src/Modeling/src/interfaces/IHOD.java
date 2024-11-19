package src.Modeling.src.interfaces;

import src.Modeling.src.Event;
import src.Modeling.src.Manager;

public interface IHOD extends ITeamMember {
    Event createEvent(Boolean type, String celebrity, String collab);
//    Boolean addEvent(Event event);
//
//    void printEvent(Event event);
//
//    void printAllEvents();
//
//    void assignTask(Manager manager);
//
//    Boolean requestAdvertisement(Event event);
//
//    Boolean requestContract(String celebrity);
//
//    Boolean requestCollab(String brand);
}
