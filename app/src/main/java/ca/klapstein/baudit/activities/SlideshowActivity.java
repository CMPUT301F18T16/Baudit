package ca.klapstein.baudit.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import ca.klapstein.baudit.R;

public class SlideshowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        ArrayList<String> bitmapStringArray =
            getIntent().getStringArrayListExtra("BitmapStringArray");

        ViewPager viewPager = findViewById(R.id.view_pager);
        SlideshowAdapter adapter = new SlideshowAdapter(this, bitmapStringArray);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
