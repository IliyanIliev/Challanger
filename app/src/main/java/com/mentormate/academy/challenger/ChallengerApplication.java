package com.mentormate.academy.challenger;

import android.app.Application;

import com.parse.Parse;

public class ChallengerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "NdRWPldwDHAG7ROfXzOZTYgCsjRrrlPZPL1EpaP4", "XHYqBhVS7mHKrqInV4hGiqkCidmhcnZ1gDDGGCNl");

    }
}
