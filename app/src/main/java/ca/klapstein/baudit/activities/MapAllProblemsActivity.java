package ca.klapstein.baudit.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.ContactInfo;
import ca.klapstein.baudit.data.Email;
import ca.klapstein.baudit.data.GeoLocation;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PhoneNumber;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordTreeSet;
import ca.klapstein.baudit.data.Username;
import ca.klapstein.baudit.presenters.MapAllProblemsPresenter;
import ca.klapstein.baudit.views.MapAllProblemsView;

import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapAllProblemsActivity extends AppCompatActivity
    implements MapAllProblemsView, OnMapReadyCallback {

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    private MapView mapView;
    private MapAllProblemsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_all_problems);
        Toolbar toolbar = findViewById(R.id.map_all_problems_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.problem_locations);

        presenter = new MapAllProblemsPresenter(this,getApplicationContext());

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.map_all_problems_map);
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
        // LatLng ny = new LatLng(40.7143528, -74.0059731);
        // googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        // populateMap(presenter.getPatient(), googleMap);
        testPopulateMap(googleMap);
    }

    public void populateMap(Patient patient, GoogleMap googleMap) {
        //https://developers.google.com/maps/documentation/android-sdk/map-with-marker
        ProblemTreeSet problemTreeSet = patient.getProblemTreeSet();
        for (Problem problem:problemTreeSet){
            RecordTreeSet recordTreeSet = problem.getRecordTreeSet();
            for(Record record: recordTreeSet){
                LatLng marker = new LatLng(record.getGeoLocation().getLat(),record.getGeoLocation().getLon());
                googleMap.addMarker(new MarkerOptions().position(marker).title(record.getTitle()));
            }
        }
    }

    public void testPopulateMap(GoogleMap googleMap){
        //create a test patient to store records and problems to be iterated
        Username testUsername = new Username("ThisIsATest");
        ContactInfo testContactInfo = new ContactInfo("Test", "McTest", new Email("test@gmail.com"), new PhoneNumber("7805551234"));
        Patient patient = new Patient(testUsername, testContactInfo);

        // add problems to the new patient's problem tree set
        ProblemTreeSet problemTreeSet = patient.getProblemTreeSet();
        Problem problem1 = new Problem("First problem","I can't feel my face");
        RecordTreeSet recordTreeSet = problem1.getRecordTreeSet();
        // add records to the problems recordtreeset
        Record record1 = new Record("First occurrence","This first happened on a Friday");
        record1.setGeoLocation(new GeoLocation(53.524074, -113.526378));
        Record record2 = new Record("Second occurrence", "This happened on a Saturday");
        record2.setGeoLocation(new GeoLocation(53.526835, -113.525717));
        recordTreeSet.add(record1);
        recordTreeSet.add(record2);
        problemTreeSet.add(problem1);
        // add problems to the new patient's problem tree set
        Problem problem2 = new Problem("Second problem","I still can't feel my face");
        RecordTreeSet recordTreeSet2 = problem2.getRecordTreeSet();
        // add records to the problems recordtreeset
        Record record3 = new Record("First occurrence","This again on Saturday");
        record1.setGeoLocation(new GeoLocation(53.527288, -113.529346));
        Record record4 = new Record("Second occurrence", "This happened on a Sunday");
        record2.setGeoLocation(new GeoLocation(53.522186, -113.526438));
        recordTreeSet2.add(record3);
        recordTreeSet2.add(record4);
        problemTreeSet.add(problem2);

        //iterate over the ProblemTreeSet's RecordTreeSet and post markers
        for (Problem problem:problemTreeSet){
            RecordTreeSet thisRecordTreeSet = problem.getRecordTreeSet();
            for(Record record: thisRecordTreeSet){
                LatLng marker = new LatLng(record.getGeoLocation().getLat(),record.getGeoLocation().getLon());
                googleMap.addMarker(new MarkerOptions().position(marker).title(record.getTitle()));
            }
        }

    }
}
