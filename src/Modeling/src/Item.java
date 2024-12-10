package src.Modeling.src;

import src.Modeling.src.interfaces.IItem;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Item implements IItem {
    static int nextid = 0;
    int id;

    Team associatedTeam;
    String itemType;
    String[] itemLocation;

    RecurrenceType recurrenceType;


    Item(int i, Team associatedTeam, String itemType, String[] itemLocation, RecurrenceType recurrenceType) {
        id = i;
        if (id >= nextid) nextid = id + 1;
        this.associatedTeam = associatedTeam;
        this.itemType = itemType;
        this.itemLocation = itemLocation;
        this.recurrenceType = recurrenceType;
    }

    Item(Team associatedTeam, String itemType, RecurrenceType recurrenceType) {
        id = nextid;
        nextid++;
        this.associatedTeam = associatedTeam;
        this.itemType = itemType;
        this.itemLocation = new String[]{Integer.toString(id), associatedTeam.toChar()};
        this.recurrenceType = recurrenceType;
    }

    @Override
    public int getId() {return id;}

    @Override
    public Team getAssociatedTeam() {return associatedTeam;}

    @Override
    public String getItemType() {return itemType;}

    @Override
    public String getItemLocation() {return itemLocation[0] + itemLocation[1];}

    @Override
    public String toString() {
        return "\n" + this.associatedTeam.toString() + " Item: " + id +
                " \nItem Type: " + this.itemType +
                " \nItem Location: " + this.getItemLocation() +
                " \nRecurrence: " + this.recurrenceType.getRecurrence();
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> itemDetails = new HashMap<>();
        itemDetails.put("id", Integer.toString(this.id));
        itemDetails.put("associatedTeam", this.associatedTeam.toString());
        itemDetails.put("itemType", this.itemType);
        itemDetails.put("itemLocation", Arrays.toString(this.itemLocation));
        return itemDetails;
    }

    public static Item parse(Map<String, String> item) {
        return new Item(
                Integer.parseInt(item.get("id")),
                Team.parseTeam(item.get("associatedTeam")),
                item.get("itemType"),
                item.get("itemLocation").replaceAll("[\\[\\] ]", "").split(","),
                RecurrenceType.parseType(item.get("recurrenceType"))
        );
    }
}
