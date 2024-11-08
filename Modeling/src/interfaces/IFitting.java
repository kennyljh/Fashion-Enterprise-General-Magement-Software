package Modeling.src.interfaces;

import HR.src.Employee;
import Modeling.src.Fitting;

import java.time.LocalDateTime;

public interface IFitting {
    Fitting addFitting(int id, Employee model, String garment, LocalDateTime date);

    void endFitting();
}
