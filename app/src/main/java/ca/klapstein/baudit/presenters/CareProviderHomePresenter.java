package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PatientTreeSet;
import ca.klapstein.baudit.views.HomeView;
import ca.klapstein.baudit.views.PatientRowView;

/**
 * MVP presenter for presenting a {@code PatientTreeSet} on a {@code PatientListView}.
 *
 * @see HomeView
 * @see Account
 * @see PatientTreeSet
 */
public class CareProviderHomePresenter extends HomePresenter<HomeView> {

    private Account account;
    private PatientTreeSet patientTreeSet;

    public CareProviderHomePresenter(HomeView view, Context context) {
        super(view, context);
        account = dataManager.getLoggedInAccount();
        patientTreeSet = dataManager.getPatients();
    }

    public void onBindPatientRowViewAtPosition(PatientRowView rowView, int position) {
        Patient patient = (Patient) patientTreeSet.toArray()[position];
        rowView.setPatientNameText(patient.getUsername().toString());
    }

    public int getPatientCount() {
        return patientTreeSet.size();
    }

    public void viewStarted() {
        account = dataManager.getLoggedInAccount();
        view.updateUsernameDisplay(account.getUsername().toString());
        view.updateEmailDisplay(account.getContactInfo().getEmail().toString());
        view.updateList();
    }
}
