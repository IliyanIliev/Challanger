package adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.homepage.challenges.ChallengesFragment;

import java.util.List;

import entities.ChallengeType;
import tools.ChallengeTypeManager;
import tools.Constants;


public class ChallengeTypesListViewAdapter extends BaseAdapter {
    private FragmentActivity activity;
    private List<ChallengeType> challengeTypes;
    private int[] backgrounds = new int[]{
            R.drawable.city_background,
            R.drawable.park_background,
            R.drawable.mall_background,
            R.drawable.other_background
    };

    public ChallengeTypesListViewAdapter(FragmentActivity activity) {
        this.activity = activity;
        this.challengeTypes = ChallengeTypeManager.challengeTypes;
    }

    @Override
    public int getCount() {
        return challengeTypes.size();
    }

    @Override
    public ChallengeType getItem(int position) {
        return challengeTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView challengeTypeBackground = null;
        TextView challengeTypeName = null;
        ImageView challengeTypeStatusIcon = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.challenge_type_item_layout, parent, false);
            challengeTypeBackground = (ImageView) convertView.findViewById(R.id.challenge_type_background);
            challengeTypeName = (TextView) convertView.findViewById(R.id.challenge_type_name);
            challengeTypeStatusIcon = (ImageView) convertView.findViewById(R.id.challenge_type_status);

            convertView.setTag(R.id.challenge_type_background, challengeTypeBackground);
            convertView.setTag(R.id.challenge_type_name, challengeTypeName);
            convertView.setTag(R.id.challenge_type_status, challengeTypeStatusIcon);

        } else {
            challengeTypeBackground = (ImageView) convertView.findViewById(R.id.challenge_type_background);
            challengeTypeName = (TextView) convertView.findViewById(R.id.challenge_type_name);
            challengeTypeStatusIcon = (ImageView) convertView.findViewById(R.id.challenge_type_status);
        }

        final ChallengeType currChallengeType = getItem(position);
        challengeTypeName.setText(currChallengeType.getChallengeTypeName());
        if (currChallengeType.isLocked()) {
            challengeTypeStatusIcon.setBackgroundResource(R.drawable.ic_locked);
        } else {
            challengeTypeStatusIcon.setBackgroundResource(R.drawable.ic_checked);
        }

        challengeTypeBackground.setBackgroundResource(currChallengeType.getBackground());
        challengeTypeBackground.setAlpha(0.8f);
        challengeTypeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, currChallengeType.getChallengeTypeName()+" was selected", Toast.LENGTH_LONG).show();
                ChallengesFragment challengesFragment = new ChallengesFragment();
                Bundle args = new Bundle();
                args.putString(Constants.CHALLENGE_TYPE_NAME, currChallengeType.getChallengeTypeName());
                challengesFragment.setArguments(args);
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.challenges_container, challengesFragment)
                        .commit();
            }
        });

        return convertView;
    }


}
