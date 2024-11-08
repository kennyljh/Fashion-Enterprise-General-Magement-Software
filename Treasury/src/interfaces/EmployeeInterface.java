package Treasury.src.interfaces;

import Treasury.src.Employee;

public interface EmployeeInterface {

	Employee getEmployeeDetails();
	
	double getSalary();
	
	void setSalary(double newSalary);
}
