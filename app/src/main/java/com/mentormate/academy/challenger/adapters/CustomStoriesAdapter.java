package com.mentormate.academy.challenger.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.activities.ChallengesActivity;
import com.mentormate.academy.challenger.models.Story;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomStoriesAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    private List<Story> storyList = null;
    private ArrayList<Story> arraylist;

    public CustomStoriesAdapter(Context context, List<Story> storyList) {
        this.context = context;
        this.storyList = storyList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(storyList);

    }

    public class ViewHolder {
        TextView name;
        ImageView picture;
    }

    @Override
    public int getCount() {
        return storyList.size();
    }

    @Override
    public Object getItem(int position) {
        return storyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.story_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.story_name);
            // Locate the ImageView in listview_item.xml
            holder.picture = (ImageView) view.findViewById(R.id.story_image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(storyList.get(position).getName());

        Picasso.with(context).load(storyList.get(position).getPictureUrl()).into(holder.picture);

        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, ChallengesActivity.class);

                intent.putExtra("objectId",storyList.get(position).getObjectId());
                intent.putExtra("name",storyList.get(position).getName());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
