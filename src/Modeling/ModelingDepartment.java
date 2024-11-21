package src.Modeling;

import src.App;

import src.Marketing.src.DesignAdvertisement;
import src.Marketing.src.EventAdvertisement;
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

        while (c != 6) {
            System.out.println("\nPlease choose an action you want to take: \n" +
                    " 1: haveEvent \n" +
                    " 2: scheduleFitting\n" +
                    " 3: getAllEvents\n" +
                    " 4: getAllFittings\n" +
                    " 5: Admin Activities\n" +
                    " 6: Back");
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
                    this.start();
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
                    this.start();
                }
                case 3 -> {
                    for(Event e: hod.getEvents()) {
                        System.out.println(e.toString());
                    }
                    System.out.println();
                }
                case 4 -> {
                    for(Fitting f: hod.getFittings()) {
                        System.out.println(f.toString());
                    }
                    System.out.println();
                }
                case 5 -> initiateAdmin();
            }
        }
        App.prompt();
    }

    private void initiateAdmin() {
        Scanner s = new Scanner(System.in);
        int c = 0;

        while (c != 2) {
            System.out.println("Please choose an action you want to take: \n" +
                    " 1: manage HOD\n" +
                    " 2: Back");
            c = s.nextInt();
            if (c == 1) {
                int response = 0;
                while (response != 2) {
                    System.out.println("Managing HOD: \n" +
                            " 1: add\n" +
                            " 2: Back");
                    response = s.nextInt();
                    if (response == 1) {
                        HOD hod = new HOD();
                        System.out.println(hod);
                    }
                }
            }
        }
    }

    public void logEvent(Event event) {

    }

    public ArrayList<TeamMember> getModels() {
        return fileManager.getTeamMembers(Team.MODELING);
    }

    public Event getEvent(int id) {
        return fileManager.getEvent(id);
    }

    public Event requestPhotoshoot(String model, String brand) {
        Event event;
        try {
            int id = Integer.parseInt(model);
            fileManager.getModel(id);
            event = hod.createEvent(true, id, brand);
        } catch (NumberFormatException e) {
            event = hod.createEvent(true, model, brand);
        }
        return event;
    }

    public ArrayList<Event> getEvents() {
        return hod.getEvents();
    }
}
