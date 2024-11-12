package src.Modeling.src;

import src.HR.src.Department;
import src.HR.src.Employee;
import src.Modeling.src.interfaces.IHOD;

import java.util.ArrayList;
import java.util.Scanner;

public class HOD implements IHOD {
    Employee employeeInfo;
    Manager[] managers;
    ArrayList<Event> events;

    public HOD() {
        this.employeeInfo = new Employee("1", "Head of Modeling", Department.MODELING, "HOD", "Employed", 10000);
        managers = new Manager[3];
        events = new ArrayList<>();
        managers[0] = new Manager("Clothing Manager", Team.CLOTHING);
        managers[1] = new Manager("Makeup Manager", Team.MAKEUP);
        managers[2] = new Manager("Modeling Manager", Team.MODELING);
    }


    @Override
    public void createEvent(Boolean type, String celebrity, Boolean collab) {
        TeamMember[] teamMembers = new TeamMember[managers[0].getTeamMembers().length + managers[1].getTeamMembers().length + managers[2].getTeamMembers().length];

        int i;
        for(i = 0; i < managers[0].getTeamMembers().length; i++) {
            teamMembers[i] = managers[0].getTeamMembers()[i]
        }
        for (int j = i;)


        Event event = new Event(events.size(),teamMembers, type, celebrity, collab);

        System.out.println(event.toString());
        System.out.println("\nAdd Event?");
        Scanner scanner = new Scanner(System.in);
        String x = scanner.next();
        if(x.equals("Y")) {
            addEvent(event);
        }
    }

    public void addEvent(Event event) {
        this.events.add(event);
        printEvents();
    }

    public void printEvents() {
        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i).toString());
        }
    }

    @Override
    public Boolean requestAdvertisement(Event event) {
        return false;
    }

    @Override
    public Boolean requestContract(String celebrity) {
        return null;
    }

    @Override
    public Boolean requestCollab(String brand) {
        return null;
    }
}
