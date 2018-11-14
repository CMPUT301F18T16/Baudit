package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.views.EditAccountView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Account} via a {@code EditAccountView}.
 *
 * @see Account
 * @see EditAccountView
 */
public class EditAccountPresenter extends Presenter<EditAccountView> {

    protected Account account; // TODO: Perhaps this information should be pulled from remote

    public EditAccountPresenter(EditAccountView view) {
        super(view);
    }

    public void viewStarted() {
        // TODO: Get account details
        view.updateFields("Test", "help@me.com", "(780) 400-0400");
    }

    public void saveClicked(String name, String email, String phoneNumber) {
        // TODO: Commit the new information
    }
}
