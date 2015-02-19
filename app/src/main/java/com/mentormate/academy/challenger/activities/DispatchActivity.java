package com.mentormate.academy.challenger.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;


public class DispatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent takeUserToMain = new Intent(this, MainActivity.class);
            startActivity(takeUserToMain);
            finish();
        } else {
            // show the signup or login screen
            Intent takeUserToLogin = new Intent(this, LoginSignupActivity.class);
            startActivity(takeUserToLogin);
            finish();
        }
    }

}
