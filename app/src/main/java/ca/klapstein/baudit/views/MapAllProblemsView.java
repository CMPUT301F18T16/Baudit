package ca.klapstein.baudit.views;

import com.google.android.gms.maps.model.MarkerOptions;

public interface MapAllProblemsView extends View {
    void updateMarkerOptions(MarkerOptions marker);
    void updateMapError();
}
