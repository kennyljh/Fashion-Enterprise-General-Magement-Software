package src.HR.src;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            //
                Time: 1215
                Candidate: Someone else
                Interviewer: Someone
                Notes: blah blh blag
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
        File folder = folderPathSchedules.toFile();
        String interviewer = input.nextLine();
        System.out.println("Please ensure this is the correct Interviewer y/n: " + interviewer);
        answer = input.nextLine();
        if (answer.equals("n") || answer.equals("N")) {
            System.out.println("Please choose Interviewer to assign:");
            interviewer = input.nextLine();
        }
        data += "Interviewer: " + interviewer + "\n";

        System.out.println("generating ID...");

        //checking to make sure folder is there
        int idCounter = 0;
        System.out.println("idCounter: " + idCounter);
        Path filePath;
        try {
            filePath = Paths.get(folderPathIDs.toString(), "idFile.txt");
        } catch (Exception e) {
            throw new FileNotFoundException("File not found");
        }
        System.out.println(filePath);

        //read integer then increment and write to file
        try(BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line = br.readLine();
            System.out.println(line);
            idCounter = Integer.parseInt(line);
            System.out.println("idCounter" + idCounter);
            CharSequence chars = ++idCounter + "";
            System.out.println(chars);
            Files.writeString(filePath, chars);
        }

        //Save file to scheduleStorage TODO: clean up
        File interview = new File(folderPathSchedules.toFile(), interviewTime + "_" + idCounter + ".txt");
        Files.writeString(interview.toPath(), data);
        if (interview.createNewFile()) {
            System.out.println("File Created");
        }
        String content = new String(Files.readAllBytes(interview.toPath()));
        System.out.println("Total Space: " + content.length() + " Bytes");
        System.out.println("folderPathSchedules: " + folderPathSchedules);
        System.out.println("Interview Time: " + interviewTime);
        System.out.println(interview.exists());
        System.out.println(data);
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
        File folder = folderPathIDs.toFile();
        if (!folder.exists()) {
            System.out.println("Folder does not exist");
        }
        Path interviewPath;
        String[] data;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPathIDs)) {
            for (Path path : directoryStream) {
                if (path.getFileName().toString().contains("_" + interviewID)) {
                    interviewPath = path.resolve(interviewID);
                    /*
                        TODO:
                            - read from file
                            - insert into string array
                            - alter with user input
                            - write to file
                     */
                }
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

    public void printInterviewNotes() {}

    public void printInterview() {}

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
