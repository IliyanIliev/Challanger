package com.example.user.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.user.myapplication.homepage.challenges.ChallengesActivity;

import tools.AssignedChallengeManager;
import tools.ChallengeManager;
import tools.ChallengeTypeManager;
import tools.UserManager;


public class StartActivity extends Activity implements View.OnClickListener{
    private Button openMainMenuButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        openMainMenuButton = (Button) findViewById(R.id.login_button);
        openMainMenuButton.setOnClickListener(this);

        ChallengeTypeManager.generateChallengeTypes();
        ChallengeManager.generateChallenges();
        UserManager.generateUsers();
        AssignedChallengeManager.generateAssignedChallenges();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                openChallengesActivity();
        }
    }

    private void openChallengesActivity() {
        Intent openChallengesActivity=new Intent(this, ChallengesActivity.class);
        startActivity(openChallengesActivity);
    }
}
