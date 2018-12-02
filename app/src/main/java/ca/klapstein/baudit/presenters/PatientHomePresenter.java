package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.views.HomeView;

/**
 * MVP presenter for presenting a {@code Patient}'s home screen on a {@code HomeView}.
 *
 * @see HomeView
 * @see Account
 * @see ProblemTreeSet
 */
public class PatientHomePresenter extends HomePresenter<HomeView> {

    private Patient patient;

    public PatientHomePresenter(HomeView view, Context context) {
        super(view, context);
        patient = dataManager.getLoggedInPatient();
        if (patient == null) {
            // TODO: error
        }
    }

    public Problem getProblemAt(int position) {
        return (Problem) patient.getProblemTreeSet().toArray()[position];
    }

    public int getProblemCount() {
        if (patient == null || patient.getProblemTreeSet() == null) {
            // TODO: error
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
            // TODO: error
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
}
