package src.Modeling.src.interfaces;

import src.HR.src.Employee;
import src.Modeling.src.Fitting;

import java.time.LocalDateTime;

public interface IFitting {
    Fitting addFitting(int id, Employee model, String garment, LocalDateTime date);

    void endFitting();
}
