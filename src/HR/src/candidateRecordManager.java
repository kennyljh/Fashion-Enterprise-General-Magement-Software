package src.HR.src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * Manages Candidate records, allowing for adding, removing, updating, moving and displaying Candidate.
 * @author Sam Gumm
 */
public class candidateRecordManager {
    Map<String, Map<String, String>> data = new LinkedHashMap<>();
    private final fileStorageHR storageHR = new fileStorageHR();
    private final valueHandling valueHandler = new valueHandling();

    /**
     * Add a new candidate into a txt file in format "candID".txt and stores it to candidateStorage
     * @param candidate the Candidate object to be stored to file
     * @throws IOException Throws IOException for internal mechanisms
     */
    public void addCandidate(Candidate candidate) throws IOException {
        Map<String, String> candidateObject = new LinkedHashMap<>();
        candidateObject.put("candidateID", candidate.getCandidateId());
        candidateObject.put("name", candidate.getName());
        candidateObject.put("positionApplied", candidate.getPositionApplied());
        candidateObject.put("status", candidate.getStatus().toString());
        data.put(candidate.getCandidateId(), candidateObject);
        storageHR.poorJarser.setRepositoryStrings(data);
        //TODO: Print out all potential statuses here
        String filepathToCandidateStorage = String.valueOf(storageHR.getCandidateStoragePath(String.valueOf(candidate.getStatus())));
        storageHR.poorJarser.writeToTextFile(filepathToCandidateStorage + "/" + candidate.getCandidateId() + ".txt");
        data.clear();
    }

    /**
     * //TODO: add description
     * @param candidateID String ID of the candidate file to be removed
     * @throws Exception Throws Exception if file deleteFile fails
     */
    public void removeCandidate(String candidateID) throws Exception {
        if(data.containsKey(candidateID)) {
            try {
                data.remove(candidateID);
                storageHR.poorJarser.setRepositoryStrings(data);
            } catch (Exception e) {
                throw new Exception("Error in removeCandidate when removing found ID from LinkedHashMap<>(): \n" + e.getMessage());
            }
        }

        String filePath = String.valueOf(findCandidateFile(candidateID));
        if(filePath == null) {
            throw new NullPointerException("filepath is null");
        }

        try {
            storageHR.deleteFile(filePath);
        } catch (Exception e) {
            throw new Exception("Error in removeCandidate when deleting file from repository with file path: "
                    + filePath + "\n" + e.getMessage());
        }
    }

    /**
     * //TODO: add description
     * @param candidateID String ID of the candidate that is being searched for
     * @return returns a Path object of the found candidate file
     * @throws IOException Throws an IOException if input is invalid
     */
    public Path findCandidateFile(String candidateID) throws IOException {
        Path base = storageHR.getDefault_filepath_candidateStorage();
        String candidateFileName = candidateID + ".txt"; // The candidate file name format

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(base)) {
            for (Path statusDir : directoryStream) {
                if (Files.isDirectory(statusDir)) { // Ensure it's a directory
                    Path candidateFile = statusDir.resolve(candidateFileName);
                    if (Files.exists(candidateFile)) {
                        return candidateFile;
                    }
                }
            }
        }
        return null; // Return null if the candidate file is not found
    }

    /**
     * //TODO: add description
     * @param candidateID String ID of the candidate file to be moved
     * @param status String name of the folder the candidate will be moved to
     * @throws IOException Throws IOException when input is malformed or invalid
     */
    private void moveCandidate(String candidateID, candidateStatus status) throws IOException {
        // Find the current candidate file path
        Path currentCandidateFile = findCandidateFile(candidateID);
        if (currentCandidateFile == null || !Files.exists(currentCandidateFile)) {
            throw new FileNotFoundException("Candidate file not found: " + candidateID + ".txt");
        }

        // Get the path to the new status folder
        Path newStatusFolder = storageHR.getCandidateStoragePath(status.toString());
        if (!Files.exists(newStatusFolder)) {
            Files.createDirectories(newStatusFolder);
        }

        // Construct the destination path for the candidate file
        Path destinationFile = newStatusFolder.resolve(candidateID + ".txt");

        // Move the candidate file to the new status folder
        Files.move(currentCandidateFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Candidate was successfully moved to " + destinationFile);
    }

    /**
     * Takes the CandidateID of the Candidate object to be changed, then checks candidateStorage
     * for the ID. If found, the filepath is returned, and the candidateObject is parsed, altered, and
     * moved to the new Status folder if necessary.
     * @param candidateId ID of the candidate to be updated
     * @throws Exception Throws an Exception for internal mechanisms
     */
    public void updateCandidate(String candidateId) throws Exception {
        String filepath;
        String name;
        String positionApplied;
        String candidateStatus;

        //As findCandidateFolder may return null, we need to make sure that doesn't happen
        filepath = Objects.requireNonNull(findCandidateFile(candidateId)).toString();
        //System.out.println("updateCandidate filepath from finding: " + filepath);

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

        System.out.println("Enter candidate name: ");
        name = valueHandler.inputValidator(true);
        System.out.println("Enter position applied: ");
        positionApplied = valueHandler.inputValidator(true);
        System.out.println("Enter in new candidate hiring status: \nApplied\nPending\nApproved\nRejected\nHiring\n Enter Here: ");
        candidateStatus = valueHandler.inputValidator(true).toUpperCase();
        candidateObject.put("candidateName", name);
        candidateObject.put("positionApplied", positionApplied);
        candidateObject.put("candidateStatus", candidateStatus);
        data.put(candidateId, candidateObject);
        storageHR.poorJarser.setRepositoryStrings(data);
        moveCandidate(candidateId, src.HR.src.candidateStatus.valueOf(candidateStatus));
        storageHR.poorJarser.writeToTextFile(filepath);
        storageHR.deleteFile(filepath);
    }

    /**
     * //TODO: add description, list of Folders
     * @param canStatus String name of the candidate folder to display.
     * @throws Exception Throws Exception if method failed to read through the given
     *                      path of a Folder
     */
    public void displayCandidatesByStatus(String canStatus) throws Exception {
        try {
            String statusDirName = canStatus.toUpperCase();

            Path statusDir = storageHR.getCandidateStoragePath(statusDirName);

            //TODO: extract into method
            if(statusDir == null) {
                throw new NullPointerException("statusDir is null");
            }

            if(!Files.exists(statusDir) || !Files.isDirectory(statusDir)) {
                System.out.println("No candidates found with status: " + canStatus);
                return; // Add return to exit the method if directory doesn't exist
            }

            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(statusDir, "*.txt")) {
                boolean candidatesFound = false;
                for (Path path : directoryStream) {
                    candidatesFound = true;
                    storageHR.loadFileAndPrint(path.toString()); // Pass full path as string
                }
                if(!candidatesFound) {
                    System.out.println("No candidates found with status: " + canStatus);
                }
            }
        } catch (Exception e) {
            throw new Exception("displayCandidatesByStatus failed: \n" + e.getMessage(), e);
        }
    }
}
