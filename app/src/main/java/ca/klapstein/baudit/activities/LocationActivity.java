package ca.klapstein.baudit.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.PlaceAutoCompleteAdapter;
import ca.klapstein.baudit.data.PlaceInfo;
import ca.klapstein.baudit.presenters.LocationPresenter;
import ca.klapstein.baudit.views.LocationView;


/**
 * Activity for displaying and choosing a location on a map.
 */
public class LocationActivity extends AppCompatActivity
        implements LocationView, OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private static final String TAG = "LOCATION_ACTIVITY";
    private static final float defaultZoom = 10.0f;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-40, -168), new LatLng(71,136));
    private static final int REQUEST_GEOLOCATION = 123;


    private MapView mapView;
    private LocationPresenter presenter;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private PlaceAutocompleteFragment autocompleteFragment;
    private GoogleMap map;
    private AutoCompleteTextView searchText;
    private ImageView gpsButton;
    private PlaceAutoCompleteAdapter autoCompleteAdapter;
    private GeoDataClient geoDataClient;
    private PlaceDetectionClient placeDetectionClient;
    private GoogleApiClient googleApiClient;
    private PlaceInfo placeInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        presenter = new LocationPresenter(this, getApplicationContext());
        searchText = findViewById(R.id.search_field);
        gpsButton = findViewById(R.id.gps);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        mapView = findViewById(R.id.choose_location_map);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        startSetLocation(map);
    }

    public void startSetLocation(GoogleMap map) {
        Toast.makeText(this, "Set a Location", Toast.LENGTH_SHORT).show();
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
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(false);
        init();
        hideSoftKeyboard();
        Log.d(TAG,"Completed setLocation");
    }

    private void init(){
        googleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        geoDataClient = Places.getGeoDataClient(this,null);
        placeDetectionClient = Places.getPlaceDetectionClient(this, null);


        autoCompleteAdapter = new PlaceAutoCompleteAdapter(this, geoDataClient, LAT_LNG_BOUNDS,null);

        searchText.setAdapter(autoCompleteAdapter);
        searchText.setOnItemClickListener(autoCompleteClickListener);

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){
                    geoLocate();
                }
                return false;
            }
        });
        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDeviceLocation();
            }
        });
    }

    private void geoLocate(){
        String searchString = searchText.getText().toString();
        Geocoder geocoder = new Geocoder(LocationActivity.this);
        List<Address> list = new ArrayList<Address>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
        }
        if(list.size() > 0){
            Address address = list.get(0);
            Log.d(TAG, "geoLocate: found a location: " + address.toString());
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()),10f, address.getAddressLine(0));
        }
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
                        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), defaultZoom, "My Location");
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
    public void moveCamera(LatLng latLng, float zoom, String title){
        MarkerOptions marker = new MarkerOptions().position(latLng).title(title).draggable(false);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
        if(!(title.equals("My Location"))){
            map.addMarker(marker);
        }
        hideSoftKeyboard();
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private AdapterView.OnItemClickListener autoCompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            hideSoftKeyboard();
            final AutocompletePrediction item = autoCompleteAdapter.getItem(position);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(googleApiClient, placeId);
            placeResult.setResultCallback(updatePlaceDetailCallback);

        }
    };

    private ResultCallback<PlaceBuffer> updatePlaceDetailCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            Log.d(TAG, "Entered onResult method");
            if (!places.getStatus().isSuccess()) {
                places.release();
                return;
            }
            final Place place = places.get(0);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("Address", places.get(0).getName());
            resultIntent.putExtra("Latitude", places.get(0).getLatLng().latitude);
            resultIntent.putExtra("Longitude", places.get(0).getLatLng().longitude);
            setResult(RESULT_OK,resultIntent);
            Log.d(TAG, "Doing the thing");
            moveCamera(new LatLng(place.getViewport().getCenter().latitude, place.getViewport().getCenter().longitude), defaultZoom, placeInfo.getName());
            places.release();
        }
    };

}
