package Modeling.src.interfaces;

import Modeling.src.Event;

public interface IHOD {
    void haveEvent(Boolean type, String celebrity, Boolean collab);

    Boolean requestAdvertisement(Event event);

    Boolean requestContract(String celebrity);

    Boolean requestCollab(String brand);
}
