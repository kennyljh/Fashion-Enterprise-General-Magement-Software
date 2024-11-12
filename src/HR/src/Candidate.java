package src.HR.src;

import src.HR.src.interfaces.ICandidate;

/**
 * @author Sam Gumm
 */

public class Candidate implements ICandidate {
    String candidateId;
    String name;
    String positionApplied;
    String status;

    /**
     * @param candidateId
     * @param name
     * @param positionApplied
     */
    public Candidate(String candidateId, String name, String positionApplied, String status) {
        this.candidateId = candidateId;
        this.name = name;
        this.positionApplied = positionApplied;
        this.status = status;
    }

    /**
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @return candidateId
     */
    @Override
    public String getCandidateId() {
        return candidateId;
    }

    /**
     *
     * @return positionApplied
     */
    @Override
    public String getPositionApplied() {
        return positionApplied;
    }

    /**
     *
     * @return status
     */
    @Override
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    @Override
    public void setStatus(String status) {
        this.status = status;
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