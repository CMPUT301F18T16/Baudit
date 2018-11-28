package ca.klapstein.baudit.presenters;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;

import ca.klapstein.baudit.activities.MapAllProblemsActivity;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.views.MapAllProblemsView;

public class MapAllProblemsPresenter <V extends MapAllProblemsView> extends Presenter<V> {
    private Patient patient;

    public Patient getPatient(){return patient;}

    public MapAllProblemsPresenter(V view, Context context){
        super(view, context);
        patient = dataManager.getLoggedInPatient();
        if(patient == null ){
            //TODO: error
        }
    }
    public void viewStarted(){
        view.populateMap(patient);
    }
}
