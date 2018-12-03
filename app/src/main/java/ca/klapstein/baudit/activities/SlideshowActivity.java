package ca.klapstein.baudit.activities;

import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.models.DataModel;
import ca.klapstein.baudit.presenters.RecordPresenter;
import ca.klapstein.baudit.views.RecordView;

import static ca.klapstein.baudit.activities.CameraActivity.RECORD_PHOTO_PROBLEM_ID_FIELD;
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



        DataModel dataManager = new DataModel(this);
        Patient patient =  (Patient) dataManager.getLoggedInAccount();
        int problemPosition = getIntent().getIntExtra(RECORD_PHOTO_PROBLEM_ID_FIELD, -1);
        int recordPosition = getIntent().getIntExtra(RECORD_PHOTO_PROBLEM_ID_FIELD, -1);
        Problem problem = (Problem) patient.getProblemTreeSet().toArray()[problemPosition];
        Record record = (Record) problem.getRecordTreeSet().toArray()[recordPosition];

        bitmapStringArray = record.getPhotoBitmapStrings();

        //bitmapStringArray = getIntent().getStringArrayListExtra("BitmapStringArray");

        viewPager = findViewById(R.id.view_pager);
        adapter = new SlideshowAdapter(this, bitmapStringArray);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
