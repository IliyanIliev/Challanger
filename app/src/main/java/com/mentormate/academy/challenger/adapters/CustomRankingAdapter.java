package com.mentormate.academy.challenger.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mentormate.academy.challenger.R;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by djovani on 2/24/2015.
 */
public class CustomRankingAdapter extends ParseQueryAdapter<ParseObject> {


    ParseQuery<ParseObject> query = ParseQuery.getQuery("Ranking");

    public CustomRankingAdapter(Context context) {
        super(context, new QueryFactory<ParseObject>() {
            @Override
            public ParseQuery<ParseObject> create() {
                ParseQuery query = new ParseQuery("Ranking");
                query.orderByDescending("points");
                return query;
            }
        });
    }

    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.ranking_item, null);
        }

        super.getItemView(object, v, parent);

        TextView usernameTV = (TextView) v.findViewById(R.id.RankingUsernameTextView);
        usernameTV.setText(object.get("username").toString());

        TextView points = (TextView) v.findViewById(R.id.RainkingPointsTextView);
        points.setText(object.get("points").toString() + " points");

        return v;

    }
}
