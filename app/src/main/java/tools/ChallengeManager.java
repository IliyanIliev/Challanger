package tools;

import java.util.ArrayList;
import java.util.List;

import entities.Challenge;
import entities.ChallengeType;


public class ChallengeManager {
    public static List<ChallengeType> challengeTypes = new ArrayList<ChallengeType>();
    public static List<Challenge> allChallenges = new ArrayList<>();

    public static List<Challenge> getAllChallengeByChallengeType(String challengeTypeName) {

        int challengeTypeID=findChallengeTypeIdByName(challengeTypeName);
        List<Challenge> challenges = new ArrayList<>();
        for (Challenge challenge : allChallenges) {
            if (challenge.getChallengeTypeID()==challengeTypeID) {
                challenges.add(challenge);
            }
        }

        return challenges;
    }

    private static int findChallengeTypeIdByName(String challengeTypeName) {
        int challengeTypeID=0;
        for(ChallengeType challengeType:challengeTypes){
            if(challengeType.getChallengeTypeName().equals(challengeTypeName)){
                challengeTypeID=challengeType.getChallengeTypeID();
            }
        }
        return challengeTypeID;
    }

    public static void generateChallengeTypes() {
        ChallengeType challengeType1 = new ChallengeType();
        challengeType1.setChallengeTypeID(1);
        challengeType1.setChallengeTypeName(Constants.THE_PARK_CHALLENGE);
        challengeTypes.add(challengeType1);

        ChallengeType challengeType2 = new ChallengeType();
        challengeType2.setChallengeTypeID(2);
        challengeType2.setChallengeTypeName(Constants.THE_CITY_CHALLENGE);
        challengeTypes.add(challengeType2);

        ChallengeType challengeType3 = new ChallengeType();
        challengeType3.setChallengeTypeID(3);
        challengeType3.setChallengeTypeName(Constants.THE_MALL_CHALLENGE);
        challengeTypes.add(challengeType3);

        ChallengeType challengeType4 = new ChallengeType();
        challengeType4.setChallengeTypeID(4);
        challengeType4.setChallengeTypeName(Constants.OTHER_CHALLENGE);
        challengeTypes.add(challengeType4);
    }

    public static void generateChallenges() {

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
    }

    public static Challenge getChallengeByID(int challengeID) {
        Challenge challenge=null;
        for(Challenge currChallenge:allChallenges){
            if(currChallenge.getChallengeID()==challengeID){
                challenge=currChallenge;
            }
        }
        return challenge;
    }
}
