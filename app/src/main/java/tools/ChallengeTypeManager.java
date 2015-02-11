package tools;

import com.example.user.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import entities.ChallengeType;

/**
 * Created by BekiAksi1 on 2/10/2015.
 */
public class ChallengeTypeManager {
    public static List<ChallengeType> challengeTypes = new ArrayList<ChallengeType>();

    public static void generateChallengeTypes() {
        ChallengeType challengeType1 = new ChallengeType();
        challengeType1.setChallengeTypeID(1);
        challengeType1.setChallengeTypeName(Constants.THE_PARK_CHALLENGE);
        challengeType1.setLocked(true);
        challengeType1.setBackground(R.drawable.park_background);
        challengeTypes.add(challengeType1);

        ChallengeType challengeType2 = new ChallengeType();
        challengeType2.setChallengeTypeID(2);
        challengeType2.setChallengeTypeName(Constants.THE_CITY_CHALLENGE);
        challengeType2.setLocked(false);
        challengeType2.setBackground(R.drawable.city_background);
        challengeTypes.add(challengeType2);

        ChallengeType challengeType3 = new ChallengeType();
        challengeType3.setChallengeTypeID(3);
        challengeType3.setChallengeTypeName(Constants.THE_MALL_CHALLENGE);
        challengeType3.setLocked(false);
        challengeType3.setBackground(R.drawable.mall_background);
        challengeTypes.add(challengeType3);

        ChallengeType challengeType4 = new ChallengeType();
        challengeType4.setChallengeTypeID(4);
        challengeType4.setChallengeTypeName(Constants.OTHER_CHALLENGE);
        challengeType4.setLocked(true);
        challengeType4.setBackground(R.drawable.other_background);
        challengeTypes.add(challengeType4);
    }
}
