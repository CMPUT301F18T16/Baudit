package ca.klapstein.baudit.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.SlideshowPresenter2;
import ca.klapstein.baudit.views.SlideshowView2;


public class SlideshowActivity extends AppCompatActivity implements SlideshowView2 {

    public static final String RECORD_PHOTO_RECORD_ID_FIELD = "recordId";
    public static final String RECORD_PHOTO_PROBLEM_ID_FIELD = "problemId";

    private ViewPager viewPager;
    private SlideshowAdapter adapter;
    private SlideshowPresenter2 presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        presenter = new SlideshowPresenter2(this, getApplicationContext());
        ArrayList<String> bitmapStringArray = presenter.getRecordBitmapStrings(getIntent().getIntExtra(RECORD_PHOTO_PROBLEM_ID_FIELD, -1),
                getIntent().getIntExtra(RECORD_PHOTO_RECORD_ID_FIELD, -1));

        //bitmapStringArray = getIntent().getStringArrayListExtra("BitmapStringArray");

        viewPager = findViewById(R.id.view_pager);

        if (bitmapStringArray.size() != 0) {
            adapter = new SlideshowAdapter(this, bitmapStringArray);
            viewPager.setAdapter(adapter);
        } else{
            finish();
            Toast.makeText(SlideshowActivity.this, "No Photos to Show", Toast.LENGTH_LONG ).show();
        }


    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
