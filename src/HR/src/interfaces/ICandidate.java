package src.HR.src.interfaces;

public interface ICandidate {
    String getName();
    String getCandidateId();
    String getPositionApplied();
    String getStatus();
    void setStatus(String status);

    @Override
    String toString();
}
