package src.Modeling.src;

import src.HR.src.Department;
import src.HR.src.Employee;

public class TeamMember {
    Employee employeeInfo;
    Team team;

    TeamMember(int i, String name, Team team) {
        employeeInfo = new Employee(Integer.toString(i), name, Department.MODELING, team.toString(), "Employed", 100000);
        team = team;
    }

    public String toString() {
        String str = team.toString() + ": " + employeeInfo.toString();
        return str;
    }
}
