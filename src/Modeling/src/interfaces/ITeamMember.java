package src.Modeling.src.interfaces;

import src.HR.src.Employee;

import java.util.Map;

public interface ITeamMember {
    public int getId();

    Employee getEmployeeInfo();

    public Map<String, String> toMap();
}
