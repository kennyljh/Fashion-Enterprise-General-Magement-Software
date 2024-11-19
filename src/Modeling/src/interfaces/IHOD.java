package src.Modeling.src.interfaces;

import src.Modeling.src.Event;
import src.Modeling.src.HOD;
import src.Modeling.src.Manager;

import java.util.Map;

public interface IHOD extends ITeamMember {
    Event createEvent(Boolean type, String celebrity, String collab);
    void addManager(Manager manager);
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
