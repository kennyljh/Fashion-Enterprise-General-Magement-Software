package src.Modeling.src;

import java.time.LocalDate;
import java.util.Map;

public class StorageManager extends Manager {
    Map<Team, String> warehouse;

    public StorageManager(String name, Team team) {
        super(name, team);
    }

    public Item addItem(Item item) {
        return null;
    }

    public Item updateItem(Team team, int itemID, Item updatedItem) {
        return null;
    }

    public Boolean deleteItem(Team team, int itemID) {
        return null;
    }

    public Boolean lookForDamages() {
        return null;
    }

    public Boolean requestOrder(Item item, LocalDate date) {
        LocalDate today = LocalDate.now();
        if (date.isAfter(today.plusDays(3))) {
            System.out.println("Requested item cannot be ordered sooner than 3 days.");
            return false;
        }
        addItem(item);
        return true;
    }
}
