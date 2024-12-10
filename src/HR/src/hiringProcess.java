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

    //TODO: Works
    public void createInterview() throws IOException {
        /*
        TODO:
            - alter this to create a folder for an interviewer
                and upon assigning a card to them, either the folder exists and it is deposited there
                or it creates a new folder after prompting the user to confirm that the chosen interviewer is correct
         */
        String data = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter Interview Time (HH:MM)");
        String interviewTime = input.nextLine();
        System.out.println("Please ensure this is the correct time y/n: " + interviewTime);
        String answer = input.nextLine();
        if (answer.equals("n") || answer.equals("N")) {
            //Currently needs to be this way otherwise pulling data will not work correctly (NO SEMICOLONS)
            System.out.println("Please enter Interview Time (HHMM)");
            interviewTime = input.nextLine();
        }
        data += "Interview Time: " + interviewTime + "\n";


        //candidate name
        System.out.println("Please enter Candidate Name (First Last):");
        String candidateName = input.nextLine().strip();
        System.out.println("Please ensure this is the correct Candidate y/n: " + candidateName);
        answer = input.nextLine();
        if (answer.equals("n") || answer.equals("N")) {
            System.out.println("Please enter Candidate Name (First Last):");
            candidateName = input.nextLine();
        }
        data += "Candidate Name: " + candidateName + "\n";


        //assign interviewer
        System.out.println("Please choose Interviewer to assign:");
        //File folder = folderPathSchedules.toFile();
        String interviewer = input.nextLine();
        System.out.println("Please ensure this is the correct Interviewer y/n: " + interviewer);
        answer = input.nextLine();
        if (answer.equals("n") || answer.equals("N")) {
            System.out.println("Please choose Interviewer to assign:");
            interviewer = input.nextLine();
        }
        data += "Interviewer: " + interviewer + "\n";

        //add notes
        System.out.println("PLease enter notes: ");
        String notes = input.nextLine();
        System.out.println("Please ensure this is correct (y/n): " + notes);
        answer = input.nextLine();
        if (answer.equals("n") || answer.equals("N")) {
            System.out.println("Please choose Interviewer to assign:");
            notes = input.nextLine();
        }
        data += "Notes: " + notes + "\n";

        System.out.println("generating ID...");

        //checking to make sure folder is there
        int idCounter = 0;
//        System.out.println("idCounter: " + idCounter);
        Path filePath;
        try {
            filePath = Paths.get(folderPathIDs.toString(), "idFile.txt");
        } catch (Exception e) {
            throw new FileNotFoundException("File not found");
        }
//        System.out.println(filePath);

        //read integer then increment and write to file
        try(BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line = br.readLine();
//            System.out.println(line);
            idCounter = Integer.parseInt(line);
//            System.out.println("idCounter" + idCounter);
            CharSequence chars = ++idCounter + "";
//            System.out.println(chars);
            Files.writeString(filePath, chars);
        }

        //Save file to scheduleStorage TODO: clean up
        File interview = new File(folderPathSchedules.toFile(), interviewTime + "_" + idCounter + ".txt");
        Files.writeString(interview.toPath(), data);
        if (interview.createNewFile()) {
            System.out.println("File Created");
        }
//        String content = new String(Files.readAllBytes(interview.toPath()));
//        System.out.println("Total Space: " + content.length() + " Bytes");
//        System.out.println("folderPathSchedules: " + folderPathSchedules);
//        System.out.println("Interview Time: " + interviewTime);
//        System.out.println(interview.exists());
//        System.out.println(data);
    }

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
        //Take user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter interview ID to edit:");
        String interviewID = scanner.nextLine();
        System.out.println("Please ensure this is the correct time y/n: " + interviewID);
        String answer = scanner.nextLine();
        //TODO: replace with while loop
        if (answer.equals("n") || answer.equals("N")) {
            System.out.println("Please enter Candidate Name (First Last):");
            interviewID = scanner.nextLine();
        }



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
                //TODO prolly need to rework to iterate through the file name until after the _ for ID
                if (path.getFileName().toString().contains(interviewID)) {
                    filePath = path;
                    System.out.println("Found file: " + path.getFileName().toString());
//                    System.out.println("interviewPath: " + path);
                    //System.out.println(Files.exists(path) ? "Yes" : "No");
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
            String operation = scanner.nextLine();

            if (operation.equals("E")) {
                System.out.println("Choose what information to edit: \n[I]nterviewer Assigned\n[C]andidate Name\n[N]otes");
                String userInput = scanner.nextLine();
                switch (userInput) {
                    case "I" -> {
                        System.out.println("Please enter new Interviewer to assign: ");
                        String newInterviewer = scanner.nextLine();
                        System.out.println("Is " + newInterviewer + " correct? (y/n): " );
                        answer = scanner.nextLine();
                        if(answer.equals("n") || newInterviewer.equals("N")) {
                            System.out.println("Please enter new Interviewer to assign: ");
                            newInterviewer = scanner.nextLine();
                        }
                        for (String[] datum : data) {
                            for (int j = 0; j < datum.length; j++) {
                                if (datum[j].equals("Interviewer")) {
//                                    System.out.println("Found Interviewer section at: " + j);
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
                        String newCandidate = scanner.nextLine();
                        System.out.println("Is " + newCandidate + " correct? (y/n): " );
                        answer = scanner.nextLine();
                        if(answer.equals("n") || newCandidate.equals("N")) {
                            System.out.println("Please enter new Candidate name: ");
                            newCandidate = scanner.nextLine();
                        }
                        for (String[] datum : data) {
                            for (int j = 0; j < datum.length; j++) {
                                if (datum[j].equals("Candidate")) {
//                                    System.out.println("Found Candidate section at: " + j);
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
                        String newNotes = scanner.nextLine();
                        System.out.println("Is '" + newNotes + "' correct? (y/n): " );
                        answer = scanner.nextLine();
                        if(answer.equals("n") || newNotes.equals("N")) {
                            System.out.println("Please enter new notes: ");
                            newNotes = scanner.nextLine();
                        }
                        for (String[] datum : data) {
                            for (int j = 0; j < datum.length; j++) {
                                if (datum[j].equals("Notes")) {
//                                    System.out.println("Found notes section at: " + j);
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
                        break;
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
