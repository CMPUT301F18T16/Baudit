package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.LocationView;

/**
 * MVP presenter for presenting locational data.
 *
 * @see LocationView
 */
public class LocationPresenter extends Presenter<LocationView> {
    private static final String TAG = "LocationPresenter";


    public LocationPresenter(LocationView view) {
        super(view);
    }
}
