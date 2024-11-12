package src.Modeling.src;

import src.HR.src.Employee;
import src.Modeling.src.interfaces.IManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Manager implements IManager {

    Employee employeeInfo;
    TeamMember[] teamMembers;

    public Manager(String name, Team team) {
        teamMembers = new TeamMember[4];
        for (int i = 0; i < 4; i++) {
            teamMembers[i] = new TeamMember(i, team.toString() + " Member", team);
        }
    }

    @Override
    public Fitting requestFitting(Employee model, LocalDateTime date) {
        Fitting fitting = new Fitting(1, model, "Dress", date);
        System.out.println(fitting.toString());
        System.out.println("\nEnd Fitting?");
        Scanner scanner = new Scanner(System.in);
        String x = scanner.next();
        if(x.equals("Y")) {
            fitting.endFitting();
        }
        return fitting;
    }

    @Override
    public Boolean scheduleModel(Employee Model) {
        return null;
    }

    @Override
    public TeamMember[] getTeamMembers() {
        return teamMembers;
    }
}