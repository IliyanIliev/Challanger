package com.example.user.myapplication.homepage.challenges_menuitems;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.homepage.handle_fragments.HandleChallengesFragment;

import tools.Constants;
import tools.UtilityClass;

public class ParkMenuItemFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.challenges_menu_item_layout, container, false);

        ImageView backgroundImageView = (ImageView) root.findViewById(R.id.menu_item_background);
        Bitmap bmp= BitmapFactory.decodeResource(getActivity().getResources(),
                R.drawable.park_background);
        Bitmap bitmap= UtilityClass.scaleImage(getActivity(), bmp);
        backgroundImageView.setImageBitmap(bitmap);
        backgroundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "The park was selected", Toast.LENGTH_LONG).show();
                Fragment challengeFragment = new HandleChallengesFragment();
                Bundle args = new Bundle();
                args.putString(Constants.CHALLENGE_NAME, Constants.THE_PARK_CHALLENGE);
                challengeFragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, challengeFragment)
                        .commit();
            }
        });

        ImageView imageView = (ImageView) root.findViewById(R.id.menu_item_status);
        imageView.setBackgroundResource(R.drawable.ic_checked);

        TextView text = (TextView) root.findViewById(R.id.menu_item_name);
        text.setText(Constants.THE_PARK_CHALLENGE);

        return root;
    }

}
