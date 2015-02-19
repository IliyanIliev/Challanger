package com.mentormate.academy.challenger.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mentormate.academy.challenger.R;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class CustomChallengesAdapter extends ParseQueryAdapter<ParseObject> {

    public CustomChallengesAdapter(Context context, final String storyId) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseObject obj = ParseObject.createWithoutData("Story", storyId);
                ParseQuery query = new ParseQuery("Challange");
                query.whereEqualTo("story", obj);
                query.orderByAscending("number");
                query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.challange_item, null);
        }

        super.getItemView(object, v, parent);

        // Add the level number
        TextView levelNum = (TextView) v.findViewById(R.id.level_num);
        levelNum.setText("Level" + object.getInt("number"));

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.challenge_name);
        titleTextView.setText(object.getString("name"));

        return v;
    }
}
