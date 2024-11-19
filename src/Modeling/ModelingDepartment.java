package src.Modeling;

import src.App;

import src.Modeling.src.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ModelingDepartment {
    static HOD hod;
    public static FileManager fileManager;

    public ModelingDepartment() {
        fileManager = new FileManager();
        hod = fileManager.getHOD();
    }

    public void start() throws Exception {
        Scanner s = new Scanner(System.in);
        int c = 0;

        while (c != 4) {
            System.out.println("\nPlease choose an action you want to take: \n 1: haveEvent \n 2: scheduleFitting\n 3: Admin Activities\n 4: Back");
            c = s.nextInt();
            switch (c) {
                case 1 -> {
                    boolean type = false;
                    System.out.println("What type of event?\n1:Photoshoot\n2:Fashion Show");
                    int x = s.nextInt();
                    s.nextLine();
                    if (x == 1) {
                        type = true;
                    }
                    System.out.println("Type a name of a Celebrity (enter for none)");
                    String celebrity = s.nextLine();
                    System.out.println("What brand will you collab with? (enter for none)");
                    String ch = s.nextLine();
                    hod.createEvent(type, celebrity, ch);
                }
                case 2 -> {
                    System.out.println("What model? (0-3)");
                    int model = s.nextInt();
                    s.nextLine();
                    System.out.println("What kind of garment?");
                    String garment = s.nextLine();
                    System.out.println("What month (1-12)");
                    int month = s.nextInt();
                    System.out.println("What day (1-31)");
                    int day = s.nextInt();
                    System.out.println("What hour? (1-24)");
                    int hour = s.nextInt();
                    LocalDateTime date = LocalDateTime.of(2024, month, day, hour, 0);
                    hod.requestFitting(Team.MODELING, ModelingDepartment.fileManager.getModel(model), garment, date);
                    start();
                }
                case 3 -> initiateAdmin();
            }
            App.prompt();
        }
    }

    private void initiateAdmin() {
        Scanner s = new Scanner(System.in);
        int c = 0;

        while (c != 2) {
            System.out.println("""
                    
                    Please choose an action you want to take: \
                    
                     1: manage HOD\
                    
                     2: Back""");
            c = s.nextInt();
            if (c == 1) {
                int response = 0;
                while (response != 2) {
                    System.out.println("""
                            
                            Managing HOD: \
                            
                             1: add\
                            
                             2: Back""");
                    response = s.nextInt();
                    if (response == 1) {
                        HOD hod = new HOD();
                        System.out.println(hod);
                    }
                }
            }
        }
    }

    public ArrayList<TeamMember> getModels() {
        return fileManager.getTeamMembers(Team.MODELING);
    }
}
