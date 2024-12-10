package src.Modeling.src;

import src.HR.src.Employee;
import src.Modeling.ModelingDepartment;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static src.Modeling.ModelingDepartment.fileManager;

public class StorageManager extends Manager {

    public StorageManager(String name, Team team) {
        super(name, team);
    }

    public StorageManager(Team team) {
        super(team);
    }

    public StorageManager(int id, Employee employeeInfo, Team team) {
        super(id, employeeInfo, team);
    }

    public void addItem(Item item) throws IOException {
        fileManager.addItem(item);
    }

    public void updateItem(Item updatedItem, Team oldTeam) throws IOException {
        fileManager.updateItem(updatedItem, oldTeam);
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

    public static StorageManager parse(Map<String, String> manager) {
        return new StorageManager(
                Integer.parseInt(manager.get("id")),
                Employee.parseEmployee(manager.get("employeeInfo")),
                Team.parseTeam(manager.get("team"))
        );
    }


}
