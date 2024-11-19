package src.Modeling.src.interfaces;

import src.Modeling.src.Manager;
import src.Modeling.src.Team;
import src.Modeling.src.TeamMember;

import java.time.LocalDateTime;

public interface IHOD extends ITeamMember {
    void createEvent(Boolean type, String celebrity, String collab);
    void addManager(Manager manager);
    Manager getManager(Team team);

    void requestFitting(Team team, TeamMember model, String garment, LocalDateTime date);
}
