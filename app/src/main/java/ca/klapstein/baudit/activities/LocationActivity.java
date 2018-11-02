package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.LocationPresenter;
import ca.klapstein.baudit.views.LocationView;

/**
 * Activity for displaying and choosing a location on a map.
 */
public class LocationActivity extends AppCompatActivity implements LocationView {
    private static final String TAG = "LocationActivity";

    private LocationPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        presenter = new LocationPresenter(this);
    }

    @Override
    public void setLocation() {

    }

    @Override
    public void setLocationError() {

    }
}
