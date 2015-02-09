package com.example.user.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.myapplication.homepage.MainActivity;


public class StartActivity extends Activity implements View.OnClickListener{
    private Button openMainMenuButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        openMainMenuButton = (Button) findViewById(R.id.login_button);
        openMainMenuButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                openMainActivity();
        }
    }

    private void openMainActivity() {
        //to do
        Intent openMainActivityIntent=new Intent(this,MainActivity.class);
        startActivity(openMainActivityIntent);
    }
}
