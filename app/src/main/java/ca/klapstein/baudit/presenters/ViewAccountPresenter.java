package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Username;
import ca.klapstein.baudit.views.ViewAccountView;

public class ViewAccountPresenter extends Presenter<ViewAccountView> {
    public ViewAccountPresenter(ViewAccountView view, Context context) {
        super(view, context);
    }

    public void viewStarted(String username) {
        // TODO: cleanup
        Account account = dataManager.getPatient(new Username(username));
        if (account == null) {
            account = dataManager.getCareProvider(new Username(username));
        }
        if (account != null) {
            view.updateUsernameDisplay(account.getUsername().toString());
            view.updateNameDisplay(
                account.getContactInfo().getFirstName(),
                account.getContactInfo().getLastName()
            );
            view.updateEmailDisplay(account.getContactInfo().getEmail().toString());
            view.updatePhoneNumberDisplay(account.getContactInfo().getPhoneNumber().toString());
        } else {
            view.updateViewAccountError();
        }
    }
}
