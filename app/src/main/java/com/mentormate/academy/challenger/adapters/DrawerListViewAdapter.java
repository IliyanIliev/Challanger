package com.mentormate.academy.challenger.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.models.DrawerItem;
import com.mentormate.academy.challenger.utils.DrawerItemManager;

import java.util.List;


public class DrawerListViewAdapter extends BaseAdapter {
    private Context context;
    private List<DrawerItem> drawerItemsList = null;

    public DrawerListViewAdapter(Context context) {
        this.context = context;
        drawerItemsList = DrawerItemManager.generateDrawerItemsList();
    }

    @Override
    public int getCount() {
        return drawerItemsList.size();
    }

    @Override
    public DrawerItem getItem(int position) {
        return drawerItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iconImageView = null;
        TextView iconNameTextView = null;


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.drawer_list_item, parent, false);
            iconImageView = (ImageView) convertView.findViewById(R.id.drawer_item_icon);
            iconNameTextView = (TextView) convertView.findViewById(R.id.drawer_item_name);

            convertView.setTag(R.id.drawer_item_icon, iconImageView);
            convertView.setTag(R.id.drawer_item_name, iconNameTextView);

        } else {
            iconImageView = (ImageView) convertView.findViewById(R.id.drawer_item_icon);
            iconNameTextView = (TextView) convertView.findViewById(R.id.drawer_item_name);
        }

        final DrawerItem currDrawItem = getItem(position);
        iconNameTextView.setText(currDrawItem.getName());

        String uri = "@drawable/" + currDrawItem.getIcon();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        iconImageView.setImageDrawable(res);

        return convertView;
    }


}
