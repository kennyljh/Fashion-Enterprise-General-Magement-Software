package src.Modeling.src;

import java.io.IOException;
import java.time.LocalDate;

import static src.Modeling.ModelingDepartment.fileManager;

public class StorageManager extends Manager {

    public StorageManager(String name, Team team) {
        super(name, team);
    }

    public StorageManager(Team team) {
        super(team);
    }

    public void addItem(Item item) throws IOException {
        fileManager.addItem(item);
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

    public Boolean requestOrder(Item item, LocalDate date) throws IOException {
        LocalDate today = LocalDate.now();
        if (date.isAfter(today.plusDays(3))) {
            System.out.println("Requested item cannot be ordered sooner than 3 days.");
            return false;
        }
        addItem(item);
        return true;
    }

    public void printWarehouse() {

    }
}
