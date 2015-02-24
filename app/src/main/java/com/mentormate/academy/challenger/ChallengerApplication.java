package com.mentormate.academy.challenger;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class ChallengerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "NdRWPldwDHAG7ROfXzOZTYgCsjRrrlPZPL1EpaP4", "XHYqBhVS7mHKrqInV4hGiqkCidmhcnZ1gDDGGCNl");

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
    }
}
