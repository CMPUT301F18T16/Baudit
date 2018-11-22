package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.views.EditAccountView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Account} via a {@code EditAccountView}.
 *
 * @see Account
 * @see EditAccountView
 */
public class EditAccountPresenter extends Presenter<EditAccountView> {

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
            view.updateFields(
                    account.getUsername().toString(),
                    account.getContactInfo().getEmail().toString(),
                    account.getContactInfo().getPhoneNumber().toString()
            );
        } else {
            // TODO: error
        }
    }

    public void saveClicked(String name, String email, String phoneNumber) {
        // TODO: Commit the new information
    }
}
