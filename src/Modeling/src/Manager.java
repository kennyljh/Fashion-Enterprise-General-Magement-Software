package src.Modeling.src;

import src.App;
import src.HR.src.Department;
import src.HR.src.Employee;
import src.Modeling.ModelingDepartment;
import src.Modeling.src.interfaces.IManager;

import java.time.LocalDateTime;
import java.util.*;

public class Manager implements IManager {
    static int nextid = 0;
    int id;

    private final Employee employeeInfo;
    private final ArrayList<TeamMember> teamMembers;
    private final Team team;

//    public Manager(String name, Team team) {
//        id = nextid;
//        nextid++;
//        employeeInfo = App.hrDepartment.getEmployee(Department.MODELING, name);
//        teamMembers = ModelingDepartment.fileManager.getTeamMembers(team);
//        this.team = team;
//    }

    public Manager(int id, Employee employeeInfo, Team team) {
        this.id = id;
        if(id>=nextid) nextid = id+1;
        this.employeeInfo = employeeInfo;
        teamMembers = ModelingDepartment.fileManager.getTeamMembers(team);
        this.team = team;
    }

    public Manager(Team team) {
        id = nextid;
        nextid++;
        employeeInfo = new Employee("manager", team.toString() + " Manager", Department.MODELING, "Manager", "Employeed", 100000);
        teamMembers = new ArrayList<>();
        for(int i = 0; i <= 3; i++) {
            teamMembers.add(new TeamMember(team));
        }
        this.team = team;

        ModelingDepartment.fileManager.addManager(this);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Employee getEmployeeInfo() {
        return employeeInfo;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public ArrayList<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    @Override
    public void addTeamMember(TeamMember member) {
        teamMembers.add(member);
        ModelingDepartment.fileManager.addTeamMember(member);
    }

    @Override
    public Fitting scheduleFitting(TeamMember model, String garment, LocalDateTime date) {
        return new Fitting(model, garment, date);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> memberDetails = new HashMap<>();
        memberDetails.put("id", Integer.toString(this.id));
        memberDetails.put("employeeInfo", this.employeeInfo.toString());
        Integer[] tmp = new Integer[teamMembers.size()];
        for(int i = 0; i < teamMembers.size(); i++) {
            tmp[i] = teamMembers.get(i).getId();
        }
        memberDetails.put("teamMembers", Arrays.toString(tmp));
        memberDetails.put("team", this.team.toString());
        return memberDetails;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("\n" + this.team.toString() + " Manager: " + id);
        str.append("\nEmployeeInfo: ").append(this.employeeInfo.toString());
        str.append("\nTeam Members: ");
        for (TeamMember teamMember : teamMembers) {
            str.append("\n").append(teamMember.toString());
        }
        return str.toString();
    }

    public static Manager parse(Map<String, String> manager) {
        return new Manager(
            Integer.parseInt(manager.get("id")),
            Employee.parseEmployee(manager.get("employeeInfo")),
            Team.parseTeam(manager.get("team"))
        );
    }
}