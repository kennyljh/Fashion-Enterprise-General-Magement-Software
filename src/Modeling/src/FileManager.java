package src.Modeling.src;

import src.TextEditor.PoorTextEditor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private Map<String, Map<String, String>> events = new HashMap<>();
    private Map<String, Map<String, String>> teamMembers = new HashMap<>();
    private Map<String, Map<String, String>> fittings = new HashMap<>();

    private final PoorTextEditor editor = new PoorTextEditor();

    private final String repo = "src/Modeling/repository/";

    public FileManager() {
        fillRepos();
    }

    public void fillRepos() {
        File f = new File(repo + "events.txt");
        if(f.exists()) {
            editor.processTextFile(repo + "events.txt");
            events = editor.getRepositoryStringMap();
        }

        f = new File(repo + "fittings.txt");
        if(f.exists()) {
            editor.processTextFile(repo + "fittings.txt");
            fittings = editor.getRepositoryStringMap();
        }

        f = new File(repo + "department.txt");
        if(f.exists()) {
            editor.processTextFile(repo + "department.txt");
            teamMembers = editor.getRepositoryStringMap();
        }
    }

//    Events:
    public void addEvent(Event event) {
        events.put("Event " + event.getId(), event.toMap());

        editor.setRepositoryStrings(events);
        editor.writeToTextFile(repo + "events.txt");
    }

    public ArrayList<Event> getEvents() {
        ArrayList<Event> tmp = new ArrayList<>();
        for(Map<String, String> event: events.values()) {
            tmp.add(Event.parse(event));
        }
        return tmp;
    }

    public ArrayList<Fitting> getFittings() {
        ArrayList<Fitting> tmp = new ArrayList<>();
        for(Map<String, String> event: fittings.values()) {
            tmp.add(Fitting.parse(event));
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

    public void addFitting(Fitting fitting) {
        fittings.put("Fitting " + fitting.getId(), fitting.toMap());

        editor.setRepositoryStrings(fittings);
        editor.writeToTextFile(repo + "fittings.txt");
    }

    public TeamMember getModel(int model) {
        return TeamMember.parse(teamMembers.get("Team Member "+ model));
    }
}
