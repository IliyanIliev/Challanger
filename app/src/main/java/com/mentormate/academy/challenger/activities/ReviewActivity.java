package com.mentormate.academy.challenger.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.adapters.ReviewImageAdapter;
import com.mentormate.academy.challenger.listeners.OnReviewViewPagerButtonClickListener;
import com.mentormate.academy.challenger.utils.Constants;
import com.mentormate.academy.challenger.utils.Utils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ReviewActivity extends ActionBarActivity  implements OnReviewViewPagerButtonClickListener {

    private ViewPager viewPager;
    private ReviewImageAdapter adapter;
    private TextView reviewMsgTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ActionBar bar = getSupportActionBar();
        Utils.setCustomActionBarWithColor(bar, Constants.DRAWER_MENU_ITEM_REVIEW);

        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        reviewMsgTv = (TextView) findViewById(R.id.reviewTv);
        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery query = new ParseQuery("Submission");
        query.whereNotEqualTo("user", currentUser);
        query.whereEqualTo("status", Constants.PROGRESS_STATE);
        query.orderByAscending("createdAt");
        query.setLimit(5);
        query.include("user");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> submissionsToCheck, ParseException e) {
                if (e == null) {
                    adapter = new ReviewImageAdapter(ReviewActivity.this, submissionsToCheck);
                    viewPager.setAdapter(adapter);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

    @Override
    public void reloadViewPager(List<ParseObject> submissionsToCheck) {
        if(submissionsToCheck.size()==0){
            reviewMsgTv.setVisibility(View.VISIBLE);
        }
        adapter = new ReviewImageAdapter(ReviewActivity.this, submissionsToCheck);
        viewPager.setAdapter(adapter);
    }
}
