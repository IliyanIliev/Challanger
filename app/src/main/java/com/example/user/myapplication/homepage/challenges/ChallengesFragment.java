package com.example.user.myapplication.homepage.challenges;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import adapters.ChallengesListViewAdapter;
import tools.Constants;

public class ChallengesFragment extends ListFragment {
    private String challengeTypeName;
    private ChallengesListViewAdapter customAdapter = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        challengeTypeName = getArguments().getString(Constants.CHALLENGE_TYPE_NAME);
    }

    // onActivityCreated() is called when the activity's onCreate() method has returned.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        customAdapter = new ChallengesListViewAdapter(getActivity(), challengeTypeName);

        // Populate list with all the titles we have in the Constants.TITLES.
        setListAdapter(customAdapter);

    }


    // If the user clicks on an item in the list then the onListItemClick() method is called.
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    // Helper function to show the details of a selected item, either by displaying a fragment
    // in-place in the current UI, or starting a whole new activity in which it is displayed.

    void showDetails(int index) {
        // Launch new DetailsActivity to display the dialog fragment with selected text. Put selected index as Extra.
        Intent intent = new Intent();
        intent.setClass(getActivity(), ChallengesListViewAdapter.class);
        // intent.putExtra("movie_id", customAdapter.getItem(index).getMovieId());
        startActivity(intent);

    }
}
