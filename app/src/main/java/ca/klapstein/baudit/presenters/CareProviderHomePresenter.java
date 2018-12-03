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
            careProvider = dataManager.getLoggedInCareProvider();
            careProvider.getAssignedPatientTreeSet().add(dataManager.getPatient(new Username(username)));
            dataManager.commitCareProvider(careProvider);
            view.updateList();
        } catch (Exception e) {
            Log.e(TAG, "failed to assign patient " + username, e);
            view.updateScanQRCodeError();
        }
    }

    public void onBindPatientRowViewAtPosition(PatientRowView rowView, int position) {
        try {
            careProvider = dataManager.getLoggedInCareProvider();
            Patient patient = (Patient) careProvider.getAssignedPatientTreeSet().toArray()[position];
            rowView.updatePatientNameText(
                    patient.getContactInfo().getFirstName(),
                    patient.getContactInfo().getLastName()
            );
            rowView.updatePatientUsernameText(patient.getUsername().toString());
            rowView.updatePatientProblemNum(patient.getProblemTreeSet().size());
        } catch (Exception e) {
            Log.e(TAG, "failed to get assigned patient", e);
            view.updateAccountLoadError();
        }
    }

    public int getPatientCount() {
        try {
            careProvider = dataManager.getLoggedInCareProvider();
            return careProvider.getAssignedPatientTreeSet().size();
        } catch (Exception e) {
            view.updateAccountLoadError();
            return 0;
        }
    }

    public void viewStarted() {
        try {
            careProvider = dataManager.getLoggedInCareProvider();
            view.updateUsernameDisplay(careProvider.getUsername().toString());
            view.updateEmailDisplay(careProvider.getContactInfo().getEmail().toString());
            view.updateList();
        } catch (Exception e) {
            Log.e(TAG, "failed to present care provider", e);
            view.updateAccountLoadError();
        }
    }

    public String getUsername() {
        try {
            careProvider = dataManager.getLoggedInCareProvider();
            return careProvider.getUsername().toString();
        } catch (Exception e) {
            Log.e(TAG, "failed to get care provider username", e);
            view.updateAccountLoadError();
            return "Unknown";
        }
    }

    public void removePatientClicked(int position) {
        try {
            careProvider = dataManager.getLoggedInCareProvider();
            Patient deletedPatient = (Patient) careProvider.getAssignedPatientTreeSet().toArray()[position];
            careProvider.getAssignedPatientTreeSet().remove(deletedPatient);
            dataManager.commitCareProvider(careProvider);
            view.updateList();
        } catch (Exception e) {
            Log.e(TAG, "failed to remove paitent", e);
            view.updateRemovePatientError();
        }
    }

    public String getPatientUsername(int position) {
        try {
            careProvider = dataManager.getLoggedInCareProvider();
            Patient patient = (Patient) careProvider.getAssignedPatientTreeSet().toArray()[position];
            return patient.getUsername().toString();
        } catch (Exception e) {
            Log.e(TAG, "failed to get assigned patient username", e);
            view.updateAccountLoadError();
            return "Unknown";
        }
    }
}
