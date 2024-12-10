package src.Modeling.src;

import src.Modeling.src.interfaces.IItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Item implements IItem {
    static int nextid = 0;
    int id;

    Team associatedTeam;
    String itemType;
    String[] itemLocation;
    String itemName;
    RecurrenceType recurrenceType;


    Item(int i, Team associatedTeam, String itemType, String[] itemLocation, String itemName, RecurrenceType recurrenceType) {
        id = i;
        if (id >= nextid) nextid = id + 1;
        this.associatedTeam = associatedTeam;
        this.itemType = itemType;
        this.itemLocation = itemLocation;
        this.itemName = itemName;
        this.recurrenceType = recurrenceType;
    }

    public Item(Team associatedTeam, String itemType, String itemName, RecurrenceType recurrenceType) {
        id = nextid;
        nextid++;
        this.associatedTeam = associatedTeam;
        this.itemType = itemType;
        this.itemLocation = new String[]{Integer.toString(id), associatedTeam.toChar()};
        this.itemName = itemName;
        this.recurrenceType = recurrenceType;
    }

    @Override
    public int getId() {return id;}

    @Override
    public Team getAssociatedTeam() {return associatedTeam;}

    @Override
    public String getItemType() {return itemType;}

    @Override
    public String getItemName() {return itemName;}

    @Override
    public String getItemLocation() {return itemLocation[0] + itemLocation[1];}

    @Override
    public String toString() {
        return "\n" + this.associatedTeam.toString() + " Item: " + id +
                "\n Item Type: " + this.itemType +
                "\n Item Location: " + this.getItemLocation() +
                "\n Item Name: " + this.getItemName() +
                "\n Recurrence: " + this.recurrenceType.getRecurrence();
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> itemDetails = new HashMap<>();
        itemDetails.put("id", Integer.toString(this.id));
        itemDetails.put("associatedTeam", this.associatedTeam.toString());
        itemDetails.put("itemType", this.itemType);
        itemDetails.put("itemLocation", Arrays.toString(this.itemLocation));
        itemDetails.put("itemName", this.itemName);
        return itemDetails;
    }

    public static Item parse(Map<String, String> item) {
        return new Item(
                Integer.parseInt(item.get("id")),
                Team.parseTeam(item.get("associatedTeam")),
                item.get("itemType"),
                item.get("itemLocation").replaceAll("[\\[\\] ]", "").split(","),
                item.get("itemName"),
                RecurrenceType.parseType(item.get("recurrenceType"))
        );
    }
}
