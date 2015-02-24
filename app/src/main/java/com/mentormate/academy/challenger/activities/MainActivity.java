package com.mentormate.academy.challenger.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.adapters.CustomStoriesAdapter;
import com.mentormate.academy.challenger.fragments.NavigationDrawerFragment;
import com.mentormate.academy.challenger.models.Story;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private CustomStoriesAdapter storiesAdapter;
    private ListView storiesListView;
    DrawerLayout mDrawer;

    List<ParseObject> ob;
    private List<Story> storylist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(0xff1fbba6));

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();


        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                mDrawer);

        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();
    }


    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            storylist = new ArrayList<Story>();
            try {
                // Locate the class table named "Story" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "Story");
                query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
                ob = query.find();
                for (ParseObject story : ob) {
                    // Locate images in flag column
                    ParseFile image = (ParseFile) story.get("picture");

                    Story currentStory = new Story();
                    currentStory.setObjectId(story.getObjectId());
                    currentStory.setName((String) story.get("name"));
                    currentStory.setPictureUrl(image.getUrl());
                    storylist.add(currentStory);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            storiesListView = (ListView) findViewById(R.id.story_list);
            // Pass the results into ListViewAdapter.java
            storiesAdapter = new CustomStoriesAdapter(MainActivity.this,
                    storylist);
            // Binds the Adapter to the ListView
            storiesListView.setAdapter(storiesAdapter);
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        loadChosenActivity(position);
    }

    private void loadChosenActivity(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:
                intent = new Intent(this, RankingActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, ReviewActivity.class);
                startActivity(intent);
                break;
            case 5:
                ParseUser.logOut();
                Intent logout = new Intent(MainActivity.this, LoginSignupActivity.class);
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logout);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed(){
        if(mDrawer.isDrawerOpen(Gravity.LEFT)){
            mDrawer.closeDrawer(Gravity.LEFT);     // replace this with actual function which closes drawer
        }
        else{
            super.onBackPressed();
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }
}
