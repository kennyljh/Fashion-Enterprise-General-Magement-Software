package src.HR;

import src.App;
import src.HR.src.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class HRDepartment {

    public void start() throws Exception {
        fileStorageHR storage = new fileStorageHR();
        System.out.println("Current file path is: " + storage.getFilepath());
        employeeRecordManager empHandler = new employeeRecordManager(storage);
        candidateRecordManager canHandler = new candidateRecordManager(storage);

        boolean loop = true;

        //start of user interaction
        while(loop) {
            /*
            TODO:
                - Add in new logic capabilities
                - Replace with container system so i dont have to deal with the switch cases
                - Replace old logic with new updated methods
                - decide whether altering the file path can be done by user
             */
            System.out.println("Welcome to the HR Department!");
            System.out.println("Please choose from these options:");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Retrieve Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Display All Employees");
            System.out.println("6. Display All Employees By Department");

            System.out.println("7. Add Candidate");
            System.out.println("8. Remove Candidate");
            System.out.println("9. Retrieve Candidate");
            System.out.println("10. Update Candidate");
            System.out.println("11. Display All Candidates");
            System.out.println("12. Display All Candidates By Status");

            System.out.println("0. Exit");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            switch (choice) {
                case 1: //add employee
                    System.out.println("Enter employee name: ");
                    String name = input.next();
                    System.out.println("Enter employeeID: ");
                    String employeeId = input.next();
                    System.out.println("Enter employee department: ");
                    for(int i = 0; i < Department.values().length; i++) {
                        System.out.println(Department.values()[i].name());
                    }
                    Department department = Department.valueOf(input.next().toUpperCase());
                    System.out.println("Enter employee position: ");
                    String position = input.next();
                    System.out.println("Enter employee employment status (i.e. onboarding): ");
                    String employmentStatus = input.next();
                    System.out.println("Enter employee salary: ");
                    int salary = input.nextInt();
                    Employee employeeHolder = new Employee(employeeId, name, department, position, employmentStatus, salary);
                    empHandler.addEmployee(employeeHolder);
                    System.out.println("Employee added successfully! Returning to menu...\n\n\n\n");
                    break;

                case 2: //remove employee
                    System.out.println("Enter employee ID: ");
                    String markedEmployee = input.next();
                    empHandler.removeEmployee(markedEmployee);
                    System.out.println("Employee removed successfully! Returning to menu...\n\n\n\n");
                    break;

                case 3: //retrieve employee
                    System.out.println("Enter Employee ID: ");
                    String markedEmpID = input.next();
                    Path currentEmpFile = empHandler.findEmployeeFile(markedEmpID);
                    storage.loadFileAndPrint(currentEmpFile.toString());
                    System.out.println("Employee retrieved successfully! Returning to menu...\n\n\n\n");
                    break;

                case 4: //update employee //TODO working i think
                    System.out.println("Enter employee ID: ");
                    String updateEmployeeID = input.next();
                    empHandler.updateEmployee(updateEmployeeID);
                    System.out.println("Employee updated successfully! Returning to menu...\n\n\n\n");
                    break;

                case 5: //display all employees
                    System.out.println("List of All Employees: ");
                    for (Department department1 : Department.values()) {
                        empHandler.displayEmployeesByDepartment(department1);
                    }
                    System.out.println("END OF LIST, returning to menu...\n\n\n\n");
                    break;

                case 6: //display employees in a department
                    System.out.print("Please enter Department folder to list: ");
                    Department departmentFolder = Department.valueOf(input.next().toUpperCase());
                    System.out.println();
                    empHandler.displayEmployeesByDepartment(departmentFolder);
                    System.out.println("END OF LIST, returning to menu...\n\n\n\n");
                    break;

                case 7: //add candidate
                    System.out.println("Enter candidate name: ");
                    String candidateName = input.next();
                    System.out.println("Enter candidateId");
                    String candidateId = input.next();
                    System.out.println("Enter position candidate applied for: ");
                    String positionApplied = input.next();
                    System.out.print("Enter candidate status: \nApplied\nApproved\nHiring\nPending\nRejected\nEnter Here: ");
                    candidateStatus candidateStatus = src.HR.src.candidateStatus.valueOf(input.next().toUpperCase());
                    Candidate newCandidate = new Candidate(candidateId, candidateName, positionApplied, candidateStatus);
                    canHandler.addCandidate(newCandidate);
                    System.out.println("Candidate added successfully! Returning to menu...\n\n\n\n");
                    break;

                case 8: //remove candidate
                    System.out.println("Enter candidate ID to be removed: ");
                    String candidateID = input.next();
                    canHandler.removeCandidate(candidateID);
                    System.out.println("Candidate removed successfully! Returning to menu...\n\n\n\n");
                    break;

                case 9: //retrieve candidate by id
                    System.out.println("Enter Candidate ID: ");
                    String candidateID3 = input.next();
                    Path currentCandidateFile = canHandler.findCandidateFile(candidateID3);
                    storage.loadFileAndPrint(currentCandidateFile.toString());
                    break;

                case 10: //update candidate //TODO CHECK IT
                    System.out.println("Enter Candidate ID: ");
                    String candidateID2 = input.next();
                    canHandler.updateCandidate(candidateID2);
                    System.out.println("\nReturning to menu...\n\n\n\n");
                    break;

                case 11: //list all candidates
                    System.out.println("List of All Candidates: ");
                    for (src.HR.src.candidateStatus candidate : src.HR.src.candidateStatus.values()) {
                        canHandler.displayCandidatesByStatus(candidate + "\n");
                    }
                    System.out.println("END OF LIST, returning to menu...\n\n\n\n");
                    break;

                case 12: //list candidates by status
                    System.out.print("Please enter Status folder to list: ");
                    String statusFolder = input.next().toUpperCase();
                    System.out.println();
                    canHandler.displayCandidatesByStatus(statusFolder);
                    System.out.println("END OF LIST, returning to menu...\n\n\n\n");
                    break;

                case 0:
                    loop = false;
                    input.close();
                    System.out.println("EXITING...");
                    App.prompt(); //<---- Kicks you back to the main homepage
                    break;
            }
        }
    }

    public Employee getEmployee(String employeeID) throws IOException {
        //TODO
        return null;
    }

}
