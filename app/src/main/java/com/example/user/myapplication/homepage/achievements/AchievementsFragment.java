package com.example.user.myapplication.homepage.achievements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.myapplication.R;

public class AchievementsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_achivements, container, false);
        TextView text=(TextView)root.findViewById(R.id.ach_text_view);
        text.setText("Achievements");
        return root;
    }
}
