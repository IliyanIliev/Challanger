package com.mentormate.academy.challenger.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.listeners.OnReviewViewPagerButtonClickListener;
import com.mentormate.academy.challenger.utils.Constants;
import com.parse.DeleteCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ReviewImageAdapter extends PagerAdapter {
    private OnReviewViewPagerButtonClickListener onButtonClickListener;
    Activity activity;
    private List<ParseObject> submissionsToCheck;

    // This holds all the currently displayable views, in order from left to right.
    private ArrayList<View> views = new ArrayList<View>();

    public ReviewImageAdapter(Activity activity, List<ParseObject> subToCheck) {
        this.activity = activity;
        this.submissionsToCheck = subToCheck;
        this.onButtonClickListener = (OnReviewViewPagerButtonClickListener) activity;
    }

    @Override
    public int getCount() {
        return submissionsToCheck.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        TextView challengeTextView;
        ImageView photoImageView;
        ImageView approveButton;
        ImageView rejectButton;

        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.review_viewpager_item, container, false);

        challengeTextView = (TextView) itemView.findViewById(R.id.challenge_desc);
        rejectButton = (ImageView) itemView.findViewById(R.id.review_reject_button);
        approveButton = (ImageView) itemView.findViewById(R.id.review_approve_button);

        final ParseObject currAssignedSubmission = submissionsToCheck.get(position);

        ParseObject currChallengeForSubmission = (ParseObject) currAssignedSubmission.get("challenge");
        String challengeDesc = currChallengeForSubmission.getString("description");
        challengeTextView.setText(challengeDesc + "\n Is it done? ");

        photoImageView = (ImageView) itemView.findViewById(R.id.review_photo_iv);
        Picasso.with(activity).load(currAssignedSubmission.getParseFile("photo").getUrl()).into(photoImageView);


        ParseUser userObj = (ParseUser) currAssignedSubmission.get("user");

        final String username = userObj.getUsername();
        final String storyName = currAssignedSubmission.getString("storyName").replaceAll("\\s+", "");


        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currAssignedSubmission.deleteInBackground(new DeleteCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            ParsePush parsePush = new ParsePush();
                            ParseQuery pQuery = ParseInstallation.getQuery();
                            pQuery.whereEqualTo("usrname", username);

                            ParseObject challange = (ParseObject) currAssignedSubmission.get("challenge");

                            int level = challange.getInt("number");
                            String categoryName = currAssignedSubmission.getString("storyName");
                            String pushMsg = "Sorry! You did not completed Level "
                                    + level + " from category " + categoryName+" You can try again :)";
                            parsePush.sendMessageInBackground(pushMsg, pQuery);
                        } else {
                            e.printStackTrace();
                        }
                    }
                });

                submissionsToCheck.remove(position);
                onButtonClickListener.reloadViewPager(submissionsToCheck);
            }
        });


        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Change status
                currAssignedSubmission.put("status", Constants.SUCCESS_STATE);
                currAssignedSubmission.saveInBackground();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Ranking");
                query.whereEqualTo("username", username);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject ranking, ParseException e) {
                        if (ranking != null) {
                            int currPoints = ranking.getInt("points");
                            ranking.put("points", currPoints + 10);
                            int currLevel = ranking.getInt(storyName);
                            ranking.put(storyName, currLevel + 1);
                            ranking.saveInBackground();
                        } else {
                            Log.d("ERR", "Could not get ranking!");
                        }
                    }
                });
                //Send notification to the user

                ParsePush parsePush = new ParsePush();
                ParseQuery pQuery = ParseInstallation.getQuery();
                pQuery.whereEqualTo("usrname", username);

                ParseObject challange = (ParseObject) currAssignedSubmission.get("challenge");

                int level = challange.getInt("number");
                String categoryName = currAssignedSubmission.getString("storyName");
                String pushMsg = "Hey you just earned 10 points! You successfully completed Level "
                        + level + " from category " + categoryName;

                parsePush.sendMessageInBackground(pushMsg, pQuery);

                submissionsToCheck.remove(position);
                onButtonClickListener.reloadViewPager(submissionsToCheck);
            }
        });

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
