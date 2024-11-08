package Modeling.src.interfaces;

import HR.src.Employee;
import Modeling.src.Fitting;

import java.time.LocalDateTime;

public interface IManager {
    Fitting requestFitting(Employee model, LocalDateTime date);

    Boolean scheduleModel(Employee model);

    Employee[] getModels();
}
