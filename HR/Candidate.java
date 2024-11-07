package HR;

/**
 * @author Sam Gumm
 */

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

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Position Applied: %s, Status: %s", 
                candidateId, name, positionApplied, status);
    }
}