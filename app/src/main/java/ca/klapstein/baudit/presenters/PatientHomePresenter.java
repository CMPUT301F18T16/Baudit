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

    public PatientHomePresenter(HomeView view, Context context) {
        super(view, context);
        patient = dataManager.getLoggedInPatient();
        if (patient == null) {
            view.updateAccountLoadError();
        }
    }

    public Problem getProblemAt(int position) {
        return (Problem) patient.getProblemTreeSet().toArray()[position];
    }

    public int getProblemCount() {
        if (patient == null || patient.getProblemTreeSet() == null) {
            view.updateAccountLoadError();
            return 0;
        } else {
            return patient.getProblemTreeSet().size();
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
        patient = dataManager.getLoggedInPatient();
        if (patient == null) {
            view.updateAccountLoadError();
        } else {
            view.updateUsernameDisplay(patient.getUsername().toString());
            view.updateEmailDisplay(patient.getContactInfo().getEmail().toString());
            view.updateList();
        }
    }

    public void deleteProblemClicked(int position) {
        Problem deletedProblem = (Problem) patient.getProblemTreeSet().toArray()[position];
        patient.getProblemTreeSet().remove(deletedProblem);
        dataManager.commitPatient(patient);
        view.updateList();
    }

    public HashSet<Integer> filterProblemsByTitle(CharSequence constraint) {
        String searchTitle = constraint.toString();
        patient = dataManager.getLoggedInPatient();
        HashSet<Integer> filterIndexes = new HashSet<>();
        Problem[] problemArray = patient.getProblemTreeSet().toArray(new Problem[0]);
        for (int i = 0; i < problemArray.length; i++) {
            Problem problem = problemArray[i];

            if (problem.getTitle().toLowerCase().contains(searchTitle.toLowerCase()))
                filterIndexes.add(i);
        }
        Log.d(TAG, "obtained filtered problems by title search: constraint: " + searchTitle + " indexes: " + Arrays.toString(filterIndexes.toArray()));
        return filterIndexes;
    }
}
