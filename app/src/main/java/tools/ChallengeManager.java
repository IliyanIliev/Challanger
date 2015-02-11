package tools;

import java.util.ArrayList;
import java.util.List;

import entities.Challenge;
import entities.ChallengeType;


public class ChallengeManager {

    public static List<Challenge> allChallenges = new ArrayList<>();

    public static List<Challenge> getAllChallengeByChallengeType(String challengeTypeName) {

        int challengeTypeID = findChallengeTypeIdByName(challengeTypeName);
        List<Challenge> challenges = new ArrayList<>();
        for (Challenge challenge : allChallenges) {
            if (challenge.getChallengeTypeID() == challengeTypeID) {
                challenges.add(challenge);
            }
        }

        return challenges;
    }

    private static int findChallengeTypeIdByName(String challengeTypeName) {
        int challengeTypeID = 0;
        List<ChallengeType> challengeTypes = ChallengeTypeManager.challengeTypes;
        for (ChallengeType challengeType : challengeTypes) {
            if (challengeType.getChallengeTypeName().equals(challengeTypeName)) {
                challengeTypeID = challengeType.getChallengeTypeID();
            }
        }
        return challengeTypeID;
    }


    public static void generateChallenges() {

        //the city challenges
        Challenge challenge1 = new Challenge();
        challenge1.setChallengeID(1);
        challenge1.setChallengeName("Take a photo of...");
        challenge1.setChallengeTypeID(2);
        challenge1.setLocked(false);
        challenge1.setPoints(3);
        allChallenges.add(challenge1);

        Challenge challenge2 = new Challenge();
        challenge2.setChallengeID(2);
        challenge2.setChallengeName("Cars");
        challenge2.setChallengeTypeID(2);
        challenge2.setLocked(true);
        challenge2.setPoints(10);
        allChallenges.add(challenge2);

        Challenge challenge3 = new Challenge();
        challenge3.setChallengeID(3);
        challenge3.setChallengeName("People on the street");
        challenge3.setChallengeTypeID(2);
        challenge3.setLocked(true);
        challenge3.setPoints(5);
        allChallenges.add(challenge3);

        Challenge challenge4 = new Challenge();
        challenge4.setChallengeID(4);
        challenge4.setChallengeName("Dogs");
        challenge4.setChallengeTypeID(2);
        challenge4.setLocked(false);
        challenge4.setPoints(11);
        allChallenges.add(challenge4);

        //the mall challenges
        Challenge challenge5 = new Challenge();
        challenge5.setChallengeID(5);
        challenge5.setChallengeName("Take a photo of Santa Clause");
        challenge5.setChallengeTypeID(3);
        challenge5.setLocked(false);
        challenge5.setPoints(3);
        allChallenges.add(challenge5);

        Challenge challenge6 = new Challenge();
        challenge6.setChallengeID(6);
        challenge6.setChallengeName("Park your car without accidents:P");
        challenge6.setChallengeTypeID(3);
        challenge6.setLocked(true);
        challenge6.setPoints(20);
        allChallenges.add(challenge6);

        Challenge challenge7 = new Challenge();
        challenge7.setChallengeID(7);
        challenge7.setChallengeName("Say HELLO! to the first person:)");
        challenge7.setChallengeTypeID(3);
        challenge7.setLocked(true);
        challenge7.setPoints(5);
        allChallenges.add(challenge7);

    }

    public static Challenge getChallengeByID(int challengeID) {
        Challenge challenge = null;
        for (Challenge currChallenge : allChallenges) {
            if (currChallenge.getChallengeID() == challengeID) {
                challenge = currChallenge;
            }
        }
        return challenge;
    }
}
