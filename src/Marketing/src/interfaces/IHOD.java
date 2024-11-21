package src.Marketing.src.interfaces;

import src.Marketing.src.Manager;
import src.Marketing.src.Team;

import java.util.Map;

public interface IHOD extends ITeamMember{
    Map<String, String> toMap();
    void addManager(Manager manager);
    Manager getManager(Team team);
}
