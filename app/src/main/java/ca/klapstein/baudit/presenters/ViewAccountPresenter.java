package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Username;
import ca.klapstein.baudit.views.ViewAccountView;

public class ViewAccountPresenter extends Presenter<ViewAccountView> {
    private static final String TAG = "ViewAccountPresenter";

    public ViewAccountPresenter(ViewAccountView view, Context context) {
        super(view, context);
    }

    public void viewStarted(String username) {
        try {
            Account account = dataManager.getPatient(new Username(username));
            if (account == null) {
                account = dataManager.getCareProvider(new Username(username));
            }
            view.updateUsernameDisplay(account.getUsername().toString());
            view.updateNameDisplay(
                    account.getContactInfo().getFirstName(),
                    account.getContactInfo().getLastName()
            );
            view.updateEmailDisplay(account.getContactInfo().getEmail().toString());
            view.updatePhoneNumberDisplay(account.getContactInfo().getPhoneNumber().toString());
        } catch (Exception e) {
            Log.e(TAG, "failed to present account", e);
            view.updateViewAccountError();
        }
    }
}
