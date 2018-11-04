package ca.klapstein.baudit.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.PhotoPresenter;
import ca.klapstein.baudit.views.PhotoView;

/**
 * Activity providing a camera widget to take photos for use in Baudit.
 */
public class ViewPhotoActivity extends AppCompatActivity implements PhotoView {
    private static final String TAG = "ViewPhotoActivity";

    private PhotoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        this.presenter = new PhotoPresenter(this);
    }

    @Override
    public void setPhoto(Bitmap bitmap) {

    }

    @Override
    public void setPhotoError() {

    }

    @Override
    public void onStart() {

        super.onStart();
    }
}
