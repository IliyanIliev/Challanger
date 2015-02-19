package com.mentormate.academy.challenger.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.fragments.LoginFragment;

public class LoginSignupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        LoginFragment newFragment = LoginFragment.newInstance();

        ft.replace(R.id.container, newFragment, "loginFragment");

        ft.commit();
    }

}
