package src.Security.src;

public class SecuritySchedulerController implements src.Security.src.interfaces.SecuritySchedulerController {

    private

    @Override
    public boolean addSecurityPersonnel(String filePath) {
        return false;
    }

    @Override
    public boolean deleteSecurityPersonnel(String employeeID) {
        return false;
    }

    @Override
    public boolean showPendingEvents() {
        return false;
    }

    @Override
    public boolean showAllEvents() {
        return false;
    }

    @Override
    public boolean showFreeSecurityEmployees() {
        return false;
    }

    @Override
    public boolean showAllSecurityEmployees() {
        return false;
    }

    @Override
    public boolean createSchedule(String requestID) {
        return false;
    }

    @Override
    public boolean deleteSchedule(String scheduleID) {
        return false;
    }

    @Override
    public boolean editScheduleAssignments(String scheduleID) {
        return false;
    }

    @Override
    public boolean showSchedule(String scheduleID) {
        return false;
    }
}
