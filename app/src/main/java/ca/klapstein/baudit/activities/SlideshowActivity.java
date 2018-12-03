package ca.klapstein.baudit.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.SlideShowPresenter;
import ca.klapstein.baudit.views.SlideShowView;

import java.util.ArrayList;

import static ca.klapstein.baudit.util.BitmapRotater.RotateBitmap90;

public class SlideshowActivity extends AppCompatActivity implements SlideShowView {

    public static final String RECORD_PHOTO_RECORD_ID_FIELD = "recordId";
    public static final String RECORD_PHOTO_PROBLEM_ID_FIELD = "problemId";

    private ViewPager viewPager;
    private SlideshowAdapter adapter;
    private SlideShowPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        presenter = new SlideShowPresenter(this, getApplicationContext());
        ArrayList<Bitmap> bitmapArrayList = presenter.getRecordPhotoBitmaps(getIntent().getIntExtra(RECORD_PHOTO_PROBLEM_ID_FIELD, -1),
                getIntent().getIntExtra(RECORD_PHOTO_RECORD_ID_FIELD, -1));

        viewPager = findViewById(R.id.view_pager);

        if (bitmapArrayList.size() != 0) {
            adapter = new SlideshowAdapter(this, bitmapArrayList);
            viewPager.setAdapter(adapter);
        } else{
            Toast.makeText(SlideshowActivity.this, getResources().getString(R.string.no_photos_to_show), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void updateViewSlideShowError() {
        Toast.makeText(SlideshowActivity.this, getResources().getString(R.string.slideshow_view_error), Toast.LENGTH_LONG).show();
        finish();
    }

    /**
     * Adapter providing a "slideshow" of photos to be easily viewed.
     */
    private class SlideshowAdapter extends PagerAdapter {
        @NonNull
        private ArrayList<Bitmap> bitmapArrayList = new ArrayList<>();
        private Context context;
        private LayoutInflater layoutInflater;

        public SlideshowAdapter(Context context, ArrayList<Bitmap> bitmapArrayList) {
            this.context = context;
            this.bitmapArrayList.addAll(bitmapArrayList);
        }

        @Override
        public int getCount() {
            //TODO: Get length of the photos stored in bitmap Array
            // return image_resource.length; TEST
            return bitmapArrayList.size();
        }

        /**
         * Determines whether a page View is associated with a specific key object as returned by instantiateItem(ViewGroup, int).
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return (view == object);
        }

        /**
         * Create the page for the given position
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item_view = layoutInflater.inflate(R.layout.slideshow_layout, container, false);

            ImageView imageView = item_view.findViewById(R.id.image_view);
            // imageView.setImageResource(image_resource[position]);  //TEST CASE

            //TODO:update empty bitmapArray & setImageResource to the images on the list
            imageView.setImageBitmap(RotateBitmap90(bitmapArrayList.get(position)));

            container.addView(item_view);
            return item_view;
        }

        /**
         * Remove a page for the given position
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ConstraintLayout) object);
        }
    }
}
