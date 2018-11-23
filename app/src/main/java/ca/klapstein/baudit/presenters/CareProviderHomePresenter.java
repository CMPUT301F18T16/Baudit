package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.CareProvider;
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

    private CareProvider careProvider;
    public CareProviderHomePresenter(HomeView view, Context context) {
        super(view, context);
        careProvider = dataManager.getLoggedInCareProvider();
    }

    public void onBindPatientRowViewAtPosition(PatientRowView rowView, int position) {
        if (careProvider == null || careProvider.getAssignedPatientTreeSet() == null) {
            // TODO: error
        } else {
            Patient patient = (Patient) careProvider.getAssignedPatientTreeSet().toArray()[position];
            rowView.setPatientNameText(patient.getUsername().toString());
        }

    }

    public int getPatientCount() {
        if (careProvider == null || careProvider.getAssignedPatientTreeSet() == null) {
            // TODO: error
            return 0;
        } else {
            return careProvider.getAssignedPatientTreeSet().size();
        }
    }

    public void viewStarted() {
        careProvider = dataManager.getLoggedInCareProvider();
        if (careProvider == null) {
            // TODO: error
        } else {
            view.updateUsernameDisplay(careProvider.getUsername().toString());
            view.updateEmailDisplay(careProvider.getContactInfo().getEmail().toString());
            view.updateList();
        }
    }
}
