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

    Fitting (int id, Employee model, String garment, LocalDateTime date) {
        this.id = id;
        this.model = model;
        this.garment = garment;
        this.date = date;
        this.completionStatus = false;
    }

    @Override
    public void endFitting() {
        this.completionStatus = true;
        System.out.println(this.toString());
    }

    public String toString() {
        String str = "\nFitting: ";
        str += "\nModel: " + this.model.toString();
        str += "\nGarment: " + this.garment +
                "\nDate: " + this.date +
                "\nCompletion Status: " + this.completionStatus;
        return str;
    }
}
