package src.Modeling.src.interfaces;

import src.Modeling.src.Event;

public interface IHOD {
    void createEvent(Boolean type, String celebrity, Boolean collab);

    Boolean requestAdvertisement(Event event);

    Boolean requestContract(String celebrity);

    Boolean requestCollab(String brand);
}
