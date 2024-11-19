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

    public boolean createSchedule(String requestID);

    public boolean editSchedule(String requestID);

    public boolean addPersonnelToSchedule (String personnelID);

    public boolean createSchedule();

}
