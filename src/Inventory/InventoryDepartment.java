package src.Inventory;

import src.Inventory.src.BasicInventoryController;

public class InventoryDepartment {

    public void start() throws Exception {
        BasicInventoryController basicInventoryController = new BasicInventoryController();
        basicInventoryController.run();

    }
}
