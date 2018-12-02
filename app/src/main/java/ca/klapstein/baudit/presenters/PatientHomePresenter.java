package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.views.HomeView;

import java.util.Arrays;
import java.util.HashSet;

/**
 * MVP presenter for presenting a {@code Patient}'s home screen on a {@code HomeView}.
 *
 * @see HomeView
 * @see Account
 * @see ProblemTreeSet
 */
public class PatientHomePresenter extends Presenter<HomeView> {

    private static final String TAG = "PatientHomePresenter";
    private Patient patient;
    private ProblemTreeSet problemTreeSet = new ProblemTreeSet();

    public PatientHomePresenter(HomeView view, Context context) {
        super(view, context);
        patient = dataManager.getLoggedInPatient();
        if (patient == null) {
            view.updateAccountLoadError();
        } else {
            problemTreeSet.clear();
            problemTreeSet.addAll(patient.getProblemTreeSet());
        }
    }

    public Problem getProblemAt(int position) {
        return (Problem) problemTreeSet.toArray()[position];
    }

    public int getProblemCount() {
        if (patient == null || problemTreeSet == null) {
            view.updateAccountLoadError();
            return 0;
        } else {
            return problemTreeSet.size();
        }
    }

    public String getUsername() {
        return patient.getUsername().toString();
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
        } catch (Exception e) {
            Log.e(TAG, "failed to obtain patient account info", e);
            view.updateAccountLoadError();

        }
    }

    public void deleteProblemClicked(int position) {
        Problem deletedProblem = (Problem) problemTreeSet.toArray()[position];
        patient.getProblemTreeSet().remove(deletedProblem);
        dataManager.commitPatient(patient);
        view.updateList();
    }

    public void filterProblemsByTitle(CharSequence constraint) {
        try {
            patient = dataManager.getLoggedInPatient();
            problemTreeSet.clear();
            problemTreeSet.addAll(patient.getProblemTreeSet());
        } catch (Exception e) {
            Log.e(TAG, "failed to obtain patient account info", e);
        }

        String searchTitle = constraint.toString();
        patient = dataManager.getLoggedInPatient();
        HashSet<Integer> filterIndexes = new HashSet<>();
        Problem[] problemArray = problemTreeSet.toArray(new Problem[0]);
        for (int i = 0; i < problemArray.length; i++) {
            Problem problem = problemArray[i];
            if (!problem.getTitle().toLowerCase().contains(searchTitle.toLowerCase()))
                problemTreeSet.remove(problemArray[i]);
        }

        Log.d(TAG, "obtained filtered problems by title search: constraint: " + searchTitle + " indexes: " + Arrays.toString(filterIndexes.toArray()));
        return;
    }
}
