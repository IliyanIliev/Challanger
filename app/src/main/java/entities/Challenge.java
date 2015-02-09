package entities;


public class Challenge {
    private int challengeID;
    private String challengeName;
    private boolean locked;
    private int points;
    private int challengeTypeID;

    public int getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(int challengeID) {
        this.challengeID = challengeID;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getChallengeTypeID() {
        return challengeTypeID;
    }

    public void setChallengeTypeID(int challengeTypeID) {
        this.challengeTypeID = challengeTypeID;
    }
}
