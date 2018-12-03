package ca.klapstein.baudit.activities;

import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.RecordPresenter;
import ca.klapstein.baudit.views.RecordView;

import static ca.klapstein.baudit.activities.ProblemActivity.PROBLEM_POSITION_EXTRA;
import static ca.klapstein.baudit.activities.RecordActivity.RECORD_POSITION_EXTRA;

public class SlideshowActivity extends AppCompatActivity {

    ViewPager viewPager;
    SlideshowAdapter adapter;
    private ArrayList<String> bitmapStringArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        bitmapStringArray = getIntent().getStringArrayListExtra("BitmapStringArray");

        viewPager = findViewById(R.id.view_pager);
        adapter = new SlideshowAdapter(this, bitmapStringArray);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
