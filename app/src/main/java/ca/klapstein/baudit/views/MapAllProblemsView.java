package ca.klapstein.baudit.views;

import com.google.android.gms.maps.GoogleMap;

import ca.klapstein.baudit.data.Patient;

public interface MapAllProblemsView extends View {
    void populateMap(Patient patient, GoogleMap googleMap);
}
