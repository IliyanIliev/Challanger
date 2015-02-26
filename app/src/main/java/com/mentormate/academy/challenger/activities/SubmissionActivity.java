package com.mentormate.academy.challenger.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mentormate.academy.challenger.R;
import com.mentormate.academy.challenger.utils.Constants;
import com.mentormate.academy.challenger.utils.Utils;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class SubmissionActivity extends ActionBarActivity {

    private ImageView challangeImg;
    private Uri imageUri;
    private String challangeId;
    private ImageButton cameraBtn;
    private String storyName = "";
    private TextView challengeDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

        cameraBtn = (ImageButton) findViewById(R.id.cameraBtn);
        challangeImg = (ImageView) findViewById(R.id.challenge_submission);
        challengeDesc = (TextView) findViewById(R.id.challenge_desc);

        cameraBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cameraBtn.setImageResource(R.drawable.cameraiconpressed);
                        break;
                    case MotionEvent.ACTION_UP:
                        cameraBtn.setImageResource(R.drawable.cameraicon);
                        break;
                }
                return false;
            }
        });

        Intent obtainedIntent = getIntent();

        if (obtainedIntent != null) {
            String title = obtainedIntent.getStringExtra("name");
            ActionBar bar = getSupportActionBar();
            Utils.setCustomActionBarWithColor(bar, title);
            challangeId = obtainedIntent.getStringExtra("objectId");
            storyName = obtainedIntent.getStringExtra("storyName");
            challengeDesc.setText(obtainedIntent.getStringExtra("challenge_desc"));
        }

        ParseObject challenge = ParseObject.createWithoutData("Challange", challangeId);
        ParseUser user = ParseUser.getCurrentUser();
        ParseQuery query = new ParseQuery("Submission");
        query.whereEqualTo("user", user);
        query.whereEqualTo("challenge", challenge);
        try {
            ParseObject submission = query.getFirst();

            if(submission!=null){
                ParseFile photo = (ParseFile) submission.get("photo");
                String photoUrl = photo.getUrl();
                if(photoUrl!="" && photoUrl!=null){
                    Picasso.with(SubmissionActivity.this).load(photoUrl).into(challangeImg);
                }
                int status = submission.getInt("status");
                if(status == Constants.PROGRESS_STATE){
                    cameraBtn.setImageResource(R.drawable.progress);
                    cameraBtn.setEnabled(false);
                    cameraBtn.setClickable(false);
                }
                else if(status>0){
                    cameraBtn.setImageResource(R.drawable.completed);
                    cameraBtn.setEnabled(false);
                    cameraBtn.setClickable(false);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.CAMERA_REQUEST && resultCode == RESULT_OK) {
            cameraBtn.setImageResource(R.drawable.progress);
            cameraBtn.setEnabled(false);
            cameraBtn.setClickable(false);

            Uri currentImg = imageUri;
            getContentResolver().notifyChange(currentImg, null);
            Bitmap scaledImage = Utils.scalePhoto(imageUri, SubmissionActivity.this);
            challangeImg.setImageBitmap(scaledImage);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            scaledImage.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] scaledData = bos.toByteArray();

            ParseFile photo = new ParseFile("photo.jpg", scaledData);

            //Get the current user
            ParseUser user = ParseUser.getCurrentUser();

            ParseObject submissionObj = new ParseObject("Submission");
            submissionObj.put("user",user);
            submissionObj.put("storyName",storyName);
            submissionObj.put("challenge", ParseObject.createWithoutData("Challange", challangeId));
            submissionObj.put("photo", photo);
            submissionObj.put("status",Constants.PROGRESS_STATE);
            submissionObj.saveInBackground(new SaveCallback() {

                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(SubmissionActivity.this,
                                "Error saving: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SubmissionActivity.this,
                                "Your photo was successfully uploaded!",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = Utils.createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                imageUri = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        imageUri);
                startActivityForResult(takePictureIntent, Constants.CAMERA_REQUEST);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

}
