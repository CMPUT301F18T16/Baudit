package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Email;
import ca.klapstein.baudit.data.PhoneNumber;
import ca.klapstein.baudit.views.EditAccountView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Account} via a {@code EditAccountView}.
 *
 * @see Account
 * @see EditAccountView
 */
public class EditAccountPresenter extends Presenter<EditAccountView> {
    private static final String TAG = "EditAccountPresenter";

    private Account account;

    public EditAccountPresenter(EditAccountView view, Context context) {
        super(view, context);
        account = dataManager.getLoggedInAccount();
    }

    /**
     * On startup of the {@code EditAccountView} populate fields noting the account.
     */
    public void viewStarted() {
        account = dataManager.getLoggedInAccount();
        if (account != null) {
            view.updateFirstNameField(account.getContactInfo().getFirstName());
            view.updateLastNameField(account.getContactInfo().getLastName());
            view.updateEmailField(account.getContactInfo().getEmail().toString());
            view.updatePhoneNumberField(account.getContactInfo().getPhoneNumber().toString());
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
            account = dataManager.getLoggedInAccount();
            account.getContactInfo().setFirstName(firstName);
            account.getContactInfo().setLastName(lastName);
            account.getContactInfo().setEmail(new Email(email));
            account.getContactInfo().setPhoneNumber(new PhoneNumber(phoneNumber));
            dataManager.commitAccount(account);
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
