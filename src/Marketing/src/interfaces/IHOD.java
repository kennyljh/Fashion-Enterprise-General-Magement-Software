package src.Marketing.src.interfaces;

import src.Marketing.src.AdvertType;
import src.Marketing.src.EventAdvertisement;
import src.Marketing.src.Manager;
import src.Marketing.src.Team;
import src.Modeling.src.Event;

import java.util.ArrayList;
import java.util.Map;

public interface IHOD extends ITeamMember{
    ArrayList<EventAdvertisement> getEventAdverts();

    EventAdvertisement createEventAdvert(Event event, AdvertType type);

    Map<String, String> toMap();
    void addManager(Manager manager);
    Manager getManager(Team team);

    Event requestPhotoshoot(int modelId);
}
