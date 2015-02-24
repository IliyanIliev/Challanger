package com.mentormate.academy.challenger.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.models.User;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupFragment extends Fragment implements View.OnClickListener {

    private EditText mEtUsername;
    private EditText mEtPassword;
    private EditText mEtEmail;
    private Button mBtnRegister;

    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        return fragment;
    }

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        mEtUsername = (EditText) view.findViewById(R.id.etUserName);
        mEtPassword = (EditText) view.findViewById(R.id.etPass);
        mEtEmail = (EditText) view.findViewById(R.id.etEmail);
        mBtnRegister = (Button) view.findViewById(R.id.btnSignUp);

        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        User newUser = new User();

        newUser.setUsername(this.mEtUsername.getText().toString());
        newUser.setPassword(this.mEtPassword.getText().toString());
        newUser.setEmail(this.mEtEmail.getText().toString());

        if (validateUser(newUser)) {
            final ParseUser user = new ParseUser();
            user.setUsername(this.mEtUsername.getText().toString());
            user.setPassword(this.mEtPassword.getText().toString());
            user.setEmail(this.mEtEmail.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        String username = mEtUsername.getText().toString();
                        ParseObject ranking = new ParseObject("Ranking");
                        ranking.put("username",username);
                        ranking.put("points", 0);
                        ranking.put("ThePark",1);
                        ranking.put("TheCity",1);
                        ranking.put("TheMall",1);
                        ranking.put("TheZoo",1);
                        ranking.put("TheCountry",1);
                        ranking.saveInBackground();

                        // Show a simple Toast message upon successful registration
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Successfully Signed up, please log in.",
                                Toast.LENGTH_LONG).show();

                        FragmentManager fm = getActivity()
                                .getFragmentManager();
                        fm.popBackStack();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Sign up Error", Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });
        } else {
            Toast.makeText(getActivity(), getString(R.string.validation_error), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateUser(User newUser) {
        if (newUser.getUsername().equals("")
                || newUser.getPassword().equals("")) {
            return false;
        }

        return true;
    }
}
