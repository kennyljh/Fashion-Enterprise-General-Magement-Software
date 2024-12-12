package src.Marketing.src;

import src.App;
import src.HR.src.Department;
import src.HR.src.Employee;
import src.Marketing.*;
import src.Marketing.src.interfaces.*;
import src.Modeling.src.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HOD implements IHOD {

    private final Employee employeeInfo;
    private final ArrayList<Manager> managers;

    public ArrayList<EventAdvertisement> eventAdverts;
    public ArrayList<DesignAdvertisement> designAdverts;

    public ArrayList<ICollabMember> approvedCollabMembers;
    public ArrayList<ICollab> collabs;


    public HOD(Employee employeeInfo, ArrayList<Manager> managers) {
        this.employeeInfo = employeeInfo;
        this.managers = managers;

        eventAdverts = MarketingDepartment.fileManager.getEventAdverts();
        designAdverts = MarketingDepartment.fileManager.getDesignAdverts();

        approvedCollabMembers = MarketingDepartment.fileManager.getApprovedCollabMembers();
    }

    public HOD() {
        employeeInfo = new Employee("hod", "Head of Marketing", Department.MARKETING, "HOD", "Employeed", 100000);
        managers = new ArrayList<>();
        managers.add(new Manager(Team.EDITING));
        managers.add(new Manager(Team.SOCIALMEDIA));
        managers.add(new Manager(Team.VIDEO));
        managers.add(new Manager(Team.DISTRIBUTION));

        MarketingDepartment.fileManager.addHOD(this);
    }

//    Get methods
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Employee getEmployeeInfo() {
        return employeeInfo;
    }

    @Override
    public ArrayList<EventAdvertisement> getEventAdverts() {return eventAdverts;}

    @Override
    public ArrayList<DesignAdvertisement> getDesignAdverts() {return designAdverts;}

    @Override
    public ArrayList<ICollabMember> getApprovedCollabMembers() {return approvedCollabMembers;}

//    Creation
    @Override
    public EventAdvertisement createEventAdvert(Event event, AdvertType type) {
        EventAdvertisement ad = new EventAdvertisement(event, type);
        eventAdverts.add(ad);
        return ad;
    }

    @Override
    public DesignAdvertisement createDesignAdvert(AdvertType type, String notes) {
        DesignAdvertisement ad = new DesignAdvertisement(type, notes);
        designAdverts.add(ad);
        return ad;
    }

    @Override
    public ICollabMember addApprovedCollab(ICollabMember member) {
        approvedCollabMembers.add(member);
        MarketingDepartment.fileManager.addCollabMember(member);
        return member;
    }

    @Override
    public void updateMember(ICollabMember member) {
        for (ICollabMember m: approvedCollabMembers) {
            if(member instanceof Celebrity && m instanceof Celebrity) {
                m.changeName(member.getName());
                MarketingDepartment.fileManager.updateCollabMember(member);
                return;
            }
        }
    }

    @Override
    public void printCelebrities() {
        for(ICollabMember member: approvedCollabMembers) {
            if(member instanceof Celebrity) {
                System.out.println(member.getName());
            }
        }
    }

    @Override
    public void printBrand() {
        for(ICollabMember member: approvedCollabMembers) {
            if(member instanceof Brand) {
                System.out.println(member.getName());
            }
        }
    }

    @Override
    public void printApprovedMembers() {
        for(ICollabMember member: approvedCollabMembers) {
            System.out.println(member.getName());
        }
    }

    public ICollabMember getMember(String name) {
        ArrayList<ICollabMember> tmp = new ArrayList<>();
        for (ICollabMember member: approvedCollabMembers) {
            if(member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public ICollab addCollab(ICollab collab) {
        collabs.add(collab);
        return collab;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> memberDetails = new HashMap<>();
        memberDetails.put("employeeInfo", this.employeeInfo.toString());
        Integer[] tmp = new Integer[managers.size()];
        for(int i = 0; i < managers.size(); i++) {
            tmp[i] = managers.get(i).getId();
        }
        memberDetails.put("managers", Arrays.toString(tmp));
        return memberDetails;
    }

    @Override
    public void addManager(Manager manager) {
        managers.add(manager);
        MarketingDepartment.fileManager.addManager(manager);
    }

    @Override
    public Manager getManager(Team team) {
        for (Manager manager: managers) {
            if (manager.getTeam() == team) {
                return manager;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("\nHOD: ");
        str.append("\nEmployeeInfo: ").append(this.employeeInfo.toString());
        str.append("\nManagers: ");
        for (Manager manager : managers) {
            str.append("\n  ").append(manager.toString());
        }
        return str.toString();
    }

    @Override
    public Event requestPhotoshoot(int modelId) {
        return App.modelingDepartment.requestPhotoshoot(Integer.toString(modelId), "");
    }

    public static HOD parse(Map<String, String> hod) {
        return new HOD(
                Employee.parseEmployee(hod.get("employeeInfo")),
                MarketingDepartment.fileManager.getManagers()
        );
    }
}