package src.Modeling.src;

import src.HR.src.Employee;
import src.Modeling.src.interfaces.IEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Event implements IEvent {
    static int nextid = 0;
    int id;
    TeamMember[] teamMembers;
    Boolean type; //true for photoshoot, false for fashion show
    String celebrity;
    String collab;
    Boolean completionStatus;

    Event(TeamMember[] teamMembers, Boolean type, String celebrity, String collab) {
        id = nextid;
        nextid++;
        this.teamMembers = teamMembers;
        this.type = type;
        this.celebrity = celebrity;
        this.collab = collab;
        this.completionStatus = false;
    }

    Event(int id, TeamMember[] teamMembers, Boolean type, String celebrity, String collab, Boolean completionStatus) {
        this.id = id;
        if (id > nextid) nextid = id;
        this.teamMembers = teamMembers;
        this.type = type;
        this.celebrity = celebrity;
        this.collab = collab;
        this.completionStatus = completionStatus;
    }

    public static void setNextid() {

    }

    public int getId() {
        return id;
    }
    public String getTeamMemberString() {
        String s = "";
        for (TeamMember member: teamMembers) {
            s += member.toString() + "\n";
        }

        return s;
    }
    public String getType() {
        if(this.type) return "Photoshoot";
        return "Fashion Show";
    }
    public String getCelebrity() {
        return this.celebrity;
    }
    public String getCollab() {
        return this.collab;
    }
    public String getCompletion() {
        if(completionStatus) return "true";
        return "false";
    }

    @Override
    public void endEvent() {
        this.completionStatus = true;
        System.out.println(this.toString());
    }

    public String toString() {
        String str = "\nEvent: " + id ;
        if(this.type) {
            str += "\nPhotoshoot";
        } else {
            str += "\nFashion show";
        }
        str += "\nTeam Members: ";

        for (int i = 0; i < teamMembers.length - 1; i++) {
            str += "\n" + teamMembers[i].toString();
        }
        str += "\nCelebrity: " + this.celebrity +
                "\nCollab: " + this.collab +
                "\nCompletion Status: " + this.completionStatus;
        return str;
    }

    public Map<String, String> eventToMap() {
            Map<String, String> eventDetails = new HashMap<>();
            eventDetails.put("id", Integer.toString(this.id));
            eventDetails.put("type", this.getType());
            Integer[] tmp = new Integer[teamMembers.length];
            for(int i = 0; i < teamMembers.length; i++) {
                tmp[i] = teamMembers[i].getId();
            }
            eventDetails.put("teamMembers", Arrays.toString(tmp));
            eventDetails.put("celebrity", this.getCelebrity());
            eventDetails.put("collab", this.getCollab());
            eventDetails.put("completionStatus", this.getCompletion());
            return eventDetails;
    }


//
//    public static Event parseEvent(String eventString) {
//        String[] lines = eventString.split("\n");
//
//        int id = Integer.parseInt(lines[1].split(": ")[1].trim());
//        Boolean type = lines[2].contains("Photoshoot");
//
//        int modelsStartIndex = 3;
//        int modelEndIndex = lines.length - 4;
//        TeamMember[] teamMembers = new TeamMember[modelEndIndex - modelsStartIndex + 1];
//        for(int i = modelsStartIndex; i <= modelEndIndex; i++) {
//            teamMembers[i-modelsStartIndex] = TeamMember.parseTeamMember(lines[i].trim());
//        }
//
//        String celebrity = lines[modelEndIndex+1].split(": ")[1].trim();
//        String collab = lines[modelEndIndex+2].split(": ")[1].trim();
//        Boolean completionStatus = lines[modelEndIndex+3].contains("true");
//
//        return new Event(id, teamMembers, type, celebrity, collab, completionStatus);
//    }
}
