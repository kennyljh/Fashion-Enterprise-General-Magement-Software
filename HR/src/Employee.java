/**
 * @author Sam Gumm
 */
class Employee {
    String employeeId;
    String name;
    Department department;
    String position;
    String employmentStatus;
    int salary;

    Employee(String employeeId, String name, Department department, String position, String employmentStatus, int salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.position = position;
        this.employmentStatus = employmentStatus;
        this.salary = salary;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Department: %s, Position: %s, Status: %s, Salary: %d", 
                employeeId, name, department, position, employmentStatus, salary);
    }
}
