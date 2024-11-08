package Modeling.src;

import HR.src.Employee;
import Modeling.src.interfaces.IManager;

import java.time.LocalDateTime;

public class Manager implements IManager {

    Employee employeeInfo;

    public Manager() {

    }

    @Override
    public Fitting requestFitting(Employee model, LocalDateTime date) {
        Fitting fitting = new Fitting();

        fitting.addFitting(1, model, "Clothing", date);
        return fitting;
    }

    @Override
    public Boolean scheduleModel(Employee Model) {
        return null;
    }

    @Override
    public Employee[] getModels() {
        return new Employee[0];
    }
}