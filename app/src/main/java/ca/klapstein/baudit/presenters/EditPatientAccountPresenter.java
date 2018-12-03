package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Email;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PhoneNumber;
import ca.klapstein.baudit.views.EditAccountView;
import ca.klapstein.baudit.views.EditPatientAccountView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Account} via a
 * {@code EditAccountView}.
 *
 * @see Account
 * @see EditAccountView
 */
public class EditPatientAccountPresenter extends Presenter<EditPatientAccountView> {

    private static final String TAG = "EditPatientPresenter";

    private Patient patient;

    public EditPatientAccountPresenter(EditPatientAccountView view, Context context) {
        super(view, context);
    }

    /**
     * On startup of the {@code EditAccountView} populate fields noting the account.
     */
    public void viewStarted() {
        try {
            patient = dataManager.getLoggedInPatient();
            view.updateFirstNameField(patient.getContactInfo().getFirstName());
            view.updateLastNameField(patient.getContactInfo().getLastName());
            view.updateEmailField(patient.getContactInfo().getEmail().toString());
            view.updatePhoneNumberField(patient.getContactInfo().getPhoneNumber().toString());
            view.updateBodyLocationField(patient.getBodyLocationPhotos());
        } catch (Exception e) {
            Log.e(TAG, "failed to present patient", e);
            view.updateViewAccountError();
        }
    }

    public void deleteBodyLocationClicked(int position) {
        try {
            patient = dataManager.getLoggedInPatient();
            patient.removeBodyLocationPhoto(position);
            dataManager.commitAccount(patient);
            view.updateBodyLocationField(patient.getBodyLocationPhotos());
        } catch (Exception e) {
            Log.e(TAG, "failed to delete body location", e);
            view.commitAccountEditFailure();
        }
    }

    public void saveClicked(String firstName, String lastName, String email, String phoneNumber) {
        boolean validAccount = true;
        if (!Email.isValid(email)) {
            validAccount = false;
            view.updateEmailError(context.getResources().getString(R.string.email_error));
        }

        if (!PhoneNumber.isValid(phoneNumber)) {
            validAccount = false;
            view.updatePhoneNumberError(
                    context.getResources().getString(R.string.phone_number_error)
            );
        }

        if (!validAccount) {
            view.commitAccountEditFailure();
            return;
        }

        try {
            patient = dataManager.getLoggedInPatient();
            patient.getContactInfo().setFirstName(firstName);
            patient.getContactInfo().setLastName(lastName);
            patient.getContactInfo().setEmail(new Email(email));
            patient.getContactInfo().setPhoneNumber(new PhoneNumber(phoneNumber));
            dataManager.commitAccount(patient);
            view.commitAccountEditSuccess();
        } catch (Exception e) {
            Log.e(TAG, "failed committing Account edits", e);
            view.commitAccountEditFailure();
        }
    }
}
