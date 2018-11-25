package ca.klapstein.baudit.presenters;

import android.content.Context;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.activities.CareProviderHomeActivity;
import ca.klapstein.baudit.activities.PatientHomeActivity;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Email;
import ca.klapstein.baudit.data.PhoneNumber;
import ca.klapstein.baudit.views.CreateAccountView;

public class CreateAccountPresenter extends Presenter<CreateAccountView> {

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

        if (username.length() < 8) {
            validAccount = false;
            view.updateUsernameError(context.getResources().getString(R.string.username_error));
        }

        // TODO: Check for username uniqueness
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
            if (checkedId == R.id.patient_radio_button) {
                view.onAccountConfirmed(PatientHomeActivity.class);
            } else if (checkedId == R.id.care_provider_radio_button) {
                view.onAccountConfirmed(CareProviderHomeActivity.class);
            }
        }
    }
}
