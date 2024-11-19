package src.Modeling;

import src.App;

import src.HR.src.Department;
import src.HR.src.Employee;
import src.Modeling.src.*;

import java.time.LocalDateTime;
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
            switch (c){
                case 1:
                    Boolean type = false;
                    System.out.println("What type of event?\n1:Photoshoot\n2:Fashion Show");
                    int x = s.nextInt();
                    s.nextLine();
                    switch (x) {
                        case 1:
                            type = true;
                    }
                    System.out.println("Type a name of a Celebrity (enter for none)");
                    String celebrity = s.nextLine();
                    System.out.println("What brand will you collab with? (enter for none)");
                    String ch = s.nextLine();
                    hod.createEvent(type, celebrity, ch);
                    break;
                case 2:
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
                    break;
                case 3:
                   initiateAdmin();
                   break;
            }
            App.prompt();
        }
    }

    private void initiateAdmin() {
        Scanner s = new Scanner(System.in);
        int c = 0;

        while (c != 2) {
            System.out.println("\nPlease choose an action you want to take: " +
                    "\n 1: manage HOD" +
                    "\n 2: Back");
            c = s.nextInt();
            if (c == 1) {
                int response = 0;
                while (response != 2) {
                    System.out.println("\nManaging HOD: " +
                            "\n 1: add" +
                            "\n 2: Back");
                    response = s.nextInt();
                    if (response == 1) {
                        HOD hod = new HOD();
                        System.out.println(hod.toString());
                    }
                }
            }
        }
    }

//    main method made for testing, must be commented out in final program
//    public static void main(String[] args) {
//        fileManager = new FileManager();
//        Event event1 = hod.createEvent(true, "Beyonce", "Nike");
//        Event event2 = hod.createEvent(true, "Beyonce", "Nike");
////        System.out.println(event1.toString());
//        fileManager.addEvent(event1);
//        fileManager.addEvent(event2);
//    }

//    public TeamMember getAllModels() {
//
//    }
}
