package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.activities.CareProviderHomeActivity;
import ca.klapstein.baudit.activities.PatientHomeActivity;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.ContactInfo;
import ca.klapstein.baudit.data.Email;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PhoneNumber;
import ca.klapstein.baudit.data.Username;
import ca.klapstein.baudit.models.DataModel;
import ca.klapstein.baudit.views.CreateAccountView;

public class CreateAccountPresenter extends Presenter<CreateAccountView> {
    private static final String TAG = "CreateAccountPresenter";

    protected Account account;

    public CreateAccountPresenter(CreateAccountView view, Context context) {
        super(view, context);
    }

    public void confirmButtonClicked(int checkedId, String firstName, String lastName,
                                     String usernameString, String email, String phoneNumber) {
        boolean validAccount = true;
        view.updateUsernameError("");
        view.updateEmailError("");
        view.updatePhoneNumberError("");

        if (!Username.isValid(usernameString)) {
            validAccount = false;
            view.updateUsernameError(context.getResources().getString(R.string.username_error));
        }

        if (!new DataModel(this.context).uniqueID( new Username(usernameString))) {
            validAccount = false;
            view.updateUsernameError(context.getResources().getString(R.string.username_taken_error));
        }

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

        if (validAccount) {
            Username username = new Username(usernameString);
            ContactInfo contactInfo = new ContactInfo(
                firstName,
                lastName,
                new Email(email),
                new PhoneNumber(phoneNumber)
            );

            if (checkedId == R.id.patient_radio_button) {
                Patient patient = new Patient(username, contactInfo);
                dataManager.setOfflineLoginAccount(patient);
                dataManager.commitPatient(patient);
                view.onAccountConfirmed(PatientHomeActivity.class);
            } else if (checkedId == R.id.care_provider_radio_button) {
                CareProvider careProvider = new CareProvider(username, contactInfo);
                dataManager.setOfflineLoginAccount(careProvider);
                dataManager.commitCareProvider(careProvider);
                view.onAccountConfirmed(CareProviderHomeActivity.class);
            }
        }
    }
}
