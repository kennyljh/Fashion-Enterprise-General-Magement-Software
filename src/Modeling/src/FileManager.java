package src.Modeling.src;

import src.TextEditor.PoorTextEditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileManager {
    private Map<String, Map<String, String>> events = new HashMap<>();
    private Map<String, Map<String, String>> teamMembers = new HashMap<>();
    private Map<String, Map<String, String>> fittings = new HashMap<>();
    private Map<Team, Map<String, Map<String, Map<String, String>>>> items = new HashMap<>();

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

        setItems();
    }
//    Storage:
    public StorageManager getStorageManager() {
        return StorageManager.parse(teamMembers.get("Storage Manager"));
    }
    public void setItems() {
        File folder = new File(repo + "warehouse/Clothing");
        String[] files = folder.list();
        if (files != null) {
            for(String fileName: files) {
                editor.processTextFile(folder.getPath()+"/"+fileName);
                items.computeIfAbsent(Team.CLOTHING, k -> new HashMap<>())
                        .put(fileName, editor.getRepositoryStringMap());
            }
        }

        folder = new File(repo + "warehouse/Makeup");
        files = folder.list();
        if (files != null) {
            for(String fileName: files) {
                editor.processTextFile(folder.getPath()+"/"+fileName);
                items.computeIfAbsent(Team.MAKEUP, k -> new HashMap<>())
                        .put(fileName, editor.getRepositoryStringMap());
            }
        }

        folder = new File(repo + "warehouse/Modeling");
        files = folder.list();
        if (files != null) {
            for(String fileName: files) {
                editor.processTextFile(folder.getPath()+"/"+fileName);
                items.computeIfAbsent(Team.MODELING, k -> new HashMap<>())
                        .put(fileName, editor.getRepositoryStringMap());
            }
        }
    }

    public Map<Team, Map<String, Map <String, Map<String, String>>>> getItems() {
        return items;
    }

    public boolean addItem(Item item) throws IOException {
        File f = new File(repo + "warehouse/" + item.getAssociatedTeam() + "/" + item.getItemType() + ".txt");
        System.out.println(f.getAbsolutePath());
        if(!f.exists()) {
            Scanner s = new Scanner(System.in);
            System.out.println("Type " + item.getItemType() + " is not found in the system.\n Would you like to create it?(y/n)");
            String str = s.nextLine();
            if(str.equals("y")) {
                if(f.createNewFile()) {
                    System.out.println("File created successfully: " + f.getAbsolutePath());
                } else {
                    System.out.println("Failed to create the file.");
                    return false;
                }
            } else {return false;}
        }

        items.computeIfAbsent(item.getAssociatedTeam(), k -> new HashMap<>())
                .computeIfAbsent(item.getItemType(), k -> new HashMap<>())
                .put("Item " + item.getId(), item.toMap());

        Map<String, Map<String, Map<String, String>>> teamCategories = items.get(item.getAssociatedTeam());
        if (teamCategories != null) {
            editor.setRepositoryStrings(teamCategories.get(item.getItemType())); // Returns the innermost map
            editor.writeToTextFile(f.getPath());
            return true;
        }
        return false;
    }

    public ArrayList<Item> getItems(Team team) {
        return null;
    }

    public Item getById(Team team, int id) {
        return null;
    }

    public Item updateItem(Team team, Item updatedItem) {
        return null;
    }

    public String[] getCategories(Team team) {
        File f = new File(repo + "warehouse/" + team.toString());
        String[] files = f.list();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                int dotIndex = files[i].lastIndexOf('.');
                if (dotIndex > 0) {
                    files[i] = files[i].substring(0, dotIndex);
                }
            }
        }
        return files;
    }

    public boolean deleteItem(Item item) throws IOException {
        File f = new File(repo + item.getAssociatedTeam() + "/" + item.getItemType() + ".txt");
        if (!f.exists()) {
            System.out.println("Type " + item.getItemType() + " is not found in the system.");
            return false;
        }

        Map<String, Map<String, Map<String, String>>> teamCategories = items.get(item.getAssociatedTeam());
        if (teamCategories != null) {
            Map<String, Map<String, String>> categoryItems = teamCategories.get(item.getItemType());
            if (categoryItems != null) {
                String itemKey = "Item " + item.getId();
                if (categoryItems.remove(itemKey) != null) {
                    System.out.println("Item removed successfully.");
                    // Clean up empty maps
                    if (categoryItems.isEmpty()) {
                        teamCategories.remove(item.getItemType());
                        if (teamCategories.isEmpty()) {
                            items.remove(item.getAssociatedTeam());
                        }
                    }
                    return true;
                } else {
                    System.out.println("Item not found in the system.");
                }
            }
        }
        return false;
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

    public Event getEvent(int id) {
        Event tmp = null;
        for(Map<String, String> event: events.values()) {
            if(event.get("id").equals(Integer.toString(id))){
                tmp = Event.parse(event);
                break;
            }
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
