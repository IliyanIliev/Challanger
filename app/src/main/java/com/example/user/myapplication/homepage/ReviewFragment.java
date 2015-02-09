package com.example.user.myapplication.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.myapplication.R;

import adapters.ReviewImageAdapter;

public class ReviewFragment extends Fragment {
    private ViewPager viewPager;
    private ReviewImageAdapter adapter;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_review, container, false);
        viewPager = (ViewPager) root.findViewById(R.id.view_pager);
        adapter = new ReviewImageAdapter(getActivity());
        viewPager.setAdapter(adapter);
        return root;
    }

}
