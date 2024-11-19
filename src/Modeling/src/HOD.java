package src.Modeling.src;

import src.HR.src.Department;
import src.HR.src.Employee;
import src.Modeling.src.interfaces.IHOD;
import src.TextEditor.PoorTextEditor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class HOD implements IHOD {
    Employee employeeInfo;
    Manager[] managers;
    PoorTextEditor editor;

    public HOD() {
//        this.employeeInfo = new Employee("1", "Head of Modeling", Department.MODELING, "HOD", "Employed", 10000);
//        editor = new PoorTextEditor();
//        managers = new Manager[3];
//        managers[0] = new Manager("Clothing Manager", Team.CLOTHING);
//        managers[1] = new Manager("Makeup Manager", Team.MAKEUP);
//        managers[2] = new Manager("Modeling Manager", Team.MODELING);
    }

    @Override
    public Event createEvent(Boolean type, String celebrity, String collab) {
        TeamMember[] teamMembers = new TeamMember[managers[0].getTeamMembers().length + managers[1].getTeamMembers().length + managers[2].getTeamMembers().length];

        int index = 0;
        for(Manager manager: managers) {
            TeamMember[] team = manager.getTeamMembers();
            System.arraycopy(team, 0, teamMembers, index, team.length);
            index += team.length;
        }

        Event event = new Event(teamMembers, type, celebrity, collab);

        return event;
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

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Map<String, String> toMap() {
        return Map.of();
    }
}
