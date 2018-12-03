package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.views.HomeView;
import ca.klapstein.baudit.views.PatientHomeView;
import ca.klapstein.baudit.views.ProblemRowView;

/**
 * MVP presenter for presenting a {@code Patient}'s home screen on a {@code HomeView}.
 *
 * @see HomeView
 * @see Account
 * @see ProblemTreeSet
 */
public class PatientHomePresenter extends ProblemListPresenter<PatientHomeView> {

    private static final String TAG = "PatientHomePresenter";

    public PatientHomePresenter(PatientHomeView view, Context context) {
        super(view, context);
        patient = dataManager.getLoggedInPatient();
        if (patient == null) {
            view.updateAccountLoadError();
        } else {
            problemTreeSet.clear();
            problemTreeSet.addAll(patient.getProblemTreeSet());
        }
    }

    public int getProblemCount() {
        return problemTreeSet.size();
    }

    public void onBindProblemRowViewAtPosition(ProblemRowView rowView, int position) {
        try {
            Problem problem = problemTreeSet.toArray(new Problem[0])[position];
            rowView.updateProblemTitleText(problem.getTitle());
            rowView.updateProblemDateText(problem.getTimeStamp());
            rowView.updateProblemDescriptionText(problem.getDescription());
        } catch (Exception e) {
            Log.e(TAG, "failed to get problem data", e);
            view.updateAccountLoadError();
        }
    }

    public String getUsername() {
        try {
            return patient.getUsername().toString();
        } catch (Exception e) {
            Log.e(TAG, "failed to get patient username", e);
            view.updateAccountLoadError();
            return "Unknown User";
        }
    }

    /**
     * Get the latest {@code ProblemTreeSet} of the logged in {@code Patient} and call updates to the respective
     * components of the {@code HomeView}.
     * <p>
     * Note: this method should be called when the {@code HomeView} is started.
     */
    public void viewStarted() {
        try {
            patient = dataManager.getLoggedInPatient();
            view.updateUsernameDisplay(patient.getUsername().toString());
            view.updateEmailDisplay(patient.getContactInfo().getEmail().toString());
            problemTreeSet.clear();
            problemTreeSet.addAll(patient.getProblemTreeSet());
            view.updateList();
            view.updateProblemNumber(problemTreeSet.size());
        } catch (Exception e) {
            Log.e(TAG, "failed to obtain patient account info", e);
            view.updateAccountLoadError();
        }
    }

    public void deleteProblemClicked(int position) {
        try {
            Problem deletedProblem = (Problem) problemTreeSet.toArray()[position];
            problemTreeSet.remove(deletedProblem);
            patient.getProblemTreeSet().remove(deletedProblem);
            dataManager.commitPatient(patient);
            view.updateList();
            view.updateProblemNumber(problemTreeSet.size());
        } catch (Exception e) {
            Log.e(TAG, "failed to delete problem", e);
            view.updateDeleteProblemError();
        }
    }
}
