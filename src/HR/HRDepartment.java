package src.HR;

import src.App;
import src.HR.src.*;

import java.util.Scanner;

public class HRDepartment {

    public void start() throws Exception {
        fileStorageHR storage = new fileStorageHR();
        if(storage.getFilepath().equals(storage.getDefaultFilepath())) {
            System.out.println("Using default filepath...");
        }
        else {
            System.out.println("Using alternative filepath: " + storage.getFilepath());
        }
        employeeRecordManager empHandler = new employeeRecordManager(storage);
        candidateRecordManager canHandler = new candidateRecordManager();

        boolean loop = true;

        //start of user interaction
        while(loop) {
            System.out.println("Welcome to the HR Department!");
            System.out.println("Please choose from these options:");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Retrieve Employee");
            System.out.println("4. Update Employee");


            System.out.println("5. Add Candidate");
            System.out.println("6. Remove Candidate");
            System.out.println("7. Display All Employees");
            System.out.println("8. Display All Employees By Department");
            System.out.println("9. Display All Candidates");
            System.out.println("10. nothing here rn");

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
                    System.out.println("Enter employee ID: ");
                    String markedEmployeeID = input.next();
                    empHandler.retrieveEmployeeByEmployeeID(markedEmployeeID);
                    System.out.println("Employee retrieved successfully! Returning to menu...\n\n\n\n");
                    break;

                case 4: //update employee
                    System.out.println("Enter employee ID: ");
                    String updateEmployeeID = input.next();
                    empHandler.updateEmployee(updateEmployeeID);
                    System.out.println("Employee updated successfully! Returning to menu...\n\n\n\n");
                    break;

                case 5: //add candidate
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

                case 6: //remove candidate
                    System.out.println("Enter candidate ID to be removed: ");
                    String candidateID = input.next();
                    canHandler.removeCandidate(candidateID);
                    System.out.println("Candidate removed successfully! Returning to menu...\n\n\n\n");
                    break;

                case 7: //list all employees
                    System.out.println("List of all Employees: ");
                    empHandler.displayRecords();
                    System.out.println("END OF LIST, returning to menu...\n\n\n\n");
                    break;

                case 8: //list employees by department
                    System.out.println("Enter Department Name: ");
                    String departmentName = input.next();
                    empHandler.displayEmployeesByDepartment(Department.valueOf(departmentName));
                    System.out.println("\nReturning to menu...\n\n\n\n");
                    break;

                case 9: //list all candidates
                    System.out.println("List of All Candidates: ");
                    canHandler.displayRecords();
                    System.out.println("END OF LIST, returning to menu...\n\n\n\n");
                    break;

                case 10:
                    //TODO
                    System.out.println("\nReturning to menu...\n\n\n\n");
                    break;

                case 0:
                    loop = false;
                    System.out.println("EXITING...");
                    App.prompt();
                    break;
            }
        }
    }

    public Employee getEmployee(String id) {
        //TODO: Insert get employee by id from file stuff here
        return null;
    }

    public Employee getEmployee(Department department) {
        //TODO: Insert get employee by department from file stuff here
        return null;
    }

    public Employee getEmployee(Department department, String name) {
        //TODO: Insert get employee by department and name from file stuff here
        return null;
    }
}
