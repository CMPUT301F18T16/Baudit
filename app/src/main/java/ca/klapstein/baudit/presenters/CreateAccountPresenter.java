package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.activities.CareProviderHomeActivity;
import ca.klapstein.baudit.activities.PatientHomeActivity;
import ca.klapstein.baudit.data.*;
import ca.klapstein.baudit.views.CreateAccountView;

public class CreateAccountPresenter extends Presenter<CreateAccountView> {
    private static final String TAG = "CreateAccountPresenter";

    protected Account account;

    public CreateAccountPresenter(CreateAccountView view, Context context) {
        super(view, context);
    }

    public void confirmButtonClicked(int checkedId, String firstName, String lastName,
                                     String username, String email, String phoneNumber) {
        boolean validAccount = true;
        view.updateUsernameError("");
        view.updateEmailError("");
        view.updatePhoneNumberError("");

        Username newUsername = null;
        try {
            newUsername = new Username(username);
            if (!dataManager.uniqueID(newUsername)) {
                Log.e(TAG, "given non unique userID");
                validAccount = false;
                view.updateUsernameError(context.getResources().getString(R.string.username_error));
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "given invalid userID: " + username, e);
            validAccount = false;
            view.updateUsernameError(context.getResources().getString(R.string.username_error));
        }

        Email newEmail = null;
        try {
            newEmail = new Email(email);
        } catch (IllegalArgumentException e) {
            validAccount = false;
            view.updateEmailError(context.getResources().getString(R.string.email_error));
        }

        PhoneNumber newPhoneNumber = null;
        try {
            newPhoneNumber = new PhoneNumber(phoneNumber);
        } catch (IllegalArgumentException e) {
            validAccount = false;
            view.updatePhoneNumberError(
                context.getResources().getString(R.string.phone_number_error)
            );
        }

        if (validAccount) {
            ContactInfo contactInfo = new ContactInfo(
                firstName,
                lastName,
                newEmail,
                newPhoneNumber
            );

            if (checkedId == R.id.patient_radio_button) {
                Patient patient = new Patient(newUsername, contactInfo);
                dataManager.setOfflineLoginAccount(patient);
                dataManager.commitPatient(patient);
                view.onAccountConfirmed(PatientHomeActivity.class);
            } else if (checkedId == R.id.care_provider_radio_button) {
                CareProvider careProvider = new CareProvider(new Username(username), contactInfo);
                dataManager.setOfflineLoginAccount(careProvider);
                dataManager.commitCareProvider(careProvider);
                view.onAccountConfirmed(CareProviderHomeActivity.class);
            }
        }
    }
}
