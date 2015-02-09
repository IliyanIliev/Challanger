package com.example.user.myapplication.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.myapplication.R;

import adapters.ChallengesListViewAdapter;

public class ChallengesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_challenges, container, false);

        //challenges_listView and challenges_listView_item
       /* ListView challengesListView = (ListView) root.findViewById(R.id.challenges_listView);
        ChallengesListViewAdapter adapter = new ChallengesListViewAdapter(getActivity());
        challengesListView.setAdapter(adapter);*/


        return root;
    }
}
