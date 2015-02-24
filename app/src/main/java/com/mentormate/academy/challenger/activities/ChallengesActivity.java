package com.mentormate.academy.challenger.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.adapters.CustomChallengesAdapter;
import com.mentormate.academy.challenger.utils.Utils;
import com.parse.ParseObject;

public class ChallengesActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private CustomChallengesAdapter challangesAdapter;
    private ListView challengesListView;
    private String storyName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

        Intent obtainedIntent = getIntent();
        if(obtainedIntent!=null){
            storyName = obtainedIntent.getStringExtra("name");
            ActionBar bar = getSupportActionBar();
            Utils.setCustomActionBarWithColor(bar,storyName);
        }

        // Initialize the subclass of ParseQueryAdapter
        challangesAdapter = new CustomChallengesAdapter(this, obtainedIntent.getStringExtra("objectId"), storyName);

        // Initialize ListView and set initial view to mainAdapter
        challengesListView = (ListView) findViewById(R.id.challengesListView);
        challengesListView.setAdapter(challangesAdapter);
        challangesAdapter.loadObjects();

        challengesListView.setOnItemClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ParseObject item = (ParseObject) parent.getAdapter().getItem(position);
        String objectID = item.getObjectId();

        TextView nameTV = (TextView) view.findViewById(R.id.challenge_name);
        String name = nameTV.getText().toString();
        Intent intent = new Intent(this,SubmissionActivity.class);
        intent.putExtra("objectId",objectID);
        intent.putExtra("name",name);
        intent.putExtra("storyName", storyName);
        startActivity(intent);
    }
}
