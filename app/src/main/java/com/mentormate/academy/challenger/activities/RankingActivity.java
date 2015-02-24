package com.mentormate.academy.challenger.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.adapters.CustomRankingAdapter;
import com.mentormate.academy.challenger.utils.Constants;
import com.mentormate.academy.challenger.utils.Utils;

public class RankingActivity extends ActionBarActivity {

    private CustomRankingAdapter customRankingAdapter;
    private ListView rankingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        ActionBar bar = getSupportActionBar();
        Utils.setCustomActionBarWithColor(bar, Constants.DRAWER_MENU_ITEM_RANKING);

        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);


        customRankingAdapter = new CustomRankingAdapter(this);
        rankingListView = (ListView) findViewById(R.id.RankingListView);
        rankingListView.setAdapter(customRankingAdapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
}
