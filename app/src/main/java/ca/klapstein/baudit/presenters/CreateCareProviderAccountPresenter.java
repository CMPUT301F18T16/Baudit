package ca.klapstein.baudit.presenters;

import android.content.Context;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Email;
import ca.klapstein.baudit.data.PhoneNumber;
import ca.klapstein.baudit.views.CreateCareProviderAccountView;

public class CreateCareProviderAccountPresenter extends CreateAccountPresenter<CreateCareProviderAccountView> {
    private static final String TAG = "CreateCareProviderAccountPresenter";

    private Context context;
    public CreateCareProviderAccountPresenter(CreateCareProviderAccountView view, Context context) {
        super(view, context);
        this.context = context;
    }


    public boolean validateCareProviderAccount(String username, String email, String careProviderID) {
        return true;
    }

    public void verifyButtonClicked(String id, String firstname, String lastname, String username, String email,
                                    String phonenumber, String password, String confirmpassword  ){
        boolean validAccount = true;

        view.updateCareProviderIdError("");
        view.updateUsernameError("");
        view.updateEmailError("");
        view.updatePhoneNumberError("");
        view.updatePasswordError("");

        // TODO: Check if CareProviderID Exists

        if (username.length() > 8 && username.length() <20) {
            validAccount = false;
            view.updateUsernameError(context.getResources().getString(R.string.username_error));
        }

        // TODO: Check for username uniqueness

        if (!Email.isValid(email)) {
            validAccount = false;
            view.updateEmailError(context.getResources().getString(R.string.email_error));
        }

        if (!PhoneNumber.isValid(phonenumber)) {
            validAccount = false;
            view.updatePhoneNumberError(
                    context.getResources().getString(R.string.phone_number_error)
            );
        }



        // TODO: Check validity of password (length, content, etc.)

        if(password.length() <25){
            validAccount = false;
            view.updatePasswordError(
                    context.getResources().getString(R.string.password_error)
            );
        }

        if (!password.equals(confirmpassword)) {
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
