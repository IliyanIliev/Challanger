package entities;

public class ChallengeType {
    private int challengeTypeID;
    private String challengeTypeName;
    private boolean locked;
    private int background;

    public int getChallengeTypeID() {
        return challengeTypeID;
    }

    public void setChallengeTypeID(int challengeTypeID) {
        this.challengeTypeID = challengeTypeID;
    }

    public String getChallengeTypeName() {
        return challengeTypeName;
    }

    public void setChallengeTypeName(String challengeTypeName) {
        this.challengeTypeName = challengeTypeName;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
