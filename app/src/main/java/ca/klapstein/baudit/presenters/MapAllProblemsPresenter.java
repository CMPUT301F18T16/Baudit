package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.data.*;
import ca.klapstein.baudit.views.MapAllProblemsView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapAllProblemsPresenter extends Presenter<MapAllProblemsView> {
    private static final String TAG = "MapProblemsPresenter";

    private Patient patient;

    public Patient getPatient(){return patient;}

    public MapAllProblemsPresenter(MapAllProblemsView view, Context context) {
        super(view, context);
        patient = dataManager.getLoggedInPatient();
        if (patient == null) {
            view.updateMapError();
        }
    }

    public void viewStarted() {
        patient = dataManager.getLoggedInPatient();
        try {
            ProblemTreeSet problemTreeSet = patient.getProblemTreeSet();
            for (Problem problem : problemTreeSet) {
                RecordTreeSet recordTreeSet = problem.getRecordTreeSet();
                for (Record record : recordTreeSet) {
                    if (record.getGeoLocation() != null) {  // not all records have a geo-location
                        LatLng marker = new LatLng(record.getGeoLocation().getLat(), record.getGeoLocation().getLon());
                        Log.d(TAG, "adding marker: " + marker.toString());
                        view.updateMarkerOptions(new MarkerOptions().position(marker).title(record.getTitle()));
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "error populating problem map", e);
            view.updateMapError();
        }
    }
}
