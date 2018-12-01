package ca.klapstein.baudit.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;
import android.location.Location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.GeoLocation;
import ca.klapstein.baudit.presenters.LocationPresenter;
import ca.klapstein.baudit.views.LocationView;


/**
 * Activity for displaying and choosing a location on a map.
 */
public class LocationActivity extends AppCompatActivity
        implements LocationView, OnMapReadyCallback {

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private static final String TAG = "LOCATION_ACTIVITY";
    private static final float defaultZoom = 20.0f;

    private MapView mapView;
    private LocationPresenter presenter;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private PlaceAutocompleteFragment autocompleteFragment;
    private GoogleMap googleMap;
    private GeoLocation geoLocation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = findViewById(R.id.location_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Add location");

        presenter = new LocationPresenter(this, getApplicationContext());

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.choose_location_map);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        LatLng sw = new LatLng(41.6751050889, -140.99778);
        LatLng ne = new LatLng(83.23324, -52.6480987209);
        autocompleteFragment.setBoundsBias(new LatLngBounds(sw, ne));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                geoLocation = new GeoLocation(
                        place.getName().toString(),
                        place.getLatLng().latitude,
                        place.getLatLng().longitude
                );
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    @Override
    protected void onResume(){
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

    /**
     * Called when pointer capture is enabled or disabled for the current window.
     *
     * @param hasCapture True if the window has pointer capture.
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void startSetLocation(GoogleMap googleMap) {
        Toast.makeText(this, "Set a Location", Toast.LENGTH_SHORT).show();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLng(56.1304,-106.3468)),0.0f));
        getDeviceLocation();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        //LatLng marker = new LatLng(current.getLatitude(), current.getLongitude());
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker,20.0f));
        //googleMap.addMarker(new MarkerOptions().position(marker).draggable(true));
        //GeoLocation geolocation = new GeoLocation(marker.latitude, marker.longitude);
    }

    private void getDeviceLocation(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            final Task location = fusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Log.d(TAG, "getDeviceLocation found location");
                        Location currentLocation = (Location) task.getResult();
                    }else{
                        Log.d(TAG, "getDeviceLocation could not find a location");
                        Toast.makeText(LocationActivity.this, "Current location not found", Toast.LENGTH_SHORT).show();
                        Location currentLocation = new Location("Default location");
                        currentLocation.setLatitude(53.5444);
                        currentLocation.setLongitude(-113.4909);
                    }
                }
            });

        }catch(SecurityException e){
            Log.e(TAG, "getDeviceLocation error" +e.getMessage());
        }
    }
    public void moveCamera(LatLng latLng, float zoom){
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //LatLng ny = new LatLng(40.7143528, -74.0059731);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        startSetLocation(googleMap);
    }
}
