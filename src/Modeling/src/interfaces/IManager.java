package src.Modeling.src.interfaces;

import src.HR.src.Employee;
import src.Modeling.src.Fitting;
import src.Modeling.src.TeamMember;

import java.time.LocalDateTime;

public interface IManager extends ITeamMember {
    Fitting requestFitting(Employee model, LocalDateTime date);

    Boolean scheduleModel(Employee model);

    TeamMember[] getTeamMembers();
}
