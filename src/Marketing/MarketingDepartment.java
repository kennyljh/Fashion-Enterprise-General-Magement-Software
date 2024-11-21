package src.Marketing;

import src.App;

import src.Marketing.src.*;
import src.Modeling.src.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MarketingDepartment {
    public static HOD hod;
    public static FileManager fileManager;

    public MarketingDepartment() {
        fileManager = new FileManager();
        hod = fileManager.getHOD();
    }

    public void start() throws Exception {
        Scanner s = new Scanner(System.in);
        int c = 0;

        while (c != 4) {
            System.out.println("\nPlease choose an action you want to take: \n" +
                    " 1: advertiseEvent \n" +
                    " 2: advertiseProduct\n" +
                    " 3: Admin Activities\n" +
                    " 4: Back");
            c = s.nextInt();
            switch (c) {
                case 1 -> {
                    ArrayList<Integer> eventIds = new ArrayList<>();
                    for(Event event: App.modelingDepartment.getEvents()) {
                        eventIds.add(event.getId());
                    }
                    System.out.println("Which Event: " + eventIds);
                    int event = s.nextInt();

                    AdvertType type = AdvertType.COMMERCIAL;
                    System.out.println("What type of Advertisement?\n" +
                            " 1: Billboard\n" +
                            " 2: Magazine\n" +
                            " 3: Social Media\n" +
                            " 4: Commercial\n");
                    int x = s.nextInt();
                    s.nextLine();
                    switch (x) {
                        case 1 -> {
                            type = AdvertType.BILLBOARD;
                        }
                        case 2 -> {
                            type = AdvertType.MAGAZINE;
                        }
                        case 3 -> {
                            type = AdvertType.SOCIALMEDIA;
                        }
                    }
                    EventAdvertisement e = hod.createEventAdvert(App.modelingDepartment.getEvent(event), type);
                    System.out.println(e.toString());
                    fileManager.addEventAdvert(e);
                }
                case 2 -> {

                }
                case 3 -> initiateAdmin();
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
                        src.Modeling.src.HOD hod = new src.Modeling.src.HOD();
                        System.out.println(hod);
                    }
                }
            }
        }
    }
}
