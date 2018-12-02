package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.data.*;
import ca.klapstein.baudit.views.CareProviderHomeView;
import ca.klapstein.baudit.views.HomeView;
import ca.klapstein.baudit.views.PatientRowView;

/**
 * MVP presenter for presenting a {@code PatientTreeSet} on a {@code PatientListView}.
 *
 * @see HomeView
 * @see Account
 * @see PatientTreeSet
 */
public class CareProviderHomePresenter extends Presenter<CareProviderHomeView> {
    private static final String TAG = "CPHomePresenter";

    private CareProvider careProvider;

    public CareProviderHomePresenter(CareProviderHomeView view, Context context) {
        super(view, context);
        careProvider = dataManager.getLoggedInCareProvider();
    }

    /**
     * Attempt to assign a {@code Patient} with the given string specified username.
     * <p>
     * Fails if the given string username is invalid or if the username does not relate to any valid {@code Patient}
     * account.
     *
     * @param username {@code String}
     */
    public void assignPatient(String username) {
        try {
            careProvider.getAssignedPatientTreeSet().add(dataManager.getPatient(new Username(username)));
            dataManager.commitCareProvider(careProvider);
            view.updateList();
        } catch (Exception e) {
            Log.e(TAG, "failed to assign patient " + username, e);
            view.updateScanQRCodeError();
        }
    }

    public void onBindPatientRowViewAtPosition(PatientRowView rowView, int position) {
        if (careProvider == null || careProvider.getAssignedPatientTreeSet() == null) {
            view.updateAccountLoadError();
        } else {
            Patient patient = (Patient) careProvider.getAssignedPatientTreeSet().toArray()[position];
            rowView.updatePatientNameText(patient.getUsername().toString());
            rowView.updatePatientProblemNum(patient.getProblemTreeSet().size());
        }

    }

    public int getPatientCount() {
        if (careProvider == null || careProvider.getAssignedPatientTreeSet() == null) {
            view.updateAccountLoadError();
            return 0;
        } else {
            return careProvider.getAssignedPatientTreeSet().size();
        }
    }

    public void viewStarted() {
        careProvider = dataManager.getLoggedInCareProvider();
        if (careProvider == null) {
            view.updateAccountLoadError();
        } else {
            view.updateUsernameDisplay(careProvider.getUsername().toString());
            view.updateEmailDisplay(careProvider.getContactInfo().getEmail().toString());
            view.updateList();
        }
    }
}
