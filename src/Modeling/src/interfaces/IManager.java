package src.Modeling.src.interfaces;

import src.HR.src.Employee;
import src.Modeling.src.Fitting;
import src.Modeling.src.Manager;
import src.Modeling.src.Team;
import src.Modeling.src.TeamMember;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IManager extends ITeamMember {
//    Fitting requestFitting(Employee model, LocalDateTime date);
//
//    Boolean scheduleModel(Employee model);

    public ArrayList<TeamMember> getTeamMembers();
    public void addTeamMember(TeamMember member);
//    public void removeTeamMember(TeamMember member);
}
