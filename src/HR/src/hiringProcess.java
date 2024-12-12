package src.HR.src;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class hiringProcess {
    /*
    TODO:
     - set up the hiring process
     - set up the folder access
     - set up the folder storage system
        - have txt files that will be stored into the corresponding day of the week
        - the txt files will store what current candidates are going to be interviewed that day
        - ...
    Thought process:
        Need to have counter for interview ID
        -> have this stored in a txt file somewhere, everytime the counter increments, the txt counter is
            also incremented;
        Have interviewer folders, and ability to create and remove the folders
        each folder has days of the week as folders inside of them
        each day of the week has txt files that represent time slots, when a interview is scheduled,
            it gets saved as, for example a 12:15 slot, (maybe have it be appended to the InterviewID 000_1215

            1215_1.txt
            //TODO: INITIALIZE THE CARDS EXACTLY LIKE THIS
            //
                Time: 1215
                Candidate: Someone else
                Interviewer: Someone
                Notes: blank
                                          //

        the operator can choose to see all timeslots currently created in a print out fashion, with days as headers
        they can also type in the timeslot (maybe have this changed to candidate name or something when sent to complete)
        there is a completed interviews folder where the user opens up a timeslot, and then chooses to send it to complete
            this opens up the option for them to add in any notes that they may have, and then saves the candidate in
            this format in the completed folder
            someone.txt
            //
               Candidate Name: Something
               Interviewer: Something else
               Notes: //something here idk
               Recommendation: Be sent to such and such department as this position idk
                                                                                          //
        the user should be able to get full or individual print outs of all folders available, including their contents

     */

    /*
    STEPS:
    - create interview from info
    - assign to interviewer
    - set timeslot
    - repeat until finished
    > interview is had
    > interviewer records notes about interview
    - insert notes of interviewer into the interview txt file
    - move the txt file to Completed
     */

    /*
    Necessities:
    - See all interviews arranged by interviewer
    - See only one interviewer's timeslots
    - See one timeslot's internals
    - Global counter for interviewID
     */

    private final Path folderPathSchedules = Paths.get("src", "HR", "repository", "scheduleStorage");
    private final Path folderPathIDs = Paths.get("src", "HR", "repository", "scheduleStorage", "idStorage");
    valueHandling valueHandler = new valueHandling();
    /**
     *
     * @throws IOException if File is invalid
     */
    public void createInterview() throws IOException {
        /*
        TODO:
            - alter this to create a folder for an interviewer
                and upon assigning a card to them, either the folder exists and it is deposited there
                or it creates a new folder after prompting the user to confirm that the chosen interviewer is correct
         */
        String interviewTime;
        String candidateName;
        String data = "";
        String interviewer;
        String notes;
        System.out.println("Please enter Interview Time (HH:MM)");
        interviewTime = valueHandler.inputValidator(true);


        //candidate name
        System.out.println("Please enter Candidate Name (First Last):");
        candidateName = valueHandler.inputValidator(true);
        data += "Candidate Name: " + candidateName + "\n";


        //assign interviewer
        System.out.println("Please choose Interviewer to assign:");
        //File folder = folderPathSchedules.toFile();
        interviewer = valueHandler.inputValidator(true);
        data += "Interviewer: " + interviewer + "\n";

        //add notes
        System.out.println("Please enter notes: ");
        notes = valueHandler.inputValidator(true);
        data += "Notes: " + notes + "\n";

        System.out.println("generating ID...");

        //checking to make sure folder is there
        int idCounter;
        Path filePath;
        try {
            filePath = Paths.get(folderPathIDs.toString(), "idFile.txt");
        } catch (Exception e) {
            throw new FileNotFoundException("File not found");
        }

        //read integer then increment and write to file
        try(BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line = br.readLine();
            idCounter = Integer.parseInt(line);
            CharSequence chars = ++idCounter + "";
            Files.writeString(filePath, chars);
        }

        //Save file to scheduleStorage TODO: clean up
        File interview = new File(folderPathSchedules.toFile(), interviewTime + "_" + idCounter + ".txt");
        Files.writeString(interview.toPath(), data);
        if (interview.createNewFile()) {
            System.out.println("File Created");
        }
    }

    /**
     *
     * @throws IOException
     */
    public void editInterview() throws IOException {
        /* TODO
            - trawl through scheduleStorage until file is found
            - take user input to replace:
                1. Interview Time
                2. Candidate Name
                3. Interviewer on File
                4. Interview Notes
            - STEPS:
                - read file and store to String
                - iterate through String and change marked section
                - replace file with new string
         */
        String interviewID;
        String operation;
        String userInput;
        String newInterviewer;
        String newCandidate;
        String newNotes;

        //Take user input
        System.out.println("Please enter interview ID to edit:");
        interviewID = valueHandler.inputValidator(true);

        //search for file and read to string
        //TODO: extract into a method that returns data()[]
        File folder = folderPathIDs.toFile();
        if (!folder.exists()) {
            System.out.println("Folder does not exist");
        }
        Path filePath = null;
        List<String[]> data = new ArrayList<>();
        String[] payload = null;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPathSchedules)) {
            for (Path path : directoryStream) {
                //TODO prolly need to rework to iterate through the file name until after the _ for ID with regex
                if (path.getFileName().toString().contains(interviewID)) {
                    filePath = path;
                    System.out.println("Found file: " + path.getFileName().toString());
                    Scanner fileScanner = new Scanner(path);
                    while (fileScanner.hasNextLine()) {
                        //TODO replace with HashMap prolly
                        assert false;
                        payload = fileScanner.nextLine().split(": ");
                        System.out.println(Arrays.toString(payload));
                        data.add(payload);
                    }
                    break;
                }
            }
        }

        //TODO: this will eventually call the above method and use the data()[] it returns
        while(true) {
            System.out.println("Please choose operation: [E]dit, [Q]uit:");
            operation = valueHandler.inputValidator(false);

            if (operation.equals("E")) {
                System.out.println("Choose what information to edit: \n[I]nterviewer Assigned\n[C]andidate Name\n[N]otes");
                userInput = valueHandler.inputValidator(false);
                switch (userInput) {
                    case "I" -> {
                        System.out.println("Please enter new Interviewer to assign: ");
                        newInterviewer = valueHandler.inputValidator(true);
                        for (String[] datum : data) {
                            for (int j = 0; j < datum.length; j++) {
                                if (datum[j].equals("Interviewer")) {
                                    int temp = j;
                                    datum[++temp] = newInterviewer;
                                    System.out.println(datum[j + 1]);
                                    break;
                                }
                            }
                        }
                        assert filePath != null;
                        System.out.println("Started the writing process");
                        StringBuilder newPayload = new StringBuilder();
                        for (String[] datum : data) {
                            for (String s : datum) {
                                newPayload.append(s).append(" ");
                                System.out.println(s);
                            }
                            newPayload.append("\n");
                        }
                        System.out.println("Finished writing process: " + newPayload);
                        Files.writeString(filePath, newPayload.toString());
                    }

                    case "C" -> {
                        System.out.println("Please enter new Candidate name: ");
                        newCandidate = valueHandler.inputValidator(true);
                        for (String[] datum : data) {
                            for (int j = 0; j < datum.length; j++) {
                                if (datum[j].equals("Candidate")) {
                                    int temp = j;
                                    datum[++temp] = newCandidate;
                                    System.out.println(datum[j + 1]);
                                    break;
                                }
                                else {
                                    System.out.println("Was not able to find Candidate section...");
                                }
                            }
                        }
                        assert filePath != null;
                        System.out.println("Started the writing process");
                        String newPayload = "";
                        for (String[] datum : data) {
                            for (int j = 0; j < datum.length; j++) {
                                newPayload += datum[j] + " ";
                                System.out.println(datum[j].toString());
                            }
                            newPayload += "\n";
                        }
                        System.out.println("Finished writing process: " + newPayload);
                        Files.writeString(filePath, newPayload);
                    }
                    case "N" -> {
                        System.out.println("Please enter new notes: ");
                        newNotes = valueHandler.inputValidator(true);
                        for (String[] datum : data) {
                            for (int j = 0; j < datum.length; j++) {
                                if (datum[j].equals("Notes")) {
                                    int temp = j;
                                    datum[++temp] = newNotes;
                                    System.out.println(datum[j + 1]);
                                    break;
                                }
                            }
                        }
                        assert filePath != null;
                        System.out.println("Started the writing process");
                        String newPayload = "";
                        for (String[] datum : data) {
                            for (int j = 0; j < datum.length; j++) {
                                newPayload += datum[j] + " ";
                                System.out.println(datum[j].toString());
                            }
                            newPayload += "\n";
                        }
                        System.out.println("Finished writing process: " + newPayload);
                        Files.writeString(filePath, newPayload);
                    }
                    default -> {
                    }
                }
            }

            else if (operation.equals("Q")) {
                break;
            }

            else {
                System.out.println("Incorrect input...");
            }

        }

        //take more user input




    }

    public void recordInterviewNotes() {
        /* TODO
            - Find given file name
            - Append to file contents:
                //
                    <InterviewerName>'s Notes:
                        //insert something here
                                .
                                .
                                .
                                                //
            - Save the file
            - the file should be moved to complete after this
         */
    }

    private void moveInterviewToComplete(String interviewID) {
        /* TODO
            - takes interviewID from user, finds the txt file, then moves it to the completed folder after:
                - changing the name to be the candidate's name appended by interviewID
                Everything else related to the handover process will be accomplished by other methods
         */
    }

    public void printInterviewNotes() {

    }

    /**
     *
     * @param interviewID
     * @throws IOException
     */
    public void printInterview(String interviewID) throws IOException {
        System.out.println("Interview ID: " + interviewID);
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPathSchedules)) {
            for (Path file : directoryStream) {
                String fileName = file.getFileName().toString();
                if (fileName.matches(".*_" + interviewID + ".txt")) {
                    Files.lines(file).forEach(System.out::println);
                    break;
                }
            }
//            for (Path statusDir : directoryStream) {
//                if (Files.isDirectory(statusDir)) { // Ensure it's a directory
//                    System.out.println("Executing first if statement from statusDir: " + statusDir);
//                    try (DirectoryStream<Path> filesStream = Files.newDirectoryStream(statusDir)) {
//                        for (Path file : filesStream) {
//                            System.out.println(file.toString());
//                            if (Files.isRegularFile(file)) { // Ensure it's a file
//                                String fileName = file.getFileName().toString();
//                                System.out.println(fileName);
//                                if (fileName.matches(".*_" + interviewID + ".txt")) { // Match <timeslot>_<interviewID>.txt
//                                    // Print file content
//                                    System.out.println("Found file: " + fileName);
//                                    System.out.println("Content:");
//                                    Files.lines(file).forEach(System.out::println);
//                                    return;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
        }
        // If the file is not found
    }

    public void printAllInterviews() {}

    public void printAllInterviewsByInterviewer() {}

    private void incrementInterviewID() {
        /* TODO
            - Open save file for counter
            - if no save file, create one
            - check bytes of file
         */
    }


}
