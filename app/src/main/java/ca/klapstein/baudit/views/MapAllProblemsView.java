package ca.klapstein.baudit.views;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

import ca.klapstein.baudit.data.Patient;

public interface MapAllProblemsView extends View {
    void updateMarkerOptions(MarkerOptions marker);
    void updateMapError();
    void populateMap(Patient patient, GoogleMap googleMap);
}
