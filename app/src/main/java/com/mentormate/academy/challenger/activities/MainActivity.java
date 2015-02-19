package com.mentormate.academy.challenger.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

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

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = "Stories";
                break;
            case 2:
                mTitle = "Smth";
                break;
            case 3:
                mTitle = "Smth2";
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            ParseUser.logOut();
            Intent logout = new Intent(MainActivity.this, LoginSignupActivity.class);
            logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(logout);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
