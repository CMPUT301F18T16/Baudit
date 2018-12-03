package ca.klapstein.baudit.activities;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.SlideshowPresenter;
import ca.klapstein.baudit.views.SlideshowView;

/**
 * Activity displaying slideshow
 */
public class SlideshowActivity extends AppCompatActivity implements SlideshowView {

    private SlideshowPresenter presenter;
    private LinearLayout slideshowLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        presenter = new SlideshowPresenter(this, getApplicationContext());

        slideshowLayout = findViewById(R.id.slideshow_list_layout);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted();
    }

    @Override
    public void updateImageList(@NonNull ArrayList<Bitmap> photos) {
        slideshowLayout.removeAllViews();

        for (Bitmap photo : photos) {
            CardView slideshowCard = (CardView) LayoutInflater.from(slideshowLayout.getContext())
                .inflate(R.layout.card_slideshow, slideshowLayout, false);
            ImageView imageView = slideshowCard.findViewById(R.id.slideshow_image);
            imageView.setImageBitmap(photo);

            slideshowLayout.addView(slideshowCard);
        }
    }
}
