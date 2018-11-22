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
        return patient.getProblemTreeSet().size();
    }

    /**
     * Get the latest {@code ProblemTreeSet} of the logged in {@code Patient} and call updates to the respective
     * components of the {@code HomeView}.
     * <p>
     * Note: this method should be called when the {@code HomeView} is started.
     */
    public void viewStarted() {
        patient = dataManager.getLoggedInPatient();
        if (patient != null) {
            view.updateUsernameDisplay(patient.getUsername().toString());
            view.updateEmailDisplay(patient.getContactInfo().getEmail().toString());
            view.updateList();
        } else {
            // TODO: error
        }
    }
}
