package com.mentormate.academy.challenger.listeners;


import com.parse.ParseObject;

import java.util.List;

public interface OnReviewViewPagerButtonClickListener {
    public void reloadViewPager(List<ParseObject> submissionsToCheck);
}
