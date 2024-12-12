package src.Marketing;

import src.App;

import src.Marketing.src.*;
import src.Marketing.src.interfaces.ICollabMember;
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
                     3: Exit Marketing Department
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
                    
                    Please choose an action you want to take:
                     1: advertiseEven
                     2: advertiseDesign
                     3: getAllEventAdvertisements
                     4: getAllDesignAdvertisements
                     5: manageCollaborations
                     6: Back
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
                case 5 -> initiateManagement();

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

    private void initiateManagement() {
        int userChoice = 0;
        while (userChoice != 3) {
            System.out.println("""
                    
                    Please choose an action you want to take:
                     1: manageApprovalList
                     2: manageCollaborations
                     3: Back
                    """);
            userChoice = s.nextInt();
            s.nextLine();
            switch (userChoice) {
                case 1 -> {
                    int choice = 0;
                    while (choice != 5) {
                        System.out.println("""
                            
                            Please choose an action you want to take:
                             1: addApprovedMember
                             2: updateApprovedMember
                             3: deleteApprovedMember
                             4: printApprovedMembers
                             5: Back
                            """);
                        choice = s.nextInt();
                        s.nextLine();

                        switch (choice) {
                            case 1 -> {
                                System.out.println("""
                                        
                                        What kind of member would you like to approve?
                                         1: Celebrity
                                         2: Brand""");
                                int type = s.nextInt();
                                s.nextLine();

                                System.out.println("""
                                        
                                        What is the name of the Celebrity/Brand you will like to approve?""");
                                String name = s.nextLine();

                                if(!fileManager.checkExistence(name)) {
                                    ICollabMember member;

                                    if(type == 1) {
                                        member = new Celebrity(name);
                                    } else {
                                        member = new Brand(name);
                                    }
                                    
                                    System.out.println(member);
                                    System.out.println("\nAre you sure you want to approve this Celeb/Brand?(y/n)");

                                    if(s.nextLine().equalsIgnoreCase("y")) {
                                        System.out.println("\nSending Approval to System...");
                                        hod.addApprovedCollab(member);
                                    } else {
                                        System.out.println("\nCanceling approval...");
                                    }
                                } else {
                                    System.out.println("\nCelebrity/Brand already in approval list, aborting task...");
                                }
                            }
                            case 2 -> {
                                System.out.println("""
                                        
                                        Which Celebrity/Brand would you like to change?(full name)""");
                                hod.printApprovedMembers();
                                String name = s.nextLine();

                                ICollabMember member = hod.getMember(name);

                                System.out.println(member);
                                System.out.println("\nWhat is the new name of the celebrity");
                                String newName = s.nextLine();

                                if(!fileManager.checkExistence(newName)) {
                                    System.out.println("Is this correct: " + newName + "?(y/n)");
                                    if(s.nextLine().equalsIgnoreCase("y")) {
                                        System.out.println("Updating member...");
                                        member.changeName(newName);
                                        hod.updateMember(member);
                                        System.out.println(member);
                                    } else {
                                        System.out.println("Aborting change...");
                                    }
                                } else {
                                    System.out.println("Name already exists in the system, aborting change...");
                                }
                            }
                            case 3 -> {
                                System.out.println("\nWhich member would you like to remove?(full name)");
                                hod.printApprovedMembers();

                                String name = s.nextLine();

                                if(fileManager.checkExistence(name)) {
                                    ICollabMember member = hod.getMember(name);

                                    System.out.println(member);
                                    System.out.println("\nAre you sure you want to remove " + member.getName() + "?(y/n)");

                                    if(s.nextLine().equalsIgnoreCase("y")) {
                                        hod.removeMember(member);
                                    } else {
                                        System.out.println("\n Aborting removal...");
                                    }
                                }
                            }
                            case 4 -> fileManager.printApprovedMembers();
                        }
                    }
                }
                case 2 -> {
                    int choice = 0;
                    while (choice != 5) {
                        System.out.println("""
                                
                                Please choose an action you want to take:
                                 1: addCollaboration
                                 2: updateCollaboration
                                 3: deleteCollaboration
                                 4: printCollaborations
                                 5: Back
                                """);
                        choice = s.nextInt();
                        s.nextLine();

                        switch (choice) {
                            case 1 -> {

                            }
                            case 2 -> {

                            }
                            case 3 -> {

                            }
                            case 4 -> fileManager.printApprovedMembers();
                        }
                    }
                }
            }
        }
    }
}
