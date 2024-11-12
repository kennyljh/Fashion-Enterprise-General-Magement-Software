package src.Modeling.src;

import src.HR.src.Department;
import src.HR.src.Employee;
import src.Modeling.src.interfaces.IManager;

import java.time.LocalDateTime;

public class Manager implements IManager {

    Employee employeeInfo;
    Employee[] models;

    public Manager() {
        models = new Employee[4];
        for (int i = 0; i < 4; i++) {
            models[i] = new Employee(Integer.toString(i), "Model" + i, Department.MODELING, "Model", "Employed", 10000);
        }
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
        return models;
    }
}