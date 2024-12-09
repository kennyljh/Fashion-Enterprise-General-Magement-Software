package src.PublicRelations.src;

import src.Security.src.SecurityEmployee;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ContentScheduler implements src.PublicRelations.src.interfaces.ContentScheduler {

    private final String departmentRequestDir = "src/PublicRelations/repository/departmentRequests/";
    private final String PRPlanningEmployeesDir = "src/PublicRelations/repository/planningEmployees/";
    private final String PRReviewEmployeesDir = "src/PublicRelations/repository/reviewEmployees/";
    private final String contentSchedulesDir = "src/PublicRelations/repository/contentSchedules/";
    private final String printedContentSchedulesDir = "src/PublicRelations/repository/printedContentSchedules/";

    Map<String, ContentRequests> contentRequestsRepository = new LinkedHashMap<>();

    Map<String, ContentSchedules> contentSchedulesRepository = new LinkedHashMap<>();

    /**
     * Priority queues for PR employees
     */
    PriorityQueue<PRPlanningEmployee> availablePRPlanningEmployeePriorityQueue = new PriorityQueue<>(
            Comparator.comparing(PRPlanningEmployee::getDivision)
                    .thenComparing(PRPlanningEmployee::getPosition)
                    .thenComparing((e1, e2) -> e2.getRating().compareTo(e1.getRating()))
    );
    PriorityQueue<PRPlanningEmployee> allPRPlanningEmployeePriorityQueue = new PriorityQueue<>(
            Comparator.comparing(PRPlanningEmployee::getDivision)
                    .thenComparing(PRPlanningEmployee::getPosition)
                    .thenComparing((e1, e2) -> e2.getRating().compareTo(e1.getRating()))
    );
    PriorityQueue<PRReviewEmployee> availablePRReviewEmployeePriorityQueue = new PriorityQueue<>(
            Comparator.comparing(PRReviewEmployee::getDivision)
                    .thenComparing(PRReviewEmployee::getPosition)
                    .thenComparing((e1, e2) -> e2.getRating().compareTo(e1.getRating()))
    );
    PriorityQueue<PRReviewEmployee> allPRReviewEmployeePriorityQueue = new PriorityQueue<>(
            Comparator.comparing(PRReviewEmployee::getDivision)
                    .thenComparing(PRReviewEmployee::getPosition)
                    .thenComparing((e1, e2) -> e2.getRating().compareTo(e1.getRating()))
    );

    Map<String, PRPlanningEmployee> PRPlanningEmployeeRepository = new LinkedHashMap<>();

    Map<String, PRReviewEmployee> PRReviewEmployeeRepository = new LinkedHashMap<>();

    @Override
    public boolean addPREmployee() {
        return false;
    }

    @Override
    public boolean deletePREmployee(String employeeID) {
        return false;
    }

    @Override
    public boolean showFreePlanningEmployees() {
        return false;
    }

    @Override
    public boolean showAllPlanningEmployees() {
        return false;
    }

    @Override
    public boolean showFreeReviewEmployees() {
        return false;
    }

    @Override
    public boolean showAllReviewEmployees() {
        return false;
    }

    @Override
    public boolean showPendingRequests() {
        return false;
    }

    @Override
    public boolean showAllRequests() {
        return false;
    }

    @Override
    public boolean createContent(String requestID) {
        return false;
    }

    @Override
    public boolean deleteContent(String contentID) {
        return false;
    }

    @Override
    public boolean editContent(String contentID) {
        return false;
    }

    @Override
    public boolean showAllContents() {
        return false;
    }

    @Override
    public boolean showPlanningContents() {
        return false;
    }

    @Override
    public boolean showReviewingContents() {
        return false;
    }

    @Override
    public boolean showRevisingContents() {
        return false;
    }

    @Override
    public boolean showReadyContents() {
        return false;
    }

    @Override
    public boolean showContentByID(String contentID) {
        return false;
    }


}
