package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.Email;
import ca.klapstein.baudit.data.PhoneNumber;
import ca.klapstein.baudit.views.EditAccountView;
import ca.klapstein.baudit.views.EditCareProviderAccountView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Account} via a
 * {@code EditAccountView}.
 *
 * @see Account
 * @see EditAccountView
 */
public class EditCareProviderAccountPresenter extends Presenter<EditCareProviderAccountView> {

    private static final String TAG = "EditPatientAccountPresenter";

    private CareProvider careProvider;

    public EditCareProviderAccountPresenter(EditCareProviderAccountView view, Context context) {
        super(view, context);
        careProvider = (CareProvider) dataManager.getLoggedInAccount();
    }

    /**
     * On startup of the {@code EditAccountView} populate fields noting the account.
     */
    public void viewStarted() {
        careProvider = (CareProvider) dataManager.getLoggedInAccount();
        if (careProvider != null) {
            view.updateFirstNameField(careProvider.getContactInfo().getFirstName());
            view.updateLastNameField(careProvider.getContactInfo().getLastName());
            view.updateEmailField(careProvider.getContactInfo().getEmail().toString());
            view.updatePhoneNumberField(careProvider.getContactInfo().getPhoneNumber().toString());
        } else {
            // TODO: error
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
            careProvider = (CareProvider) dataManager.getLoggedInAccount();
            careProvider.getContactInfo().setFirstName(firstName);
            careProvider.getContactInfo().setLastName(lastName);
            careProvider.getContactInfo().setEmail(new Email(email));
            careProvider.getContactInfo().setPhoneNumber(new PhoneNumber(phoneNumber));
            dataManager.commitAccount(careProvider);
            view.commitAccountEditSuccess();
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "failed committing Account edits", e);
            view.commitAccountEditFailure();
        } catch (NullPointerException e) {
            Log.e(TAG, "failed to obtain account to commit Account edits too", e);
            view.commitAccountEditFailure();
        }
    }
}