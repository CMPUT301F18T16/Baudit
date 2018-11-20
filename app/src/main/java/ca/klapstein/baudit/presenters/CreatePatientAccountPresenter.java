package ca.klapstein.baudit.presenters;

import android.content.Context;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Email;
import ca.klapstein.baudit.data.PhoneNumber;
import ca.klapstein.baudit.views.CreateAccountView;

public class CreatePatientAccountPresenter extends CreateAccountPresenter<CreateAccountView> {

    private Context context;

    public CreatePatientAccountPresenter(CreateAccountView view, Context context) {
        super(view, context);
        this.context = context;
    }

    public void confirmButtonClicked(String firstName, String lastName, String username,
                                     String email, String phoneNumber, String password,
                                     String confirmPassord) {
        boolean validAccount = true;
        view.updateUsernameError("");
        view.updateEmailError("");
        view.updatePhoneNumberError("");
        view.updatePasswordError("");

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

        // TODO: Check validity of password (length, content, etc.)

        if (!password.equals(confirmPassord)) {
            validAccount = false;
            view.updatePasswordError(
                context.getResources().getString(R.string.password_match_error)
            );
        }

        if (validAccount) {
            view.onAccountConfirmed();
        }
    }
}
