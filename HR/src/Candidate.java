package HR.src;

class Candidate {
    String candidateId;
    String name;
    String positionApplied;
    String status;

    Candidate(String candidateId, String name, String positionApplied) {
        this.candidateId = candidateId;
        this.name = name;
        this.positionApplied = positionApplied;
        this.status = "Applied";
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Position Applied: %s, Status: %s", 
                candidateId, name, positionApplied, status);
    }
}