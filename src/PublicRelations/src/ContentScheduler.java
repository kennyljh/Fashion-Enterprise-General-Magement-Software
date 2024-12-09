package src.PublicRelations.src;

import src.TextEditor.PoorTextEditor;

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
    PriorityQueue<ContentSchedules> planningContentSchedulesPriorityQueue = new PriorityQueue<>(
            Comparator.comparing((ContentSchedules schedule) -> {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                return LocalDateTime.parse(schedule.getDeadline(), formatter);
            }).reversed().thenComparing(schedule -> -Integer.parseInt(schedule.getPriority()))
    );
    PriorityQueue<ContentSchedules> reviewingContentSchedulesPriorityQueue = new PriorityQueue<>(
            Comparator.comparing((ContentSchedules schedule) -> {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                return LocalDateTime.parse(schedule.getDeadline(), formatter);
            }).reversed().thenComparing(schedule -> -Integer.parseInt(schedule.getPriority()))
    );
    PriorityQueue<ContentSchedules> revisingContentSchedulesPriorityQueue = new PriorityQueue<>(
            Comparator.comparing((ContentSchedules schedule) -> {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                return LocalDateTime.parse(schedule.getDeadline(), formatter);
            }).reversed().thenComparing(schedule -> -Integer.parseInt(schedule.getPriority()))
    );
    PriorityQueue<ContentSchedules> readyContentSchedulesPriorityQueue = new PriorityQueue<>(
            Comparator.comparing((ContentSchedules schedule) -> {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                return LocalDateTime.parse(schedule.getDeadline(), formatter);
            }).reversed().thenComparing(schedule -> -Integer.parseInt(schedule.getPriority()))
    );
    PriorityQueue<ContentSchedules> allContentSchedulesPriorityQueue = new PriorityQueue<>(
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

        if (!retrieveAllPREmployees()){
            return false;
        }

        for (PRPlanningEmployee employee : availablePRPlanningEmployeePriorityQueue){
            planningEmployeeToText(employee);
        }
        return true;
    }

    @Override
    public boolean showAllPlanningEmployees() {

        if (!retrieveAllPREmployees()){
            return false;
        }

        for (PRPlanningEmployee employee : allPRPlanningEmployeePriorityQueue){
            planningEmployeeToText(employee);
        }
        return true;
    }

    @Override
    public boolean showFreeReviewEmployees() {

        if (!retrieveAllPREmployees()){
            return false;
        }

        for (PRReviewEmployee employee : availablePRReviewEmployeePriorityQueue){
            reviewEmployeeToText(employee);
        }
        return true;
    }

    @Override
    public boolean showAllReviewEmployees() {
        
        if (!retrieveAllPREmployees()){
            return false;
        }

        for (PRReviewEmployee employee : allPRReviewEmployeePriorityQueue){
            reviewEmployeeToText(employee);
        }
        return true;
    }

    @Override
    public boolean showPendingRequests() {

        if (!retrieveAllRequests()){
            return false;
        }

        for (ContentRequests request : pendingContentRequestsPriorityQueue){
            contentRequestToText(request);
        }
        return true;
    }

    @Override
    public boolean showAllRequests() {

        if (!retrieveAllRequests()){
            return false;
        }

        for (ContentRequests request : allContentRequestPriorityQueue){
            contentRequestToText(request);
        }
        return true;
    }

    @Override
    public boolean createContentSchedule(String requestID) {
        return false;
    }

    @Override
    public boolean deleteContentSchedule(String contentID) {

        if (!retrieveAllSchedules()) return false;

        if (!retrieveAllPREmployees()) return false;

        if (!retrieveAllRequests()) return false;

        if (!contentSchedulesRepository.containsKey(contentID)){

            System.out.println("Content schedule ID: " + contentID + " not found");
            return false;
        }

        ContentSchedules schedule = contentSchedulesRepository.get(contentID);

        if (schedule.getPlanningTeamAssign().isEmpty()|| schedule.getReviewTeamAssign().isEmpty()){

            System.out.println("Incomplete employee assignment for content schedule ID: " + contentID);
            return false;
        }

        /**
         * Freeing planning employees current assignment
         */
        List<String> planningEmployees = schedule.getPlanningTeamAssign();
        editor.processTextFile(PRPlanningEmployeesDir + "PRPlanningEmployeeList.txt");
        for (String s : planningEmployees){
            editor.setValue(s, "currentAssignment", "free");
        }
        editor.writeToTextFile(PRPlanningEmployeesDir + "PRPlanningEmployeeList.txt");

        /**
         * Freeing review employees current assignment
         */
        List<String> reviewEmployees = schedule.getReviewTeamAssign();
        editor.processTextFile(PRReviewEmployeesDir + "PRReviewEmployeeList.txt");
        for (String s : reviewEmployees){
            editor.setValue(s, "currentAssignment", "free");
        }
        editor.writeToTextFile(PRReviewEmployeesDir + "PRReviewEmployeeList.txt");

        /**
         * Setting content request resolved status back to false
         */
        ContentRequests request = contentRequestsRepository.get(schedule.getRequestID());
        editor.processTextFile(departmentRequestDir + request.getFileName());
        editor.setValue(schedule.getRequestID(), "resolved", "false");
        editor.writeToTextFile(departmentRequestDir + request.getFileName());

        /**
         * Deleting content schedule
         */
        File fileToDelete = new File(contentSchedulesDir, schedule.getFileName());

        if (fileToDelete.exists()){

            if (fileToDelete.delete()){

                System.out.println("Content schedule of ID: " + contentID + " successfully deleted");
            }
            else {

                System.out.println("Failed to delete content schedule ID: " + contentID);
                return false;
            }
        }
        else {

            System.out.println("Content schedule with ID: " + contentID + " not found");
            return false;
        }
        return true;
    }

    @Override
    public boolean editContentSchedule(String contentID) {
        return false;
    }

    @Override
    public boolean showAllContentSchedules() {

        if (!retrieveAllSchedules()){
            return false;
        }

        for (ContentSchedules schedule : allContentSchedulesPriorityQueue){
            contentScheduleToText(schedule);
        }
        return true;
    }

    @Override
    public boolean showPlanningContentSchedules() {

        if (!retrieveAllSchedules()){
            return false;
        }

        for (ContentSchedules schedule : planningContentSchedulesPriorityQueue){
            contentScheduleToText(schedule);
        }
        return true;
    }

    @Override
    public boolean showReviewingContentSchedules() {

        if (!retrieveAllSchedules()){
            return false;
        }

        for (ContentSchedules schedule : reviewingContentSchedulesPriorityQueue){
            contentScheduleToText(schedule);
        }
        return true;
    }

    @Override
    public boolean showRevisingContentSchedules() {

        if (!retrieveAllSchedules()){
            return false;
        }

        for (ContentSchedules schedule : revisingContentSchedulesPriorityQueue){
            contentScheduleToText(schedule);
        }
        return true;
    }

    @Override
    public boolean showReadyContentSchedules() {

        if (!retrieveAllSchedules()){
            return false;
        }

        for (ContentSchedules schedule : readyContentSchedulesPriorityQueue){
            contentScheduleToText(schedule);
        }
        return true;
    }

    @Override
    public boolean showContentScheduleByID(String contentID) {

        if (!retrieveAllSchedules()){
            return false;
        }

        if (!contentSchedulesRepository.containsKey(contentID)){

            System.out.println("Content schedule with ID: " + contentID + " not found");
            return false;
        }

        contentScheduleToText(contentSchedulesRepository.get(contentID));
        return true;
    }

    /**
     * Retrieve all content schedules
     * @return true if successful, false otherwise
     */
    private boolean retrieveAllSchedules() {

        planningContentSchedulesPriorityQueue.clear();
        reviewingContentSchedulesPriorityQueue.clear();
        revisingContentSchedulesPriorityQueue.clear();
        readyContentSchedulesPriorityQueue.clear();
        allContentSchedulesPriorityQueue.clear();
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
                        editor.retrieveValue(s, "requestID"),
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
                    schedule.getStatus() == null || schedule.getDateScheduled() == null ||
                    schedule.getFileName() == null || schedule.getPlanningTeamAssign().isEmpty() ||
                    schedule.getReviewTeamAssign().isEmpty()){

                    System.out.println("Invalid schedule data detected");
                    return false;
                }

                String status = schedule.getStatus();
                switch (status){
                    case "planning" -> planningContentSchedulesPriorityQueue.add(schedule);
                    case "reviewing" -> reviewingContentSchedulesPriorityQueue.add(schedule);
                    case "revising" -> revisingContentSchedulesPriorityQueue.add(schedule);
                    case "ready" -> readyContentSchedulesPriorityQueue.add(schedule);
                }

                allContentSchedulesPriorityQueue.add(schedule);
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

    /**
     * Pretty print content requests
     * @param request specific content request
     */
    private void contentRequestToText(ContentRequests request) {

        System.out.println("======================================================");
        System.out.println("Security Request ID: " + request.getRequestID());
        System.out.println("Priority: " + request.getPriority());
        System.out.println("Department: " + request.getDepartment());
        System.out.println();
        System.out.println("Content Category: " + request.getContentCategory());
        System.out.println("Theme: " + request.getTheme());
        System.out.println("Platform: " + request.getPlatform());
        System.out.println("Description: " + request.getDescription());
        System.out.println("Tasks: " + request.getTasks());
        System.out.println();
        System.out.println("Duration: " + request.getDuration());
        System.out.println("Deadline: " + request.getDeadline());
        System.out.println("Special Requests: " + request.getSpecialReqs());
        System.out.println();
        System.out.println("Date Issued: " + request.getDateIssued());
        System.out.println("Resolve Status: " + request.getResolved());
        System.out.println("======================================================\n");
    }

    /**
     * Pretty print content schedules
     * @param schedule specific content schedule
     */
    private void contentScheduleToText(ContentSchedules schedule){

        System.out.println("=======================================================");
        System.out.println("Schedule ID: " + schedule.getScheduleID());
        System.out.println("is associated with");
        System.out.println("Request ID: " + schedule.getRequestID());
        System.out.println("------------------------------------------------------");
        System.out.println("Status: " + schedule.getStatus());
        System.out.println("Priority: " + schedule.getPriority());
        System.out.println("Department: " + schedule.getDepartment());
        System.out.println("------------------------------------------------------");
        System.out.println("Content Category: " + schedule.getContentCategory());
        System.out.println("Theme: " + schedule.getTheme());
        System.out.println("Platform: " + schedule.getPlatform());
        System.out.println("Description: " + schedule.getDescription());
        System.out.println("Tasks: " + schedule.getTasks());
        System.out.println("------------------------------------------------------");
        System.out.println("Duration: " + schedule.getDuration());
        System.out.println("Deadline: " + schedule.getDeadline());
        System.out.println("Date Scheduled: " + schedule.getDateScheduled());
        System.out.println("------------------------------------------------------");
        System.out.println("Assigned Planning Employees:");
        for (String s : schedule.getPlanningTeamAssign()){
            System.out.println(s);
        }
        System.out.println("Assigned Review Employees:");
        for (String s : schedule.getReviewTeamAssign()){
            System.out.println(s);
        }
        System.out.println("=======================================================\n");
    }

    /**
     * Pretty print planning employee
     * @param employee specific planning employee
     */
    private void planningEmployeeToText(PRPlanningEmployee employee){

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Employee ID: " + employee.getEmployeeID());
        System.out.println("Name: " + employee.getName());
        System.out.println("Division: " + employee.getDivision());
        System.out.println("Position: " + employee.getPosition());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("------------------------------------------------------");
        System.out.println("Rating: " + employee.getRating());
        System.out.println("Years of Experience: " + employee.getYearsOfExperience());
        System.out.println("Previous Assignment: " + employee.getPreviousAssignment());
        System.out.println("Current Assignment: " + employee.getCurrentAssignment());
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
    }

    /**
     * Pretty print review employee
     * @param employee specific review employee
     */
    private void reviewEmployeeToText(PRReviewEmployee employee){

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Employee ID: " + employee.getEmployeeID());
        System.out.println("Name: " + employee.getName());
        System.out.println("Division: " + employee.getDivision());
        System.out.println("Position: " + employee.getPosition());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("------------------------------------------------------");
        System.out.println("Rating: " + employee.getRating());
        System.out.println("Years of Experience: " + employee.getYearsOfExperience());
        System.out.println("Previous Assignment: " + employee.getPreviousAssignment());
        System.out.println("Current Assignment: " + employee.getCurrentAssignment());
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
    }










































}
