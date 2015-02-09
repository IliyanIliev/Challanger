package entities;


public class AssignedChallenge {
    private int assignedChallengeID;
    private int challengeID;
    private int userID;
    private boolean completed;
    private boolean approved;
    private boolean rejected;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(int challengeID) {
        this.challengeID = challengeID;
    }

    public int getAssignedChallengeID() {
        return assignedChallengeID;
    }

    public void setAssignedChallengeID(int assignedChallengeID) {
        this.assignedChallengeID = assignedChallengeID;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }
}
