package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;

import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.data.Username;
import ca.klapstein.baudit.views.CareProviderProblemListView;
import ca.klapstein.baudit.views.ProblemRowView;

public class CareProviderProblemListPresenter extends Presenter<CareProviderProblemListView> {

    private static final String TAG = "ProblemListPresenter";

    private Patient patient;

    private ProblemTreeSet problemTreeSet = new ProblemTreeSet();

    public CareProviderProblemListPresenter(CareProviderProblemListView view, Context context) {
        super(view, context);
    }

    public void viewStarted(String patientUsername) {
        try {
            patient = dataManager.getPatient(new Username(patientUsername));
            problemTreeSet.clear();
            problemTreeSet.addAll(patient.getProblemTreeSet());
            view.updateList();
        } catch (Exception e) {
            Log.e(TAG, "failed to obtain patient account info", e);
            Log.e(TAG, patientUsername);
            Log.e(TAG, patientUsername);
            Log.e(TAG, patientUsername);
            view.updateAccountLoadError();
        }
    }

    public int getProblemCount() {
        if (patient != null) {
            return problemTreeSet.size();
        } else {
            return 0;
        }
    }

    public void onBindProblemRowViewAtPosition(ProblemRowView rowView, int position) {
        try {
            Problem problem = (Problem) problemTreeSet.toArray()[position];
            rowView.updateProblemTitleText(problem.getTitle());
            rowView.updateProblemDateText(problem.getTimeStamp());
            rowView.updateProblemDescriptionText(problem.getDescription());
        } catch (Exception e) {
            Log.e(TAG, "failed to get problem data", e);
            view.updateAccountLoadError();
        }
    }
}
