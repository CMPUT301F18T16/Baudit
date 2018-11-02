package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.LocationView;

/**
 * MVP presenter for presenting locational data.
 *
 * @see LocationView
 */
public class LocationPresenter {
    private static final String TAG = "LocationPresenter";

    private LocationView view;

    public LocationPresenter(LocationView view) {
        this.view = view;
    }
}
