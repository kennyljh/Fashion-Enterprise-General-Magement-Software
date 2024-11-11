package src.Treasury;

import src.Treasury.src.PayrollController;

public class TreasuryDepartment {

    public void start() {
        PayrollController controller = new PayrollController();
        controller.run();
    }
}
