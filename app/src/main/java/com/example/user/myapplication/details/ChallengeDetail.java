package com.example.user.myapplication.details;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.myapplication.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import tools.Constants;

public class ChallengeDetail extends ActionBarActivity {

    private TextView name, description;
    private Button takePicture, submitChallenge;
    private ImageView imgView;

    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_detail);

        name = (TextView) findViewById(R.id.tvChallengeDetailName);
        description = (TextView) findViewById(R.id.tvChallengeDetailDescription);


        if (!getIntent().getExtras().isEmpty()) {
            name.setText(getIntent().getExtras().get(Constants.CHALLENGES_FRAGMENT_INTENT_CUSTOM_ADAPTER_NAME_MSG).toString());
//            todo add description
            description.setText("awodawodauowhdoawhduoawhdawodhoawdhoawdhoawdoawdnawdawdawdawdawdawd");
        }


        imgView = (ImageView) findViewById(R.id.imageViewChallengeDetailImg);

        takePicture = (Button) findViewById(R.id.btnChallengeDetailTakePicture);
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(v);
            }
        });

        submitChallenge = (Button) findViewById(R.id.btnChallengeDetailFinishChallenge);
        submitChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TODO submit challenge, redirect to ?
            }
        });

    }


    public void takePicture(View v) {
//                Handle video?
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String imageFileName = Constants.FILE_PREFIX + timeStamp + Constants.FILE_SUFFIX;

        File albumFolder = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            albumFolder = new File(Environment.getExternalStorageDirectory() + Constants.CAMERA_DIR + Constants.ALBUM_NAME);
            if (albumFolder != null) {
                if (!albumFolder.mkdirs()) {
                    if (!albumFolder.exists()) {
                        Log.e(this.getClass().getName(), "Cannot create directory");
                        albumFolder = null;
                    }
                }
            }
        } else {
            Log.e(this.getClass().getName(), "No external storage");
//            TODO handle missing external storage
        }

        File photoFile = new File(albumFolder, imageFileName);
        photoPath = photoFile.getAbsolutePath();
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));

        startActivityForResult(takePictureIntent, Constants.ACTION_TAKE_PHOTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (photoPath != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
                imgView.setImageBitmap(bitmap);
                imgView.setVisibility(View.VISIBLE);
                photoPath = null;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_challenge_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
