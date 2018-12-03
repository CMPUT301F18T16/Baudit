package ca.klapstein.baudit.views;

import android.location.Address;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public interface LocationView extends View {
    void startSetLocation(GoogleMap googleMap);
    void init();
    void confirmLocation(Address address);
    Address geoLocate();
    void getDeviceLocation();
    void moveCamera(LatLng latLng, float zoom, String title);
    void hideSoftKeyboard();

}
