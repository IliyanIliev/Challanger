package com.mentormate.academy.challenger.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.activities.MainActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private TextView mTvSignup;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        init(v);
        return v;
    }

    private void init(View view) {
        mEtUsername = (EditText) view.findViewById(R.id.etUserName);
        mEtPassword = (EditText) view.findViewById(R.id.etPass);
        mBtnLogin = (Button) view.findViewById(R.id.btnSignIn);
        mTvSignup = (TextView) view.findViewById(R.id.tvSignUp);

        mBtnLogin.setOnClickListener(this);
        mTvSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                login();

                break;
            case R.id.tvSignUp:
                goToRegisterUser();
                break;
        }
    }

    private void login() {
        final String username = this.mEtUsername.getText().toString().trim();
        // Send data to Parse.com for verification
        ParseUser.logInInBackground(username,
                this.mEtPassword.getText().toString().trim(),
                new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            //Associate current installation with the current user
                            ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                            installation.put("usrname", username);
                            installation.saveInBackground();

                            // If user exist and authenticated, send user to MainActivity
                            Intent intent = new Intent(
                                    getActivity(),
                                    MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            Toast.makeText(
                                    getActivity().getApplicationContext(),
                                    "No such user exist, please signup",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void goToRegisterUser() {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(
                R.animator.slide_in_left,
                R.animator.slide_out_right,
                R.animator.slide_in_left,
                R.animator.slide_out_right);
        ft.replace(R.id.container, new SignupFragment(), "signupFragment");
        ft.addToBackStack(null);
        ft.commit();
    }
}
