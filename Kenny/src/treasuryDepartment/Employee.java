package treasuryDepartment;

public class Employee implements EmployeeInterface{

	private String employeeID, name, position, department;
	private double workHours, benefits, overtime, salary;
	
	public Employee() {};
	
	public Employee(String employeeID, String name, String position, String department,
					double workHours, double benefits, double overtime, double salary) {
		
		this.employeeID = employeeID;
		this.name = name;
		this.position = position;
		this.department = department;
		this.workHours = workHours;
		this.benefits = benefits;
		this.overtime = overtime;
		this.salary = salary;
	}
	
	@Override
	public Employee getEmployeeDetails() {

		return this;
	}

	@Override
	public double getSalary() {
		// TODO Auto-generated method stub
		return salary;
	}

	@Override
	public void setSalary(double newSalary) {
		this.salary = newSalary;
	}

}
