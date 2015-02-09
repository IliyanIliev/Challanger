package adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import entities.AssignedChallenge;
import entities.Challenge;
import listeners.OnReviewViewPagerButtonClickListener;
import tools.AssignedChallengeManager;
import tools.ChallengeManager;

public class ReviewImageAdapter extends PagerAdapter {
    private OnReviewViewPagerButtonClickListener onButtonClickListener;
    Activity activity;
    private List<AssignedChallenge> assignedChallenges;
    private int[] photos = new int[]{
            R.drawable.city_background,
            R.drawable.park_background,
            R.drawable.mall_background
    };

    // This holds all the currently displayable views, in order from left to right.
    private ArrayList<View> views = new ArrayList<View>();

    public ReviewImageAdapter(Activity activity) {
        this.activity = activity;
        this.assignedChallenges = AssignedChallengeManager.getNotApprovedChallenges();
        this.onButtonClickListener = (OnReviewViewPagerButtonClickListener) activity;

    }

    @Override
    public int getCount() {
        return assignedChallenges.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        TextView challengeTextView;
        TextView photoCounterTextView;
        ImageView photoImageView;
        ImageView approveButton;
        ImageView rejectButton;

        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.review_viewpager_item, container,
                false);

        challengeTextView = (TextView) itemView.findViewById(R.id.review_challenger_tv);
        photoCounterTextView = (TextView) itemView.findViewById(R.id.review_photoCounter_tv);
        rejectButton = (ImageView) itemView.findViewById(R.id.review_reject_button);
        approveButton = (ImageView) itemView.findViewById(R.id.review_approve_button);

        int photoCounter = position + 1;
        final AssignedChallenge currAssignedChallenge = assignedChallenges.get(position);
        photoCounterTextView.setText(photoCounter + "/" + getCount());

        final Challenge currChallenge = ChallengeManager.getChallengeByID(currAssignedChallenge.getChallengeID());
        challengeTextView.setText(currChallenge.getChallengeName());

        photoImageView = (ImageView) itemView.findViewById(R.id.review_photo_iv);
        photoImageView.setImageResource(photos[position]);
        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currAssignedChallenge.setRejected(true);
                onButtonClickListener.reloadViewPager();
            }
        });

        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currAssignedChallenge.setApproved(true);
                onButtonClickListener.reloadViewPager();
            }
        });


        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        /*ImageView imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(GalImages[position]);
        ((ViewPager) container).addView(imageView, 0);*/
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
