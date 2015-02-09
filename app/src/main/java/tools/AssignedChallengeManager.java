package tools;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import entities.AssignedChallenge;
import entities.Challenge;
import entities.User;

public class AssignedChallengeManager {

    private static List<AssignedChallenge> assignedChallenges = null;

    public static void generateAssignedChallenges() {
        List<User> users = UserManager.users;
        List<Challenge> challenges = ChallengeManager.allChallenges;
        assignedChallenges = new ArrayList<AssignedChallenge>();

        int counter = 1;
        for (int i = 0; i < 3; i++) {
            AssignedChallenge assignedChallenge = new AssignedChallenge();
            assignedChallenge.setAssignedChallengeID(counter++);
            assignedChallenge.setUserID(users.get(0).getUserID());
            assignedChallenge.setChallengeID(challenges.get(i).getChallengeID());
           // Log.e("not approved: ",challenges.get(i).getChallengeName());
            assignedChallenge.setCompleted(true);
            assignedChallenge.setApproved(false);
            assignedChallenge.setRejected(false);
            assignedChallenges.add(assignedChallenge);
        }
        for (int i = 3; i < challenges.size(); i++) {
            AssignedChallenge assignedChallenge = new AssignedChallenge();
            assignedChallenge.setAssignedChallengeID(counter++);
            assignedChallenge.setUserID(users.get(0).getUserID());
            assignedChallenge.setChallengeID(challenges.get(i).getChallengeID());
            //Log.e("approved: ",challenges.get(i).getChallengeName());
            assignedChallenge.setCompleted(true);
            assignedChallenge.setApproved(true);
            assignedChallenge.setRejected(false);
            assignedChallenges.add(assignedChallenge);
        }


    }


    public static List<AssignedChallenge> getNotApprovedChallenges() {
        List<AssignedChallenge> notApprovedAssignedChallenges=new ArrayList<AssignedChallenge>();
        for(AssignedChallenge assignedChallenge:assignedChallenges){
            if((assignedChallenge.isCompleted())&&(!assignedChallenge.isApproved())&&(!assignedChallenge.isRejected())){
                notApprovedAssignedChallenges.add(assignedChallenge);
            }
        }
        return notApprovedAssignedChallenges;
    }
}
