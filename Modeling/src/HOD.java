package Modeling.src;

import HR.src.Department;
import HR.src.Employee;
import Modeling.src.interfaces.IHOD;

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

        Event event = new Event();
        event.addEvent(1,models, type, celebrity, collab);
        requestAdvertisement(event);
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
