package src.Modeling.src;

import src.HR.src.Department;
import src.HR.src.Employee;
import src.Modeling.src.interfaces.IHOD;

import java.util.Scanner;

public class HOD implements IHOD {
    Employee employeeInfo;
    Manager[] managers;

    public HOD() {
        this.employeeInfo = new Employee("1", "Name", Department.MODELING, "HOD", "Employed", 10000);
    }

    Manager manager = new Manager();

    @Override
    public void haveEvent(Boolean type, String celebrity, Boolean collab) {
        Employee[] models = manager.getModels();

        Event event = new Event(1,models, type, celebrity, collab);
//        requestAdvertisement(event);

        System.out.println(event.toString());
        System.out.println("\nEnd Event?");
        Scanner scanner = new Scanner(System.in);
        String x = scanner.next();
        if(x.equals("Y")) {
            event.endEvent();
        }
    }

    @Override
    public Boolean requestAdvertisement(Event event) {
        return false;
    }

    @Override
    public Boolean requestContract(String celebrity) {
        return null;
    }

    @Override
    public Boolean requestCollab(String brand) {
        return null;
    }
}
