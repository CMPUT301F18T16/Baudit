package ca.klapstein.baudit.presenters;

import android.content.Context;

import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.views.CareProviderProblemListView;

public class CareProviderProblemListPresenter extends Presenter<CareProviderProblemListView> {
    private static final String TAG = "CareProviderProblemListPresenter";
    private Patient patient;



    public CareProviderProblemListPresenter(CareProviderProblemListView view, Context context) {
        super(view, context);
        patient = dataManager.getPatient(patientUsername);
    }
}
