package src.Marketing.src;

import src.Modeling.src.Event;
import src.TextEditor.PoorTextEditor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private Map<String, Map<String, String>> teamMembers = new HashMap<>();
    private Map<String, Map<String, String>> eventAdverts = new HashMap<>();
    private Map<String, Map<String, String>> designAdverts = new HashMap<>();

    private final PoorTextEditor editor = new PoorTextEditor();

    private final String repo = "src/Marketing/repository/";

    public FileManager() {
        fillRepos();
    }

    public void fillRepos() {
        File f = new File(repo + "department.txt");
        if(f.exists()) {
            editor.processTextFile(repo + "department.txt");
            teamMembers = editor.getRepositoryStringMap();
        }

        f = new File(repo + "eventAdvert.txt");
        if(f.exists()) {
            editor.processTextFile(repo + "eventAdvert.txt");
            eventAdverts = editor.getRepositoryStringMap();
        }

        f = new File(repo + "designAdvert.txt");
        if(f.exists()) {
            editor.processTextFile(repo + "designAdvert.txt");
            designAdverts = editor.getRepositoryStringMap();
        }
    }

    //    EventsAdverts:
    public void addEventAdvert(EventAdvertisement advert) {
        eventAdverts.put("Advert " + advert.getId(), advert.toMap());

        editor.setRepositoryStrings(eventAdverts);
        editor.writeToTextFile(repo + "eventAdvert.txt");
    }

    public ArrayList<EventAdvertisement> getEventAdverts() {
        ArrayList<EventAdvertisement> tmp = new ArrayList<>();
        for(Map<String, String> advert: eventAdverts.values()) {
            tmp.add(EventAdvertisement.parse(advert));
        }
        return tmp;
    }



    //    EventsAdverts:
    public void addDesignAdvert(DesignAdvertisement advert) {
        designAdverts.put("Advert " + advert.getId(), advert.toMap());

        editor.setRepositoryStrings(designAdverts);
        editor.writeToTextFile(repo + "designAdvert.txt");
    }

    public ArrayList<DesignAdvertisement> getDesignAdverts() {
        ArrayList<DesignAdvertisement> tmp = new ArrayList<>();
        for(Map<String, String> advert: designAdverts.values()) {
            tmp.add(DesignAdvertisement.parse(advert));
        }
        return tmp;
    }

    //    TeamMembers:
    public void addTeamMember(TeamMember teamMember) {
        teamMembers.put("Team Member "+ teamMember.getId(), teamMember.toMap());

        editor.setRepositoryStrings(teamMembers);
        editor.writeToTextFile(repo + "department.txt");
    }

    public void addManager(Manager manager) {
        teamMembers.put(manager.getTeam().toString() + " Manager", manager.toMap());

        editor.setRepositoryStrings(teamMembers);
        editor.writeToTextFile(repo + "department.txt");
    }

    public void addHOD(HOD hod) {
        teamMembers.put("HOD", hod.toMap());

        editor.setRepositoryStrings(teamMembers);
        editor.writeToTextFile(repo + "department.txt");
    }

    public ArrayList<Manager> getManagers() {
        ArrayList<Manager> tmp = new ArrayList<>();
        for(Map<String, String> member: teamMembers.values()) {
            if(member.containsKey("teamMembers")) {
                tmp.add(Manager.parse(member));
            }
        }
        return tmp;
    }

    public ArrayList<TeamMember> getTeamMembers(Team team) {
        ArrayList<TeamMember> tmp = new ArrayList<>();
        for(Map<String, String> member: teamMembers.values()) {
            if (member.containsValue(team.toString()) && !member.containsKey("teamMembers") && !member.containsKey("managers")) {
                tmp.add(TeamMember.parse(member));
            }
        }
        return tmp;
    }

    public ArrayList<TeamMember> getTeamMembers() {
        ArrayList<TeamMember> tmp = new ArrayList<>();
        for(Map<String, String> member: teamMembers.values()) {
            if (!member.containsKey("teamMembers") && !member.containsKey("managers")) {
                tmp.add(TeamMember.parse(member));
            }
        }
        return tmp;
    }

    public HOD getHOD() {
        return HOD.parse(teamMembers.get("HOD"));
    }
}
