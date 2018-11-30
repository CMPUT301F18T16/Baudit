package ca.klapstein.baudit.views;

import com.google.android.gms.maps.GoogleMap;

public interface LocationView extends View {
    void onMapReady(GoogleMap googleMap);
    void updateLocation(); // TODO

    void updateLocationError();
}
