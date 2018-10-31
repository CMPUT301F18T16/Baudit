package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;

/**
 * Activity for displaying and choosing a location on a map.
 */
public class LocationActivity extends AppCompatActivity {
    private static final String TAG = "LocationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    }

    public interface onLocationSetListener {
        void onLocationSet(); // TODO: define
    }
}
