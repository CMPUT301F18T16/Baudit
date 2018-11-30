package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.views.LocationView;

/**
 * MVP presenter for presenting locational data.
 *
 * @see LocationView
 */
public class LocationPresenter extends Presenter<LocationView> {

    public LocationPresenter(LocationView view, Context context) {
        super(view, context);
    }

    public boolean validateLocation(long posx, long posy) {
        // TODO:
        return true;
    }
}
