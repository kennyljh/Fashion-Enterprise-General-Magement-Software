package src.Modeling.src;

import src.TextEditor.PoorTextEditor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileManager {
    private Map<String, Map<String, String>> events = new HashMap<>();
    private Map<String, Map<String, String>> teamMembers = new HashMap<>();
    private Map<String, Map<String, String>> fittings = new HashMap<>();

    private PoorTextEditor editor = new PoorTextEditor();

    private String repo = "src/Modeling/repository/";

    public FileManager() {
        fillRepos();
    }

    public void fillRepos() {
        File f = new File(repo + "events.txt");
        if(f.exists()) {
            editor.processTextFile(repo + "events.txt");
            events = editor.getRepositoryString();
        }

        f = new File(repo + "fittings.txt");
        if(f.exists()) {
            editor.processTextFile(repo + "fittings.txt");
            fittings = editor.getRepositoryString();
        }

        f = new File(repo + "teamMembers.txt");
        if(f.exists()) {
            editor.processTextFile(repo + "teamMembers.txt");
            teamMembers = editor.getRepositoryString();
        }
    }

    public void addEvent(Event event) {
        events.put("Event " + event.getId(), event.eventToMap());

        editor.setRepositoryStrings(events);
        editor.writeToTextFile(repo + "events.txt");
    }

    public void addTeamMember(TeamMember teamMember) {
        teamMembers.put("Team Member "+ teamMember.getId(), teamMember.toMap());

        editor.setRepositoryStrings(teamMembers);
        editor.writeToTextFile(repo + "teamMembers.txt");
    }

    public void addFitting(Fitting fitting) {
//        fittings.put("Fitting " + fitting.getId(), event.eventToMap());
//
//        editor.setRepositoryStrings(events);
//        editor.writeToTextFile(repo + "events.txt");
    }
}
