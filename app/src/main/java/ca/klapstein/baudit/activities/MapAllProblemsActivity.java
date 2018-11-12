package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.views.MapAllProblemsView;

public class MapAllProblemsActivity extends AppCompatActivity implements MapAllProblemsView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_all_problems);
        Toolbar toolbar = findViewById(R.id.map_all_problems_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.problem_locations);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void populateMap() {
        // Do something
    }
}
