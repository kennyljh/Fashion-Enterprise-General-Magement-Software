package src.HR.src.interfaces;

public interface IEmployee {
    String getName();
    String getEmployeeID();
    String getDepartment();
    String getPosition();
    String getEmployementStatus();
    String getSalary();

    @Override
    String toString();
}
