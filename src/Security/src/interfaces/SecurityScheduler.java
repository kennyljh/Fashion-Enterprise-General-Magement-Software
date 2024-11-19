package src.Security.src.interfaces;

public interface SecurityScheduler {

    public boolean addSecurityPersonnel(String filePath);

    /**
     * Show all pending security requests
     * @return true if successful retrieval, otherwise false
     */
    public boolean showPendingEvents();

    /**
     * Show all security requests
     * @return true if successful retrieval, otherwise false
     */
    public boolean showAllEvents();

    /**
     * Create a new security schedule based on an available security request ID
     * @param requestID specified requestID
     * @return true if successfully created, false otherwise
     */
    public boolean createSchedule(String requestID);

    public boolean editSchedule(String scheduleID);

    public boolean addPersonnelToSchedule (String personnelID);
}
