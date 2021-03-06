package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.Username;
import ca.klapstein.baudit.views.CareProviderProblemListView;
import ca.klapstein.baudit.views.ProblemRowView;

public class CareProviderProblemListPresenter extends ProblemListPresenter<CareProviderProblemListView> {

    private static final String TAG = "ProblemListPresenter";

    public CareProviderProblemListPresenter(@NonNull CareProviderProblemListView view,
                                            @NonNull Context context) {
        super(view, context);
    }

    public void viewStarted(@NonNull String patientUsername) {
        try {
            patient = dataManager.getPatient(new Username(patientUsername));
            problemTreeSet.clear();
            problemTreeSet.addAll(patient.getProblemTreeSet());
            view.updateList();
        } catch (Exception e) {
            Log.e(TAG, "failed to obtain patient account info", e);
            view.updateAccountLoadError();
        }
    }

    public void onBindProblemRowViewAtPosition(@NonNull ProblemRowView rowView, int position) {
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
