package src.PublicRelations.src.interfaces;

public interface ContentSchedulerController {

    boolean addPREmployee();

    boolean deletePREmployee(String employeeID);

    boolean showFreePlanningEmployees();

    boolean showAllPlanningEmployees();

    boolean showFreeReviewEmployees();

    boolean showAllReviewEmployees();

    boolean showPendingRequests();

    boolean showAllRequests();

    boolean createContent(String requestID);

    boolean deleteContent(String contentID);

    boolean editContent(String contentID);

    boolean showAllContents();

    boolean showPlanningContents();

    boolean showReviewingContents();

    boolean showRevisingContents();

    boolean showReadyContents();

    boolean showContentByID(String contentID);
}
