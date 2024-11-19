package src.Modeling.src;

import src.HR.src.Department;
import src.HR.src.Employee;
import src.Modeling.ModelingDepartment;
import src.Modeling.src.interfaces.IHOD;

import java.util.*;

public class HOD implements IHOD {

    private final Employee employeeInfo;
    private final ArrayList<Manager> managers;

    public ArrayList<Event> events;

    public HOD(Employee employeeInfo, ArrayList<Manager> managers) {
        this.employeeInfo = employeeInfo;
        this.managers = managers;

        events = ModelingDepartment.fileManager.getEvents();
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
    public Event createEvent(Boolean type, String celebrity, String collab) {
        ArrayList<TeamMember> teamMembers = new ArrayList<>();

        for(Manager manager: managers) {
            ArrayList<TeamMember> team = manager.getTeamMembers();
            team.addAll(manager.getTeamMembers());
        }

        Event event = new Event(teamMembers, type, celebrity, collab);

        events.add(event);
        System.out.println(event.toString());
        ModelingDepartment.fileManager.addEvent(event);
        return event;
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

    public String toString() {
        String str = "\nHOD: ";
        str += "\nEmployeeInfo: " + this.employeeInfo.toString();
        str += "\nManagers: ";
        for (int i = 0; i < managers.size(); i++) {
            str += "\n  " + managers.get(i).toString();
        }
        return str;
    }

    public static HOD parse(Map<String, String> hod) {
        return new HOD(
                Employee.parseEmployee(hod.get("employeeInfo")),
                ModelingDepartment.fileManager.getManagers()
        );
    }
//
//    @Override
//    public Boolean addEvent(Event event) {
//        editor.processTextFile("/repository/events.txt");
//        if (editor.getRepository().isEmpty()) {
//            return false;
//        }
//
////        String[] temp = editor.getArrayNames();
////        for(String s: temp) {
////            Event event = new Event(
////                    editor.retrieveValue()
////            )
////        }
//        return null;
//    }
//
//    @Override
//    public void printEvent(Event event) {
//
//    }
//
//    @Override
//    public void printAllEvents() {
//
//    }
//
//    @Override
//    public void assignTask(Manager manager) {
//
//    }
//
////    public void printEvents() {
////        for (int i = 0; i < events.size(); i++) {
////            System.out.println(events.get(i).toString());
////        }
////    }
//
//    @Override
//    public Boolean requestAdvertisement(Event event) {
//        return false;
//    }
//
//    @Override
//    public Boolean requestContract(String celebrity) {
//        return null;
//    }
//
//    @Override
//    public Boolean requestCollab(String brand) {
//        return null;
//    }

}
