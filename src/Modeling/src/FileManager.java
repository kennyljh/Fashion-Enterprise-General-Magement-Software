package src.Modeling.src;

import src.TextEditor.PoorTextEditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

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

    public void addItem(Item item) throws IOException {
        File f = new File(repo + "warehouse/" + item.getAssociatedTeam() + "/" + item.getItemType() + ".txt");
        if(!f.exists()) {
            Scanner s = new Scanner(System.in);
            System.out.println("Type " + item.getItemType() + " is not found in the system.\n Would you like to create it?(y/n)");
            String str = s.nextLine();
            if(str.equals("y")) {
                if(f.createNewFile()) {
                    System.out.println("File created successfully: " + f.getAbsolutePath());
                } else {
                    System.out.println("Failed to create the file.");
                    return;
                }
            } else {return;}
        }

        items.computeIfAbsent(item.getAssociatedTeam(), k -> new HashMap<>())
                .computeIfAbsent(item.getItemType(), k -> new HashMap<>())
                .put("Item " + item.getId(), item.toMap());

        Map<String, Map<String, Map<String, String>>> teamCategories = items.get(item.getAssociatedTeam());
        if (teamCategories != null) {
            editor.setRepositoryStrings(teamCategories.get(item.getItemType())); // Returns the innermost map
            editor.writeToTextFile(f.getPath());
        }
    }

    public ArrayList<Item> getItems(Team team) {
        return null;
    }

    public List<Integer> getAllItemIdsForTeam(Team team) {
        List<Integer> itemIds = new ArrayList<>();

        // Retrieve the category map for the team
        Map<String, Map<String, Map<String, String>>> categoryMap = items.get(team);

        if (categoryMap != null) {
            // Iterate through each itemType (category) for the team
            for (Map.Entry<String, Map<String, Map<String, String>>> categoryEntry : categoryMap.entrySet()) {
                String itemType = categoryEntry.getKey();
                Map<String, Map<String, String>> itemMap = categoryEntry.getValue();

                // Iterate through each item for the current itemType
                for (Map.Entry<String, Map<String, String>> itemEntry : itemMap.entrySet()) {
                    Map<String, String> itemDetails = itemEntry.getValue();

                    // Extract the item id from the details and add it to the list
                    if (itemDetails != null && itemDetails.containsKey("id")) {
                        int itemId = Integer.parseInt(itemDetails.get("id"));
                        itemIds.add(itemId);
                    }
                }
            }
        }

        return itemIds; // Return the list of item ids
    }


    public Item getItemById(Team team, String itemType, int itemId) {
        Map<String, Map<String, Map<String, String>>> categoryMap = items.get(team);

        if (categoryMap != null) {
            Map<String, Map<String, String>> itemMap = categoryMap.get(itemType);

            if (itemMap != null) {
                for (Map.Entry<String, Map<String, String>> itemEntry : itemMap.entrySet()) {
                    Map<String, String> itemDetails = itemEntry.getValue();
                    if (itemDetails != null && Integer.parseInt(itemDetails.get("id")) == itemId) {
                        return Item.parse(itemDetails);
                    }
                }
            }
        }
        return null;
    }

    public Item getItemById(Team team, int itemId) {
        Map<String, Map<String, Map<String, String>>> categoryMap = items.get(team);

        if (categoryMap != null) {
            for (Map.Entry<String, Map<String, Map<String, String>>> itemTypeEntry : categoryMap.entrySet()) {
                Map<String, Map<String, String>> itemMap = itemTypeEntry.getValue();

                for (Map.Entry<String, Map<String, String>> itemEntry : itemMap.entrySet()) {
                    Map<String, String> itemDetails = itemEntry.getValue();
                    if (itemDetails != null && Integer.parseInt(itemDetails.get("id")) == itemId) {
                        return Item.parse(itemDetails);
                    }
                }
            }
        }

        // Return null if no matching item was found
        return null;
    }

    public boolean updateItem(Item item, Team oldTeam) throws IOException {
        // Get the new team from the item (this is already set in the item)
        Team newTeam = item.getAssociatedTeam();

        // Delete the item from the old team
        boolean deleted = deleteById(oldTeam, item.getId());
        if (!deleted) {
            System.out.println("Failed to delete item from old team.");
            return false;
        }

        // Define the old and new file paths
        String oldFilePath = repo + "warehouse/" + oldTeam + "/" + item.getItemType() + ".txt";
        String newFilePath = repo + "warehouse/" + newTeam + "/" + item.getItemType() + ".txt";

        // Ensure the new team and file exist, if not, create them
        File newFile = new File(newFilePath);
        if (!newFile.exists()) {
            File newDirectory = new File(repo + "warehouse/" + newTeam);
            if (!newDirectory.exists()) {
                newDirectory.mkdirs();  // Create the directory for the new team if it doesn't exist
            }

            // Create the new file for the new team and item type
            newFile.createNewFile();
            System.out.println("File created: " + newFilePath);
        }

        // Update the items map with the new team
        Map<String, Map<String, Map<String, String>>> newTeamCategories = items.computeIfAbsent(newTeam, k -> new HashMap<>());
        Map<String, Map<String, String>> itemTypeMap = newTeamCategories.computeIfAbsent(item.getItemType(), k -> new HashMap<>());

        // Add the item to the new team
        itemTypeMap.put("Item " + item.getId(), item.toMap());

        // Update the item's associated team in the item object to the new team
        item.associatedTeam = newTeam;

        // Write the updated map to the new file
        editor.setRepositoryStrings(newTeamCategories.get(item.getItemType())); // Return the innermost map
        editor.writeToTextFile(newFile.getPath());

        System.out.println("Item updated successfully.");
        return true;
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
        File f = new File(repo + "warehouse/" + item.getAssociatedTeam() + "/" + item.getItemType() + ".txt");
        if (!f.exists()) {
            System.out.println("File for " + item.getItemType() + " does not exist.");
            return false;
        }

        Map<String, Map<String, Map<String, String>>> teamCategories = items.get(item.getAssociatedTeam());
        if (teamCategories != null) {
            Map<String, Map<String, String>> categoryMap = teamCategories.get(item.getItemType());
            if (categoryMap != null) {
                categoryMap.remove("Item " + item.getId());

                editor.setRepositoryStrings(categoryMap);
                editor.writeToTextFile(f.getPath());
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(Team team, int itemId) {
        Map<String, Map<String, Map<String, String>>> teamCategories = items.get(team);

        if (teamCategories == null) {
            System.out.println("Team not found.");
            return false;
        }

        for (Map.Entry<String, Map<String, Map<String, String>>> itemTypeEntry : teamCategories.entrySet()) {
            Map<String, Map<String, String>> itemMap = itemTypeEntry.getValue();

            for (Map.Entry<String, Map<String, String>> itemEntry : itemMap.entrySet()) {
                Map<String, String> itemDetails = itemEntry.getValue();  // This is the Map<String, String> from the entry

                if (itemDetails.containsKey("id") && itemDetails.get("id").equals(String.valueOf(itemId))) {
                    itemMap.remove(itemEntry.getKey());
                    if (itemMap.isEmpty()) {
                        File f = new File(repo + "warehouse/" + team.toString() + "/" + itemDetails.get("itemType") + ".txt");
                        if (f.exists() && f.isFile()) {
                            if (f.delete()) {
                                System.out.println("File deleted successfully: " + f.getAbsolutePath());
                            } else {
                                System.out.println("Failed to delete file: " + f.getAbsolutePath());
                                break;
                            }
                        }
                    } else {
                        editor.setRepositoryStrings(itemMap);
                        editor.writeToTextFile(repo + "warehouse/"+itemDetails.get("associatedTeam")+"/"+itemDetails.get("itemType")+".txt");
                    }
                    System.out.println("Item " + itemId + " deleted successfully.");
                    return true;
                }
            }
        }


        System.out.println("Item with ID " + itemId + " not found.");
        return false;
    }




    public void printItems() {
        // Iterate through each team in the items map
        for (Map.Entry<Team, Map<String, Map<String, Map<String, String>>>> teamEntry : items.entrySet()) {
            Team team = teamEntry.getKey();
            Map<String, Map<String, Map<String, String>>> categoryMap = teamEntry.getValue();

            System.out.println("Team: " + team);

            // Iterate through each category (itemType) for the current team
            for (Map.Entry<String, Map<String, Map<String, String>>> categoryEntry : categoryMap.entrySet()) {
                String itemType = categoryEntry.getKey();

                // Skip the ".txt" file suffix if it's there
                if (itemType.endsWith(".txt")) {
                    itemType = itemType.substring(0, itemType.length() - 4);  // Remove ".txt"
                }

                Map<String, Map<String, String>> itemMap = categoryEntry.getValue();

                System.out.println("  ItemType: " + itemType);

                // Iterate through each item for the current itemType
                for (Map.Entry<String, Map<String, String>> itemEntry : itemMap.entrySet()) {
                    String itemName = itemEntry.getKey();
                    Map<String, String> itemDetails = itemEntry.getValue();

                    System.out.println("    Item: " + itemName);

                    // Print the details of each item
                    for (Map.Entry<String, String> detailEntry : itemDetails.entrySet()) {
                        System.out.println("      " + detailEntry.getKey() + ": " + detailEntry.getValue());
                    }
                }
            }
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
