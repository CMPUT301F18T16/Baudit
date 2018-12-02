package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.views.SlideShowView;

public class SlideshowActivity extends AppCompatActivity {



    ViewPager viewPager;
    SlideshowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        viewPager = findViewById(R.id.view_pager);
        adapter = new SlideshowAdapter(this);
        viewPager.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
