package com.mentormate.academy.challenger.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.utils.Constants;
import com.mentormate.academy.challenger.utils.Utils;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ProfileActivity extends ActionBarActivity {

    private TextView usernameTv;
    private TextView pointsTv;
    private TextView emailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar bar = getSupportActionBar();
        Utils.setCustomActionBarWithColor(bar, Constants.DRAWER_MENU_ITEM_PROFILE);

        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

        usernameTv = (TextView) findViewById(R.id.username_tv);
        pointsTv = (TextView) findViewById(R.id.points_tv);
        emailTv = (TextView) findViewById(R.id.email_tv);

        ParseUser currentUser = ParseUser.getCurrentUser();

        ParseQuery query = new ParseQuery("Ranking");
        query.whereEqualTo("username",currentUser.getUsername());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject ranking, ParseException e) {
                if(e==null){
                    pointsTv.setText(String.valueOf(ranking.getInt("points")) + " points");
                }
            }
        });



        usernameTv.setText(currentUser.getUsername());
        emailTv.setText(currentUser.getEmail());
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

}
