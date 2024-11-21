package src.HR.src;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import src.HR.src.*;

/**
 * Manages Candidate records, allowing for adding, removing, updating, and displaying Candidate.
 * @author Sam Gumm
 */
public class candidateRecordManager {
    Map<String, Map<String, String>> data = new LinkedHashMap<>();
    private final fileStorageHR storageHR;

    public candidateRecordManager(fileStorageHR storageHR) {
        this.storageHR = storageHR;
    }

    /*
    TODO:
        - Make methods for moving candidates to other statuses
        - ...
     */

    /**
     * Add a new candidate into a txt file in format "candID".txt and stores it to candidateStorage
     * @param candidate the Candidate object to be stored to file
     */
    public void addCandidate(Candidate candidate) throws IOException {
        Map<String, String> candidateObject = new LinkedHashMap<>();
        candidateObject.put("candidateID", candidate.getCandidateId());
        candidateObject.put("name", candidate.getName());
        candidateObject.put("positionApplied", candidate.getPositionApplied());
        candidateObject.put("status", candidate.getStatus().toString());
        data.put(candidate.getCandidateId(), candidateObject);
        storageHR.poorJarser.setRepositoryStrings(data);
        Scanner scanner = new Scanner(System.in);
        //TODO: Print out all potential statuses here
        System.out.println("Please enter in Status of Candidate");
        candidateStatus status = candidateStatus.valueOf(scanner.nextLine());
        String filepathToCandidateStorage = String.valueOf(storageHR.getCandidateStoragePath(String.valueOf(status)));
        storageHR.poorJarser.writeToTextFile(filepathToCandidateStorage + "\\" + candidate.getCandidateId());
        scanner.close();
    }

    /**
     * Remove a candidate by ID
     * @param candidateID ID of the Candidate file to remove
     *
     */
    public void removeCandidate(String candidateID) throws Exception {
        if(data.containsKey(candidateID)) {
            try {
                data.remove(candidateID);
                storageHR.poorJarser.setRepositoryStrings(data);
            } catch (Exception e) {
                throw new Exception("Error in removeCandidate when removing from LinkedHashMap<>(): \n" + e.getMessage());
            }
        }

        try {
            //TODO implement file storage
        } catch (Exception e) {
            throw new Exception("Error in removeCandidate when deleting file from repository with file path: "
                    + storageHR.filepath + "\n" + e.getMessage());
        }
    }

    /**
     * Takes the CandidateID of the Candidate object to be changed, then checks the LinkedHashMap
     * for the ID; if it finds the ID, the associated Candidate is altered and re-uploaded to file,
     * otherwise, an error is thrown.
     * @param candidateId ID of the candidate to be updated
     */
    public void updateCandidate(String candidateId) throws Exception {
        String filepath;
        //TODO: decide whether to have direct line to candidateStorage (prolly do it)
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter in Status of Candidate to update");
        String statusString = scanner.nextLine();
        filepath = storageHR.getCandidateStoragePath(statusString) + "\\" + candidateId + ".txt";
        scanner.close();
        //read from Candidate file
        storageHR.poorJarser.processTextFile(filepath);

        //initialize data from current repo
        Map<String, Map<String, String>> data = storageHR.poorJarser.getRepositoryStringMap();

        //make sure data is not null
        //TODO: is this necessary?
        if(data == null) {
            throw new Exception("data was not initialized properly: \n");
        }

        //initialize candidateObject to modify
        Map<String, String> candidateObject = data.get(candidateId);
        if(candidateObject == null) {
            candidateObject = new LinkedHashMap<>();
        }
        scanner = new Scanner(System.in);

        System.out.println("Enter candidate name: ");
        String name = scanner.next();
        System.out.println("Enter position applied: ");
        String positionApplied = scanner.next();
        System.out.println("Enter in new candidate hiring status: \nApplied\nPending\nApproved\nRejected\nHired\n Enter Here: ");
        candidateStatus candidateStatus = src.HR.src.candidateStatus.valueOf(scanner.next().toUpperCase());
        candidateObject.put("candidateName", name);
        candidateObject.put("positionApplied", positionApplied);
        candidateObject.put("candidateStatus", String.valueOf(candidateStatus));
        data.put(candidateId, candidateObject);
        storageHR.poorJarser.setRepositoryStrings(data);
        //TODO: transfer candidate to new folder here
        storageHR.poorJarser.writeToTextFile(filepath);
        scanner.close();
    }

    /**
     * Display all candidate records
     */
    public void displayCurrentCandidateDataRecords() {
        data.values().forEach(System.out::println);
    }

    public void displayCandidatesByStatus(String candidateStatus) throws Exception {
        try {
            //TODO: implement file storage system
            System.out.print("Enter candidate name: ");
        } catch (Exception e) {
            throw new Exception("displayCandidatesByStatus failed...");
        }
    }


}
