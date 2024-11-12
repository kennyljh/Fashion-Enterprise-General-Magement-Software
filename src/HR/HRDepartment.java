package src.HR;

import src.App;
import src.HR.src.*;

import java.util.Scanner;

public class HRDepartment {

    public void start() throws Exception {
        //initializing dummy data holders for local storage
        employeeRecordManager empHandler = new employeeRecordManager();
        candidateRecordManager canHandler = new candidateRecordManager();

        boolean loop = true;

        //start of user interaction
        while(loop) {
            System.out.println("Welcome to the HR Department!");
            System.out.println("Please choose from these options:");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Add Candidate");
            System.out.println("4. Remove Candidate");
            System.out.println("5. Display All Employees");
            System.out.println("6. Display All Employees By Department");
            System.out.println("7. Display All Candidates");
            System.out.println("8. Collate Departmental Salaries");
            System.out.println("0. Exit");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            switch (choice) {
                case 1:
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

                case 2:
                    System.out.println("Enter employee name: ");
                    String markedEmployee = input.next();
                    empHandler.removeEmployee(markedEmployee);
                    System.out.println("Employee removed successfully! Returning to menu...\n\n\n\n");
                    break;

                case 3:
                    System.out.println("Enter candidate name: ");
                    String candidateName = input.next();
                    System.out.println("Enter candidateId");
                    String candidateId = input.next();
                    System.out.println("Enter position candidate applied for: ");
                    String positionApplied = input.next();
                    System.out.println("Enter candidate status (i.e. accepted, failed...): ");
                    String candidateStatus = input.next();
                    Candidate newCandidate = new Candidate(candidateId, candidateName, positionApplied, candidateStatus);
                    canHandler.addCandidate(newCandidate);
                    System.out.println("Candidate added successfully! Returning to menu...\n\n\n\n");
                    break;

                case 4:
                    System.out.println("Enter candidate ID to be removed: ");
                    String candidateID = input.next();
                    canHandler.removeCandidate(candidateID);
                    System.out.println("Candidate removed successfully! Returning to menu...\n\n\n\n");
                    break;

                case 5:
                    System.out.println("List of all Employees: ");
                    empHandler.displayRecords();
                    System.out.println("END OF LIST, returning to menu...\n\n\n\n");
                    break;

                case 6:
                    System.out.println("Enter Department Name: ");
                    String departmentName = input.next();
                    empHandler.displayEmployeesByDepartment(Department.valueOf(departmentName));
                    System.out.println("\nReturning to menu...\n\n\n\n");
                    break;

                case 7:
                    System.out.println("List of All Candidates: ");
                    canHandler.displayRecords();
                    System.out.println("END OF LIST, returning to menu...\n\n\n\n");
                    break;

                case 8:
                    System.out.println("Enter Department Name: ");
                    String salariedDepartmentName = input.next();
                    System.out.println("Total Cost of Employees for " + salariedDepartmentName + ": " + empHandler.collateSalariesByDepartment(Department.valueOf(salariedDepartmentName)));
                    System.out.println("\nReturning to menu...\n\n\n\n");
                    break;

                case 0:
                    loop = false;
                    System.out.println("EXITING...");
                    App.prompt();
//                    break;
            }
        }
    }
}
