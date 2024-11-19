package src.Security.src;

import src.TextEditor.PoorTextEditor;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class SecurityScheduler implements src.Security.src.interfaces.SecurityScheduler {

    PriorityQueue<SecurityRequests> securityRequestsPriorityQueue = new PriorityQueue<>(
            Comparator.comparingInt(request -> Integer.parseInt(request.getPriorityLevel()))
    );
    PriorityQueue<SecurityRequests> allSecurityRequestPriorityQueue = new PriorityQueue<>(
            Comparator.comparingInt(request -> Integer.parseInt(request.getPriorityLevel()))
    );
    /**
     * Repository to store all security request from departments
     */
    Map<String, SecurityRequests> securityRequestsRepository = new LinkedHashMap<>();

    private final String departmentRequestsDir = "src/Security/repository/departmentRequests/";
    private File[] securityRequestFiles = null;

    private PoorTextEditor editor = new PoorTextEditor();

    @Override
    public boolean addSecurityPersonnel(String filePath) {
        return false;
    }

    @Override
    public boolean showPendingEvents() {
        if (!retrievePendingRequests()) {
            return false;
        }

        for (SecurityRequests request : securityRequestsPriorityQueue){

            securityRequestToText(request);
        }
        return true;
    }

    @Override
    public boolean showAllEvents() {
        if (!retrieveAllRequests()){
            return false;
        }

        for (SecurityRequests request : allSecurityRequestPriorityQueue){

            securityRequestToText(request);
        }
        return true;
    }

    @Override
    public boolean createSchedule(String requestID) {

        if (securityRequestsRepository.isEmpty()){

            if (!retrieveAllRequests()){
                return false;
            }
        }

        if (checkIfRequestResolved(requestID)){

            System.out.println("This security request has already been resolved. Edit it instead.");
            return false;
        }

        // setting resolve status of security request to true
        SecurityRequests request = securityRequestsRepository.get(requestID);
        request.setResolved("true");

        if (!securityRequestsPriorityQueue.isEmpty()){
            // removing specific request from pending request queue
            securityRequestsPriorityQueue.remove(request);
        }

        


        return true;
    }

    @Override
    public boolean editSchedule(String scheduleID) {
        return false;
    }

    @Override
    public boolean addPersonnelToSchedule(String personnelID) {
        return false;
    }

    /**
     * Retrieve files containing security requests
     * @return files containing security requests
     */
    private boolean retrieveRequestFiles(){

        File directory = new File(departmentRequestsDir);
        File[] textFiles = null;

        if (directory.exists() && directory.isDirectory()){

            //grab list of text files
            FilenameFilter textFileFilter = ((dir, name) -> name.toLowerCase().endsWith(".txt"));
            textFiles = directory.listFiles(textFileFilter);
        }
        else {
            System.out.println("Repository not found");
            return false;
        }

        if (textFiles != null){

            if (textFiles.length == 0){
                System.out.println("No security requests found");
                return false;
            }
            securityRequestFiles = textFiles;
        }
        else {
            System.out.println("No security requests found");
            return false;
        }
        return true;
    }

    /**
     * Retrieve all pending security requests
     * @return true if successful retrieval, otherwise false
     */
    public boolean retrievePendingRequests() {

        if (!retrieveRequestFiles()){
            return false;
        }

        // saving file requests to repository
        for (File f : securityRequestFiles) {

            editor.processTextFile(departmentRequestsDir + f.getName());
            String[] requests = editor.getArrayNames();

            for (String s : requests) {

                if (!Boolean.parseBoolean(editor.retrieveValue(s, "resolved"))) {

                    SecurityRequests request = new SecurityRequests(s,
                            editor.retrieveValue(s, "priorityLevel"),
                            editor.retrieveValue(s, "department"),
                            editor.retrieveValue(s, "location"),
                            editor.retrieveValue(s, "description"),
                            editor.retrieveValue(s, "duration"),
                            editor.retrieveValue(s, "tasks"),
                            editor.retrieveValue(s, "specialReqs"),
                            editor.retrieveValue(s, "dateIssued"));

                    request.setFileName(f.getName());

                    if (request.getPriorityLevel() == null || request.getDepartment() == null ||
                            request.getLocation() == null || request.getDescription() == null ||
                            request.getDuration() == null || request.getTasks() == null ||
                            request.getSpecialReqs() == null || request.getDateIssued() == null) {

                        return false;
                    }

                    // adding security request to priority queue
                    securityRequestsPriorityQueue.add(request);
                }
            }
        }
        return true;
    }

    /**
     * Retrieve all security requests
     * @return true if successful retrieval, otherwise false
     */
    public boolean retrieveAllRequests() {

        if (!retrieveRequestFiles()) {
            return false;
        }

        // saving file requests to repository
        for (File f : securityRequestFiles) {

            editor.processTextFile(departmentRequestsDir + f.getName());
            String[] requests = editor.getArrayNames();

            for (String s : requests) {

                SecurityRequests request = new SecurityRequests(s,
                        editor.retrieveValue(s, "priorityLevel"),
                        editor.retrieveValue(s, "department"),
                        editor.retrieveValue(s, "location"),
                        editor.retrieveValue(s, "description"),
                        editor.retrieveValue(s, "duration"),
                        editor.retrieveValue(s, "tasks"),
                        editor.retrieveValue(s, "specialReqs"),
                        editor.retrieveValue(s, "dateIssued"));

                request.setFileName(f.getName());

                if (request.getPriorityLevel() == null || request.getDepartment() == null ||
                        request.getLocation() == null || request.getDescription() == null ||
                        request.getDuration() == null || request.getTasks() == null ||
                        request.getSpecialReqs() == null || request.getDateIssued() == null) {

                    return false;
                }

                // adding security request to repository and priority queue
                securityRequestsRepository.put(s, request);
                allSecurityRequestPriorityQueue.add(request);
            }
        }
        return true;
    }

    /**
     * Pretty print security requests
     * @param request specific security request
     */
    private void securityRequestToText(SecurityRequests request){

        System.out.println("====================================================");
        System.out.println("Security Request ID: " + request.getRequestID());
        System.out.println("Priority Level: " + request.getPriorityLevel());
        System.out.println("Department: " + request.getDepartment());
        System.out.println();
        System.out.println("Location: " + request.getLocation());
        System.out.println("Description: " + request.getDescription());
        System.out.println("Duration: " + request.getDuration());
        System.out.println("Tasks: " + request.getTasks());
        System.out.println("Special Requests: " + request.getSpecialReqs());
        System.out.println();
        System.out.println("Date Issued: " + request.getDateIssued());
        System.out.println("Resolve Status: " + request.getResolved());
        System.out.println("====================================================\n");
    }

    /**
     * Check if a security request has already been resolved
     * @param requestID ID of security request
     * @return true if already resolved, otherwise false
     */
    private boolean checkIfRequestResolved(String requestID){

        return Boolean.parseBoolean(securityRequestsRepository.get(requestID).getResolved());
    }
}
