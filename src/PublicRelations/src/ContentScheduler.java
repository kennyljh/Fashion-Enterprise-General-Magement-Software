package src.PublicRelations.src;

import src.Security.src.SecurityEmployee;
import src.Security.src.SecurityRequests;
import src.Security.src.SecuritySchedules;
import src.TextEditor.PoorTextEditor;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ContentScheduler implements src.PublicRelations.src.interfaces.ContentScheduler {

    private final String departmentRequestDir = "src/PublicRelations/repository/departmentRequests/";
    private final String PRPlanningEmployeesDir = "src/PublicRelations/repository/planningEmployees/";
    private final String PRReviewEmployeesDir = "src/PublicRelations/repository/reviewEmployees/";
    private final String contentSchedulesDir = "src/PublicRelations/repository/contentSchedules/";
    private final String printedContentSchedulesDir = "src/PublicRelations/repository/printedContentSchedules/";

    PoorTextEditor editor = new PoorTextEditor();

    // sorts content requests by deadline first, then by priority
    PriorityQueue<ContentRequests> pendingContentRequestsPriorityQueue = new PriorityQueue<>(
            Comparator.comparing((ContentRequests request) -> {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                return LocalDateTime.parse(request.getDeadline(), formatter);
            }).reversed().thenComparing(schedule -> -Integer.parseInt(schedule.getPriority()))
    );
    PriorityQueue<ContentRequests> allContentRequestPriorityQueue = new PriorityQueue<>(
            Comparator.comparing((ContentRequests request) -> {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                return LocalDateTime.parse(request.getDeadline(), formatter);
            }).reversed().thenComparing(schedule -> -Integer.parseInt(schedule.getPriority()))
    );
    /**
     * Repository to store all content requests
     */
    Map<String, ContentRequests> contentRequestsRepository = new LinkedHashMap<>();

    // sorts content schedules by deadline first, then by priority
    PriorityQueue<ContentSchedules> contentSchedulesPriorityQueue = new PriorityQueue<>(
            Comparator.comparing((ContentSchedules schedule) -> {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                return LocalDateTime.parse(schedule.getDeadline(), formatter);
            }).reversed().thenComparing(schedule -> -Integer.parseInt(schedule.getPriority()))
    );
    /**
     * Repository to store all content schedules
     */
    Map<String, ContentSchedules> contentSchedulesRepository = new LinkedHashMap<>();

    // sorts employees by division, then position, then rating in descending order
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
    /**
     * Repository to store all PR employees
     */
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

    /**
     * Retrieve all content schedules
     * @return true if successful, false otherwise
     */
    private boolean retrieveAllSchedules() {

        contentSchedulesPriorityQueue.clear();
        contentSchedulesRepository.clear();

        File[] scheduleTextFiles = retrieveScheduleFiles(contentSchedulesDir);

        if (scheduleTextFiles == null){
            return false;
        }

        for (File f : scheduleTextFiles){

            editor.processTextFile(contentSchedulesDir + f.getName());
            String[] schedules = editor.getArrayNames();

            for (String s : schedules){

                ContentSchedules schedule = new ContentSchedules(s,
                        editor.retrieveValue(s, "theme"),
                        editor.retrieveValue(s, "contentCategory"),
                        editor.retrieveValue(s, "platform"),
                        editor.retrieveValue(s, "duration"),
                        editor.retrieveValue(s, "deadline"),
                        editor.retrieveValue(s, "description"),
                        editor.retrieveValue(s, "tasks"),
                        editor.retrieveValue(s, "department"),
                        editor.retrieveValue(s, "priority"),
                        editor.retrieveValue(s, "status"),
                        editor.retrieveValue(s, "dateIssued"),
                        f.getName());

                List<String> planningEmployees = new ArrayList<>();
                int num = 0;
                String planningEmployeeID = "employeePlanning" + num;
                while ((editor.retrieveValue(s, planningEmployeeID)) != null){

                    planningEmployees.add(editor.retrieveValue(s, planningEmployeeID));
                    num++;
                    planningEmployeeID = "employeePlanning" + num;
                }
                schedule.setPlanningTeamAssign(planningEmployees);

                List<String> reviewEmployees = new ArrayList<>();
                int num1 = 0;
                String reviewEmployeeID = "employeeReview" + num1;
                while ((editor.retrieveValue(s, reviewEmployeeID)) != null){

                    planningEmployees.add(editor.retrieveValue(s, reviewEmployeeID));
                    num1++;
                    reviewEmployeeID = "employeeReview" + num1;
                }
                schedule.setReviewTeamAssign(reviewEmployees);

                if (schedule.getScheduleID() == null || schedule.getTheme() == null ||
                    schedule.getContentCategory() == null || schedule.getPlatform() == null ||
                    schedule.getDuration() == null || schedule.getDeadline() == null ||
                    schedule.getDescription() == null || schedule.getTasks() == null ||
                    schedule.getDepartment() == null || schedule.getPriority() == null ||
                    schedule.getStatus() == null || schedule.getDateIssued() == null ||
                    schedule.getFileName() == null || schedule.getPlanningTeamAssign().isEmpty() ||
                    schedule.getReviewTeamAssign().isEmpty()){

                    System.out.println("Invalid schedule data detected");
                    return false;
                }

                contentSchedulesPriorityQueue.add(schedule);
                contentSchedulesRepository.put(s, schedule);
            }
        }
        return true;
    }

    /**
     * Retrieve schedule file list
     * @param fileDirectory given directory
     * @return schedule file list
     */
    private File[] retrieveScheduleFiles(String fileDirectory){

        File directory = new File(fileDirectory);
        File[] textFiles = null;

        if (directory.exists() && directory.isDirectory()){

            // grab list of text files
            FilenameFilter textFileFilter = ((dir, name) -> name.toLowerCase().endsWith(".txt"));
            textFiles = directory.listFiles(textFileFilter);
        }
        else {

            System.out.println("Repository not found for directory: " + fileDirectory);
            return null;
        }

        if (textFiles != null) {

            if (textFiles.length == 0){
                System.out.println("No content schedules found");
                return null;
            }
        }
        else {
            System.out.println("No content schedules found");
            return null;
        }
        return textFiles;
    }

    /**
     * Retrieve all content requests
     * @return true if successful, false otherwise
     */
    private boolean retrieveAllRequests() {

        // clearing repositories and queues
        pendingContentRequestsPriorityQueue.clear();
        allContentRequestPriorityQueue.clear();
        contentRequestsRepository.clear();

        File[] requestTextFiles = retrieveRequestFiles(departmentRequestDir);

        if (requestTextFiles == null){
            return false;
        }

        for (File f : requestTextFiles){

            editor.processTextFile(departmentRequestDir + f.getName());
            String[] requests = editor.getArrayNames();

            for (String s : requests){

                ContentRequests request = new ContentRequests(s,
                        editor.retrieveValue(s, "theme"),
                        editor.retrieveValue(s, "contentCategory"),
                        editor.retrieveValue(s, "platform"),
                        editor.retrieveValue(s, "duration"),
                        editor.retrieveValue(s, "deadline"),
                        editor.retrieveValue(s, "description"),
                        editor.retrieveValue(s, "tasks"),
                        editor.retrieveValue(s, "department"),
                        editor.retrieveValue(s, "priority"),
                        editor.retrieveValue(s, "specialReqs"),
                        editor.retrieveValue(s, "dateIssued"),
                        editor.retrieveValue(s, "resolved"),
                        (f.getName()));

                if (request.getTheme() == null || request.getContentCategory() == null ||
                    request.getPlatform() == null || request.getDuration() == null ||
                    request.getDeadline() == null || request.getDescription() == null ||
                    request.getTasks() == null || request.getDepartment() == null ||
                    request.getPriority() == null || request.getSpecialReqs() == null ||
                    request.getDateIssued() == null || request.getResolved() == null){

                    System.out.println("Invalid content request detected");
                    return false;
                }

                if (!Boolean.parseBoolean(request.getResolved())){
                    pendingContentRequestsPriorityQueue.add(request);
                }
                else {
                    allContentRequestPriorityQueue.add(request);
                }
                contentRequestsRepository.put(s,request);
            }
        }
        return true;
    }

    /**
     * Retrieve request file list
     * @param fileDirectory given directory
     * @return request file list
     */
    private File[] retrieveRequestFiles(String fileDirectory) {

        File directory = new File(fileDirectory);
        File[] textFiles = null;

        if (directory.exists() && directory.isDirectory()){

            // grab list of text files
            FilenameFilter textFileFilter = ((dir, name) -> name.toLowerCase().endsWith(".txt"));
            textFiles = directory.listFiles(textFileFilter);
        }
        else {

            System.out.println("Repository not found for directory: " + fileDirectory);
            return null;
        }

        if (textFiles != null) {

            if (textFiles.length == 0){
                System.out.println("No content requests found");
                return null;
            }
        }
        else {
            System.out.println("No content requests found");
            return null;
        }
        return textFiles;
    }

    /**
     * Retrieve all PR Planning and Review Employees
     * @return true if successful, false otherwise
     */
    private boolean retrieveAllPREmployees(){

        // clearing repositories and queues
        availablePRPlanningEmployeePriorityQueue.clear();
        availablePRReviewEmployeePriorityQueue.clear();
        allPRPlanningEmployeePriorityQueue.clear();
        allPRReviewEmployeePriorityQueue.clear();
        PRPlanningEmployeeRepository.clear();
        PRReviewEmployeeRepository.clear();

        // saving Planning employees
        File[] planningTextFiles = retrieveEmployeeFiles(PRPlanningEmployeesDir);

        if (planningTextFiles == null){
            return false;
        }

        for (File f : planningTextFiles){

            editor.processTextFile(PRPlanningEmployeesDir + f.getName());
            String[] employees = editor.getArrayNames();

            for (String s : employees){

                PRPlanningEmployee employee = new PRPlanningEmployee(s,
                        editor.retrieveValue(s,"name"),
                        editor.retrieveValue(s, "division"),
                        editor.retrieveValue(s, "position"),
                        editor.retrieveValue(s, "department"),
                        editor.retrieveValue(s, "rating"),
                        editor.retrieveValue(s, "yearsOfExperience"),
                        editor.retrieveValue(s, "previousAssignment"),
                        editor.retrieveValue(s, "currentAssignment"));

                if (employee.getEmployeeID() == null || employee.getName() == null ||
                        employee.getDivision() == null || employee.getPosition() == null ||
                        employee.getDepartment() == null || employee.getRating() == null ||
                        employee.getYearsOfExperience() == null || employee.getPreviousAssignment() == null ||
                        employee.getCurrentAssignment() == null) {

                    System.out.println("Invalid employee data detected");
                    return false;
                }

                if (employee.getCurrentAssignment().equals("free")){
                    availablePRPlanningEmployeePriorityQueue.add(employee);
                }
                allPRPlanningEmployeePriorityQueue.add(employee);
                PRPlanningEmployeeRepository.put(s, employee);
            }
        }

        // saving Review employees
        File[] reviewTextFiles = retrieveEmployeeFiles(PRReviewEmployeesDir);

        if (reviewTextFiles == null){
            return false;
        }

        for (File f : reviewTextFiles){

            editor.processTextFile(PRReviewEmployeesDir + f.getName());
            String[] employees = editor.getArrayNames();

            for (String s : employees){

                PRReviewEmployee employee = new PRReviewEmployee(s,
                        editor.retrieveValue(s,"name"),
                        editor.retrieveValue(s, "division"),
                        editor.retrieveValue(s, "position"),
                        editor.retrieveValue(s, "department"),
                        editor.retrieveValue(s, "rating"),
                        editor.retrieveValue(s, "yearsOfExperience"),
                        editor.retrieveValue(s, "previousAssignment"),
                        editor.retrieveValue(s, "currentAssignment"));

                if (employee.getEmployeeID() == null || employee.getName() == null ||
                        employee.getDivision() == null || employee.getPosition() == null ||
                        employee.getDepartment() == null || employee.getRating() == null ||
                        employee.getYearsOfExperience() == null || employee.getPreviousAssignment() == null ||
                        employee.getCurrentAssignment() == null) {

                    System.out.println("Invalid employee data detected");
                    return false;
                }

                if (employee.getCurrentAssignment().equals("free")){
                    availablePRReviewEmployeePriorityQueue.add(employee);
                }
                allPRReviewEmployeePriorityQueue.add(employee);
                PRReviewEmployeeRepository.put(s, employee);
            }
        }
        return true;
    }

    /**
     * Retrieve employee file list
     * @param fileDirectory given directory
     * @return employee file list
     */
    private File[] retrieveEmployeeFiles(String fileDirectory){

        File directory = new File(fileDirectory);
        File[] textFiles = null;

        if (directory.exists() && directory.isDirectory()){

            // grab list of text files
            FilenameFilter textFileFilter = ((dir, name) -> name.toLowerCase().endsWith(".txt"));
            textFiles = directory.listFiles(textFileFilter);
        }
        else {

            System.out.println("Repository not found for directory: " + fileDirectory);
            return null;
        }

        if (textFiles != null) {

            if (textFiles.length == 0){
                System.out.println("No employees found");
                return null;
            }
        }
        else {
            System.out.println("No employees found");
            return null;
        }
        return textFiles;
    }










































}
