package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.views.HomeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

    @NonNull
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
        return problemTreeSet.size();
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
        problemTreeSet.remove(deletedProblem);
        patient.getProblemTreeSet().remove(deletedProblem);
        dataManager.commitPatient(patient);
        view.updateList();
    }

    public void filterProblemsByKeyWords(CharSequence constraint) {
        try {
            patient = dataManager.getLoggedInPatient();
            problemTreeSet.clear();
            problemTreeSet.addAll(patient.getProblemTreeSet());
        } catch (Exception e) {
            Log.e(TAG, "failed to obtain patient account info", e);
        }

        ArrayList<String> searchTokens = new ArrayList<>(Arrays.asList(constraint.toString().toLowerCase().split(" ")));
        Log.d(TAG, "filtering with tokens: " + searchTokens);
        patient = dataManager.getLoggedInPatient();
        Problem[] problemArray = problemTreeSet.toArray(new Problem[0]);
        for (Problem aProblemArray : problemArray) {
            if (Collections.disjoint(searchTokens, aProblemArray.getKeywords()))
                problemTreeSet.remove(aProblemArray);
        }
    }
}
