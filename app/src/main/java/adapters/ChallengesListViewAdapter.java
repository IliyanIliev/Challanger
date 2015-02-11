package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.myapplication.R;

import java.util.List;

import entities.Challenge;
import tools.ChallengeManager;


public class ChallengesListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Challenge> challenges;
    private int challengeNumber;
    // private static String[] challenges = {"The Park", "The City", "The Mall", "Other"};

    public ChallengesListViewAdapter(Context context, String challengeTypeName) {
        this.context = context;
        this.challenges = ChallengeManager.getAllChallengeByChallengeType(challengeTypeName);
        this.challengeNumber = 0;
    }

    @Override
    public int getCount() {
        return challenges.size();
    }

    @Override
    public Challenge getItem(int position) {
        return challenges.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        TextView challengeNameTextView = null;
        TextView challengePointsTextView = null;
        ImageView challengeStatusImageView = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.challenges_list_item, parent, false);
            challengeNameTextView = (TextView) convertView.findViewById(R.id.challenge_name_tv);
            challengePointsTextView = (TextView) convertView.findViewById(R.id.challenge_points_tv);
            challengeStatusImageView = (ImageView) convertView.findViewById(R.id.challenge_status_iv);

            convertView.setTag(R.id.challenge_name_tv, challengeNameTextView);
            convertView.setTag(R.id.challenge_points_tv, challengePointsTextView);
            convertView.setTag(R.id.challenge_status_iv, challengeStatusImageView);

        } else {
            challengeNameTextView = (TextView) convertView.findViewById(R.id.challenge_name_tv);
            challengePointsTextView = (TextView) convertView.findViewById(R.id.challenge_points_tv);
            challengeStatusImageView = (ImageView) convertView.findViewById(R.id.challenge_status_iv);
        }

        final Challenge currChallenge = getItem(position);
        challengeNumber++;
        challengeNameTextView.setText(challengeNumber+". "+currChallenge.getChallengeName());
        if (currChallenge.isLocked()) {
            challengeStatusImageView.setBackgroundResource(R.drawable.ic_locked);
            challengeStatusImageView.setVisibility(View.VISIBLE);
        } else {
            challengePointsTextView.setText(currChallenge.getPoints() + " points");
            challengePointsTextView.setVisibility(View.VISIBLE);
        }


        return convertView;
    }


}
