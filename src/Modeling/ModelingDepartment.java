package src.Modeling;

import src.App;

import src.Modeling.src.*;
import src.Security.src.SecurityRequestScheduler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ModelingDepartment {
    static HOD hod;
    static StorageManager storageManager;
    public static FileManager fileManager;
    Scanner s = new Scanner(System.in);

    public ModelingDepartment() {
        fileManager = new FileManager();
        hod = fileManager.getHOD();
        storageManager = new StorageManager(Team.STORAGE);
    }

    public void start() throws Exception {
        int login = 0;
        while (login != 4) {
            System.out.println("""
                    
                    Which User are you Logging in as?
                     1: Head of Department
                     2: Storage Manager
                     3: Admin
                     4: Exit Modeling Department
                    """);
            login = s.nextInt();
            s.nextLine();
            switch (login) {
                case 1 -> initiateHOD();
                case 2 -> initiateStorageManager();
                case 3 -> initiateAdmin();
            }
        }
        App.prompt();
    }

    private void initiateAdmin() {
        int userChoice = 0;

        while (userChoice != 2) {
            System.out.println("""
                    Please choose an action you want to take:
                     1: manage HOD
                     2: Back""");
            userChoice = s.nextInt();
            if (userChoice == 1) {
                int response = 0;
                while (response != 2) {
                    System.out.println("""
                            Managing HOD:
                             1: add
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

    private void initiateHOD() throws Exception {
        int userChoice = 0;
        while (userChoice != 5) {
            System.out.println("""
                    
                    Please choose an action you want to take:
                     1: haveEvent
                     2: scheduleFitting
                     3: getAllEvents
                     4: getAllFittings
                     5: Back
                     111: Create Security Schedule
                     112: Show All Security Requests
                     113: Delete Security Request By ID
                    """);
            userChoice = s.nextInt();
            switch (userChoice) {
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

                case 111 -> {
                    SecurityRequestScheduler scheduler = new SecurityRequestScheduler();
                    scheduler.addSecurityRequest();
                }
                case 112 -> {
                    SecurityRequestScheduler scheduler1 = new SecurityRequestScheduler();
                    scheduler1.showAllSecurityRequests();
                }
                case 113 -> {
                    SecurityRequestScheduler scheduler2 = new SecurityRequestScheduler();
                    scheduler2.deleteScheduleByID();
                }
            }
        }
        App.prompt();
    }

    private void initiateStorageManager() throws IOException {
        int userChoice = 0;
        while (userChoice != 6) {
            System.out.println("""
                    
                    Please choose an action you want to take:
                     1: orderItem
                     2: updateItem
                     3: deleteItem
                     4: damageScan
                     5: returnItem
                     6: Back
                    """);
            userChoice = s.nextInt();
            s.nextLine();
            switch (userChoice) {
                case 1 -> {
                    Team team = null;
                    String type = "";
                    RecurrenceType recurrenceType = null;

                    int teamNumber = 10;
                    while (teamNumber > 3) {
                        System.out.println("""
                                
                                What team are you requesting the item for
                                 1: Modeling
                                 2: Makeup
                                 3: Clothing
                                """);
                        teamNumber = s.nextInt();
                        s.nextLine();
                        switch (teamNumber) {
                            case 1 -> {
                                System.out.println("Is Modeling correct? (y/n)");
                                String tmp = s.nextLine();
                                if (tmp.equals("y")) {
                                    team = Team.MODELING;
                                } else {
                                    teamNumber = 10;
                                }
                            }
                            case 2 -> {
                                System.out.println("Is Makeup correct? (y/n)");
                                String tmp = s.nextLine();
                                if (tmp.equals("y")) {
                                    team = Team.MAKEUP;
                                } else {
                                    teamNumber = 10;
                                }
                            }
                            case 3 -> {
                                System.out.println("Is Clothing correct? (y/n)");
                                String tmp = s.nextLine();
                                if (tmp.equals("y")) {
                                    team = Team.CLOTHING;
                                } else {
                                    teamNumber = 10;
                                }
                            }
                        }
                    }

                    boolean verified = false;
                    while (!verified) {
                        System.out.println("What item would you like to order?");
                        type = s.nextLine();
                        System.out.println("Is " + type + " correct?(y/n)");
                        String tmp = s.nextLine();
                        if (tmp.equals("y")) {
                            verified = true;
                        }
                    }

                    int recurrence = 10;
                    while (recurrence > 5) {
                        System.out.println("""
                                
                                Will you add a recurrence flag to this item?
                                 1: Daily
                                 2: Weekly
                                 3: Monthly
                                 4: Yearly
                                 5: None
                                """);
                        recurrence = s.nextInt();
                        s.nextLine();
                        switch (recurrence) {
                            case 1 -> recurrenceType = RecurrenceType.DAILY;
                            case 2 -> recurrenceType = RecurrenceType.WEEKLY;
                            case 3 -> recurrenceType = RecurrenceType.MONTHLY;
                            case 4 -> recurrenceType = RecurrenceType.YEARLY;
                            default -> recurrenceType = RecurrenceType.NONE;
                        }
                        System.out.println("Recurring " + recurrenceType.getRecurrence() + "? (y/n)");
                        String tmp = s.nextLine();
                        if (!tmp.equals("y")) {
                            recurrence = 10;
                        }
                    }

                    assert team != null;
                    Item newItem = new Item(team, type, recurrenceType);

                    storageManager.addItem(newItem);
                    System.out.println(newItem);
                }
                case 2 -> {

                }
                case 3 -> {

                }
                case 4 -> {

                }
                case 5 -> {

                }
            }
        }
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

    public Item requestItem(int id) {
        return null;
    }
}
