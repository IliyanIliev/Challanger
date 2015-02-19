package com.mentormate.academy.challenger.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.mentormate.academy.challenger.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utils {

    public static void setCustomActionBarWithColor(ActionBar bar){
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        bar.setCustomView(R.layout.actionbar);
        bar.setDisplayShowCustomEnabled(true);
        bar.setBackgroundDrawable(new ColorDrawable(0xff1fbba6));
    }

    public static File createImageFile() throws IOException {
        String mCurrentPhotoPath;
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    public static Bitmap scalePhoto(Uri imageUri, Activity activity) {

        ContentResolver cr = activity.getContentResolver();
        //imageBitmap =

        // Resize photo from camera byte array
        Bitmap currImage = null;
        try {
          currImage = MediaStore.Images.Media.getBitmap(cr,imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap imageScaled = Bitmap.createScaledBitmap(currImage, 600
                * currImage.getWidth() / currImage.getHeight(), 600, false);

        Log.d("TAG2", imageScaled.getWidth() + " " + imageScaled.getHeight());

        // Override Android default landscape orientation and save portrait
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedScaledImage = Bitmap.createBitmap(imageScaled, 0,
                0, imageScaled.getWidth(), imageScaled.getHeight(),
                matrix, true);

        Bitmap resultBmp = null;

        if (rotatedScaledImage.getWidth() >= rotatedScaledImage.getHeight()){

            resultBmp = Bitmap.createBitmap(
                    rotatedScaledImage,
                    rotatedScaledImage.getWidth()/2 - rotatedScaledImage.getHeight()/2,
                    0,
                    rotatedScaledImage.getHeight(),
                    rotatedScaledImage.getHeight()+100
            );

        }else{

            resultBmp = Bitmap.createBitmap(
                    rotatedScaledImage,
                    0,
                    rotatedScaledImage.getHeight()/2 - rotatedScaledImage.getWidth()/2,
                    rotatedScaledImage.getWidth(),
                    rotatedScaledImage.getWidth()+100
            );
        }

        Log.d("TAG", resultBmp.getWidth() + " " + resultBmp.getHeight());
        return resultBmp;
    }
}
