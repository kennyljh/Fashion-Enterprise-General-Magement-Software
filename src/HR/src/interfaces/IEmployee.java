package src.HR.src.interfaces;

import src.HR.src.Department;

public interface IEmployee {
    String getName();
    String getEmployeeID();
    Department getDepartment();
    String getPosition();
    String getEmployementStatus();
    int getSalary();

    @Override
    String toString();
}
