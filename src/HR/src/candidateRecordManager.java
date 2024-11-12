package src.HR.src;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages Candidate records, allowing for adding, removing, updating, and displaying Candidate.
 * @author Sam Gumm
 */
public class candidateRecordManager {
    private final List<Candidate> candidatesList = new ArrayList<>();



    /**
     * Add a new candidate
     * @param candidate
     */
    public void addCandidate(Candidate candidate) {
        candidatesList.add(candidate);
    }

    /**
     * Remove a candiate by ID
     * @param candidateID
     * @throws Exception
     */
    public void removeCandidate(String candidateID) throws Exception {
        if (candidatesList.isEmpty()) {
            throw new Exception("candidate list is empty");
        }

        boolean candidateRemoved = candidatesList.removeIf(candidate -> candidate.getCandidateId().equals(candidateID));

        if (!candidateRemoved) {
            throw new Exception("candidate does not exist: " + candidateID);
        }
    }

    /**
     * Update candidate record by ID
     * @param candidateId
     * @param name
     * @param positionApplied
     * @param status
     */
    public void updateCandidate(String candidateId, String name, String positionApplied, String status) {
        for (Candidate emp : candidatesList) {
            if (emp.getCandidateId().equals(candidateId)) {
                emp.candidateId = candidateId;
                emp.name = name;
                emp.positionApplied = positionApplied;
                emp.status = status;
                return;
            }
        }
        System.out.println("candidate not found: " + candidateId);
    }

    /**
     * Display all candidate records
     */
    public void displayRecords() {
        candidatesList.forEach(System.out::println);
    }
}
