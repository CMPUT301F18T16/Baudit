package ca.klapstein.baudit.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.AddPhotoPresenter;
import ca.klapstein.baudit.views.AddPhotoView;

public class AddPhotoActivity extends AppCompatActivity implements AddPhotoView {

    AddPhotoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        presenter = new AddPhotoPresenter(this);
    }

    @Override
    public void setPhoto(Bitmap bitmap) {

    }

    @Override
    public void setPhotoError() {

    }

    @Override
    public void commitAddPhoto() {
        finish();
    }
}
