public class employeeHiringSystem {
    public static void main(String[] args) {
        // Add a new employee
        Employee newEmployee = new Employee("E123", "Alice Johnson", "Engineering", "Software Engineer", "Active");
        System.out.println("Employee '" + newEmployee.name + "' in department: " + newEmployee.department);

        // Add a new candidate
        Candidate newCandidate = new Candidate("C456", "Bob Smith", "Data Analyst");
        System.out.println("Candidate '" + newCandidate.candidateId + "' with name: " + newCandidate.name);
    }
}