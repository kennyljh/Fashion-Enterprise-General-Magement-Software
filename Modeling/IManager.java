package Modeling;

import HR.src.Employee;

import java.time.LocalDateTime;

public interface IManager {
    Fitting requestFitting(Employee model, LocalDateTime date);

    Boolean scheduleModel(Employee model);

    Employee[] getModels();
}

class Manager implements IManager {

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