package ca.klapstein.baudit.presenters;

import android.content.Context;

import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.views.LocationView;

/**
 * MVP presenter for presenting locational data.
 *
 * @see LocationView
 */
public class LocationPresenter <V extends LocationView>
        extends Presenter<V> {

    private Patient patient;
    public Patient getPatient(){return patient;}

    public LocationPresenter(V view, Context context) {
        super(view, context);
        patient = dataManager.getLoggedInPatient();
    }

    public boolean validateLocation(long posx, long posy) {
        if (posx>180 || posx<-180 || posy>90 || posy<-90){
            return false;
        }
        return true;
    }
}
