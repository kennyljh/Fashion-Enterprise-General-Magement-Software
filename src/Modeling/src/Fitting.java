package src.Modeling.src;

import src.HR.src.Employee;
import src.Modeling.src.interfaces.IFitting;

import java.time.LocalDateTime;

public class Fitting implements IFitting {
    int id;
    Employee model;
    String garment;
    LocalDateTime date;
    Boolean completionStatus;

    @Override
    public Fitting addFitting(int id, Employee model, String garment, LocalDateTime date) {
        this.id = id;
        this.model = model;
        this.garment = garment;
        this.date = date;
        this.completionStatus = false;
        return null;
    }

    @Override
    public void endFitting() {
        this.completionStatus = true;
    }
}
