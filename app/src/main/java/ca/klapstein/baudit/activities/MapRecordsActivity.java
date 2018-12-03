package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.MapRecordsPresenter;
import ca.klapstein.baudit.views.MapAllProblemsView;

public class MapRecordsActivity extends AppCompatActivity

    implements MapAllProblemsView, OnMapReadyCallback {

    public static final String MAP_RECORDS_MODE = "mode";
    public static final String MAP_RECORDS_USERNAME = "username";
    public static final String MAP_RECORDS_PROBLEM_POSITION = "problemPosition";

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    private MapView mapView;
    private MapRecordsPresenter presenter;
    private GoogleMap googleMap;
    private String mode;
    private String username;
    private int problemPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_records);

        Toolbar toolbar = findViewById(R.id.map_records_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.record_locations);

        presenter = new MapRecordsPresenter(this, getApplicationContext());

        mode = getIntent().getStringExtra(MAP_RECORDS_MODE);
        username = getIntent().getStringExtra(MAP_RECORDS_USERNAME);
        problemPosition = getIntent().getIntExtra(MAP_RECORDS_PROBLEM_POSITION, -1);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.map_records_map);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng edmonton = new LatLng(53.5408, -113.4926);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(edmonton, 10.0f));
        this.googleMap = googleMap;
        presenter.viewStarted(mode, username, problemPosition);
    }


    @Override
    public void updateMarkerOptions(MarkerOptions marker) {
        googleMap.addMarker(marker);
    }

    @Override
    public void updateMapError() {
        Toast.makeText(this, getResources().getString(R.string.problem_map_error), Toast.LENGTH_LONG).show();
    }
}
