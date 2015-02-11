package com.example.user.myapplication.homepage.challenges;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.myapplication.R;

import adapters.ChallengeTypesListViewAdapter;

public class ChallengeTypesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_challenge_types, container, false);

        ListView challengeTypesListView = (ListView) root.findViewById(R.id.challenge_types_listView);
        ChallengeTypesListViewAdapter adapter=new ChallengeTypesListViewAdapter(getActivity());
        challengeTypesListView.setAdapter(adapter);

        return root;
    }
}
