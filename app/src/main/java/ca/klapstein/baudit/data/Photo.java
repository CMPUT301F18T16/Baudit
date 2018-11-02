package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.InputStream;
import java.net.MalformedURLException;

abstract public class Photo extends AsyncTask<String,Void, Bitmap> {
    private Bitmap bmp;

    public Photo(String filename){
        this.bmp = doInBackground(filename);
    }

    protected Bitmap doInBackground(String filename){
        Bitmap bitmap = null;
        try{
            InputStream input = new java.net.URL(filename).openStream();
            bitmap = BitmapFactory.decodeStream(input);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Image not loaded");
        }catch(MalformedURLException e){
            throw new IllegalArgumentException("Image not loaded");
        }catch(java.io.IOException e){
            throw new IllegalArgumentException("Image not loaded");
        }
        return bitmap;
    }
}
