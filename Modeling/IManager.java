package Modeling;

import HR.src.Employee;

public interface IManager {
    Fitting requestFitting(Employee Model);

    Boolean scheduleModel(Employee Model);
}

class Manager implements IManager {

    @Override
    public Fitting requestFitting(Employee Model) {
        return null;
    }

    @Override
    public Boolean scheduleModel(Employee Model) {
        return null;
    }
}