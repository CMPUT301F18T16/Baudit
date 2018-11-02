package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.GeoLocation;
import ca.klapstein.baudit.views.LocationView;

/**
 * MVP presenter for presenting locational data.
 *
 * @see LocationView
 */
public class LocationPresenter extends Presenter<LocationView> {
    private static final String TAG = "LocationPresenter";

    public GeoLocation location;

    public LocationPresenter(LocationView view) {
        super(view);
    }

    public boolean validateLocation(long posx, long posy) {
        return true;
    }
}
