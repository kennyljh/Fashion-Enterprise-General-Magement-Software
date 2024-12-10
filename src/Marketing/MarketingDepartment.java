package src.Marketing;

import src.App;

import src.Marketing.src.*;
import src.Modeling.src.Event;
import src.Security.src.SecurityRequestScheduler;

import java.util.ArrayList;
import java.util.Scanner;

public class MarketingDepartment {
    public static HOD hod;
    public static FileManager fileManager;
    Scanner s = new Scanner(System.in);

    public MarketingDepartment() {
        fileManager = new FileManager();
        hod = fileManager.getHOD();
    }

    public void start() throws Exception {
        int login = 0;
        while (login != 3) {
            System.out.println("""
                    
                    Which User are you Logging in as?
                     1: Head of Department
                     2: Admin
                     3: Exit Modeling Department
                    """);
            login = s.nextInt();
            s.nextLine();
            switch (login) {
                case 1 -> initiateHOD();
                case 2 -> initiateAdmin();
            }
        }
        App.prompt();
    }

    private void initiateHOD() {
        int c = 0;

        while (c != 6) {
            System.out.println("""
                    
                    Please choose an action you want to take:\s
                     1: advertiseEvent\s
                     2: advertiseDesign
                     3: getAllEventAdvertisements
                     4: getAllDesignAdvertisements
                     5: Back
                     111: Create Security Schedule
                     112: Show All Security Requests
                     113: Delete Security Request By ID
                    """);
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
                    System.out.println("""
                            What type of Advertisement?
                             1: Billboard
                             2: Magazine
                             3: Social Media
                             4: Commercial""");
                    int x = s.nextInt();
                    switch (x) {
                        case 1 -> type = AdvertType.BILLBOARD;
                        case 2 -> type = AdvertType.MAGAZINE;
                        case 3 -> type = AdvertType.SOCIALMEDIA;
                    }
                    EventAdvertisement e = hod.createEventAdvert(App.modelingDepartment.getEvent(event), type);
                    System.out.println(e.toString());
                    fileManager.addEventAdvert(e);
                }
                case 2 -> {
                    AdvertType type = AdvertType.COMMERCIAL;
                    System.out.println("""
                            What type of Advertisement?
                             1: Billboard
                             2: Magazine
                             3: Social Media
                             4: Commercial""");
                    int x = s.nextInt();
                    switch (x) {
                        case 1 -> type = AdvertType.BILLBOARD;
                        case 2 -> type = AdvertType.MAGAZINE;
                        case 3 -> type = AdvertType.SOCIALMEDIA;
                    }
                    s.nextLine();
                    System.out.println("Any Special Notes? You will be advertising a ball gown");
                    String str = s.nextLine();
                    DesignAdvertisement e = hod.createDesignAdvert(type, str);
                    System.out.println(e.toString());
                    fileManager.addDesignAdvert(e);
                }
                case 3 -> {
                    for(EventAdvertisement ad: hod.getEventAdverts()) {
                        System.out.println(ad.toString());
                    }
                    System.out.println();
                }
                case 4 -> {
                    for(DesignAdvertisement ad: hod.getDesignAdverts()) {
                        System.out.println(ad.toString());
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
    }

    private void initiateAdmin() {
        Scanner s = new Scanner(System.in);
        int c = 0;

        while (c != 2) {
            System.out.println("""
                    Please choose an action you want to take:\s
                     1: manage HOD
                     2: Back""");
            c = s.nextInt();
            if (c == 1) {
                int response = 0;
                while (response != 2) {
                    System.out.println("""
                            Managing HOD:\s
                             1: add
                             2: Back""");
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
