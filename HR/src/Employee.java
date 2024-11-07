class Employee {
    String employeeId;
    String name;
    String department;
    String position;
    String employmentStatus;

    Employee(String employeeId, String name, String department, String position, String employmentStatus) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.position = position;
        this.employmentStatus = employmentStatus;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Department: %s, Position: %s, Status: %s", 
                employeeId, name, department, position, employmentStatus);
    }
}
