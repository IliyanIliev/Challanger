package tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

import java.io.InputStream;

public class UtilityClass {

    public static Bitmap scaleImage(Activity activity,Bitmap bmp) {
        int screenWidth = 0;
        int screenHeight = 0;

        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        Bitmap newBitmap = Bitmap.createScaledBitmap(bmp,screenWidth,screenHeight/4, true);

        /*BitmapFactory.Options options=new BitmapFactory.Options();
InputStream is = getContentResolver().openInputStream(currImageURI);
bm = BitmapFactory.decodeStream(is,null,options);
int Height = bm.getHeight();
int Width = bm.getWidth();
int newHeight = 300;
int newWidth = 300;
float scaleWidth = ((float) newWidth) / Width;
float scaleHeight = ((float) newHeight) / Height;
Matrix matrix = new Matrix();
matrix.postScale(scaleWidth, scaleHeight);
Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0,Width, Height, matrix, true);
BitmapDrawable bmd = new BitmapDrawable(resizedBitmap);*/

        return newBitmap;
    }
}
