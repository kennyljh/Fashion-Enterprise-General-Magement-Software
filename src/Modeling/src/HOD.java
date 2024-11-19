package src.Modeling.src;

import src.HR.src.Department;
import src.HR.src.Employee;
import src.Modeling.ModelingDepartment;
import src.Modeling.src.interfaces.IHOD;

import java.time.LocalDateTime;
import java.util.*;

public class HOD implements IHOD {

    private final Employee employeeInfo;
    private final ArrayList<Manager> managers;

    public ArrayList<Event> events;
    public ArrayList<Fitting> fittings;

    public HOD(Employee employeeInfo, ArrayList<Manager> managers) {
        this.employeeInfo = employeeInfo;
        this.managers = managers;

        events = ModelingDepartment.fileManager.getEvents();
        fittings = ModelingDepartment.fileManager.getFittings();
    }

    public HOD() {
        employeeInfo = new Employee("hod", "Head of Modeling", Department.MODELING, "HOD", "Employeed", 100000);
        managers = new ArrayList<>();
        managers.add(new Manager(Team.MODELING));
        managers.add(new Manager(Team.MAKEUP));
        managers.add(new Manager(Team.CLOTHING));

        ModelingDepartment.fileManager.addHOD(this);
    }

    @Override
    public void createEvent(Boolean type, String celebrity, String collab) {
        ArrayList<TeamMember> teamMembers = new ArrayList<>();

        for(Manager manager: managers) {
            ArrayList<TeamMember> team = manager.getTeamMembers();
            team.addAll(manager.getTeamMembers());
        }

        Event event = new Event(teamMembers, type, celebrity, collab);

        events.add(event);
        System.out.println(event.toString());
        ModelingDepartment.fileManager.addEvent(event);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> memberDetails = new HashMap<>();
        memberDetails.put("employeeInfo", this.employeeInfo.toString());
        Integer[] tmp = new Integer[managers.size()];
        for(int i = 0; i < managers.size(); i++) {
            tmp[i] = managers.get(i).getId();
        }
        memberDetails.put("managers", Arrays.toString(tmp));
        return memberDetails;
    }

    @Override
    public void addManager(Manager manager) {
        managers.add(manager);
        ModelingDepartment.fileManager.addManager(manager);
    }

    @Override
    public Manager getManager(Team team) {
        for (Manager manager: managers) {
            if (manager.getTeam() == team) {
                return manager;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "\nHOD: ";
        str += "\nEmployeeInfo: " + this.employeeInfo.toString();
        str += "\nManagers: ";
        for (int i = 0; i < managers.size(); i++) {
            str += "\n  " + managers.get(i).toString();
        }
        return str;
    }

    @Override
    public void requestFitting(Team team, TeamMember model, String garment, LocalDateTime date) {
        Fitting fitting = getManager(team).scheduleFitting(model, garment, date);
        fittings.add(fitting);
        System.out.println(fitting.toString());
        ModelingDepartment.fileManager.addFitting(fitting);
    }

    public static HOD parse(Map<String, String> hod) {
        return new HOD(
                Employee.parseEmployee(hod.get("employeeInfo")),
                ModelingDepartment.fileManager.getManagers()
        );
    }
}
