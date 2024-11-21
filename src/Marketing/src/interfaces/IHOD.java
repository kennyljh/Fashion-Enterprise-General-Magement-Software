package src.Marketing.src.interfaces;

import src.Marketing.src.*;
import src.Modeling.src.Event;

import java.util.ArrayList;
import java.util.Map;

public interface IHOD extends ITeamMember{
    ArrayList<EventAdvertisement> getEventAdverts();

    EventAdvertisement createEventAdvert(Event event, AdvertType type);

    DesignAdvertisement createDesignAdvert(AdvertType type, String notes);

    Map<String, String> toMap();
    void addManager(Manager manager);
    Manager getManager(Team team);

    Event requestPhotoshoot(int modelId);
}
