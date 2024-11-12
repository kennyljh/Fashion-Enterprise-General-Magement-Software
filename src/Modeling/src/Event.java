package src.Modeling.src;

import src.HR.src.Employee;
import src.Modeling.src.interfaces.IEvent;

public class Event implements IEvent {
    int id;
    TeamMember[] teamMembers;
    Boolean type; //true for photoshoot, false for fashion show
    String celebrity;
    Boolean collab;
    Boolean completionStatus;

    Event(int id, TeamMember[] teamMembers, Boolean type, String celebrity, Boolean collab) {
        this.id = id;
        this.teamMembers = teamMembers;
        this.type = type;
        this.celebrity = celebrity;
        this.collab = collab;
        this.completionStatus = false;
    }

    @Override
    public void endEvent() {
        this.completionStatus = true;
        System.out.println(this.toString());
    }

    public String toString() {
        String str = "\nEvent: ";
        if(this.type) {
            str += "Photoshoot";
        } else {
            str += "Fashion show";
        }
        str += "\nModels: ";

        for (int i = 0; i < teamMembers.length - 1; i++) {
            str += "\n" + teamMembers[i].toString();
        }
        str += "\nCelebrity: " + this.celebrity +
                "\nCollab: " + this.collab +
                "\nCompletion Status: " + this.completionStatus;
        return str;
    }
}
