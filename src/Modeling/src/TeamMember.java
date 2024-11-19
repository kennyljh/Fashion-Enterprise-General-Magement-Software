package src.Modeling.src;

import src.HR.src.Department;
import src.HR.src.Employee;
import src.Modeling.src.interfaces.ITeamMember;

import java.util.HashMap;
import java.util.Map;

public class TeamMember implements ITeamMember {
    static int nextid = 0;
    int id;
    Employee employeeInfo;
    Team team;

    TeamMember(int i, String name, Team team) {
        id = nextid;
        nextid++;
        employeeInfo = new Employee(Integer.toString(i), name, Department.MODELING, team.toString(), "Employed", 100000);
        this.team = team;
    }

    TeamMember(Employee employeeInfo, Team team) {
        id = nextid;
        nextid++;
        this.employeeInfo = employeeInfo;
        this.team = team;
    }
    public int getId() {
        return id;
    }

    public String toString() {
        String str = team.toString() + ": " + employeeInfo.toString();
        return str;
    }

//    public static TeamMember parseTeamMember(String memberString) {
//        String lines[] = memberString.split(": ", 2);
//        Team team = Team.parseTeam(lines[0]);
//        Employee employeeInfo = Employee.parseEmployee(lines[1]);
//        return new TeamMember(employeeInfo, team);
//    }

    public Map<String, String> toMap() {
        Map<String, String> memberDetails = new HashMap<>();
        memberDetails.put("employeeInfo", this.employeeInfo.toString());
        memberDetails.put("team", this.team.toString());
        return memberDetails;
    }
}
