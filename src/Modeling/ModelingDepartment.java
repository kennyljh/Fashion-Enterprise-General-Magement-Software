package src.Modeling;

import src.App;

import src.HR.src.Department;
import src.HR.src.Employee;
import src.Modeling.src.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ModelingDepartment {
    HOD hod = new HOD();
    Manager manager = new Manager();

    public void start() throws Exception {
        System.out.println("Please choose an action you want to take: \n 1: haveEvent \n 2: scheduleFitting\n 3:Exit");
        Scanner s = new Scanner(System.in);
        int c = s.nextInt();

        switch (c){
            case 1:
                Boolean type = false;
                Boolean collab = false;
                System.out.println("What type of event?\n1:Photoshoot\n2:Fashion Show");
                int x = s.nextInt();
                switch (x) {
                    case 1:
                        type = true;
                }
                System.out.println("Type a name of a Celebrity (enter for none)");
                String celebrity = s.next();
                System.out.println("Will you collab with a brand? (Y/N)");
                String ch = s.next();
                switch (ch) {
                    case "Y":
                        collab = true;
                }

                hod.haveEvent(type, celebrity, collab);
                break;
            case 2:
                System.out.println("What model? (put a name)");
                String model = s.next();
                System.out.println("What month (1-12)");
                int month = s.nextInt();
                System.out.println("What day (1-31)");
                int day = s.nextInt();
                System.out.println("What hour? (1-24)");
                int hour = s.nextInt();
                LocalDateTime date = LocalDateTime.of(2024, month, day, hour, 0);

//                Employee e = App.hrDepartment.getEmployee();
                Employee e = new Employee("1", model, Department.MODELING, "Model", "Employed", 10000);

                manager.requestFitting(e, date);
                break;
            case 3:
                App.prompt();
        }
    }
}
