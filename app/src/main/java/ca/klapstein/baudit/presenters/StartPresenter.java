package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.activities.CareProviderHomeActivity;
import ca.klapstein.baudit.activities.PatientHomeActivity;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Username;
import ca.klapstein.baudit.views.LogoutView;
import ca.klapstein.baudit.views.StartView;

/**
 * MVP presenter for presenting a {@code StartView}.
 * <p>
 * Provides the controlling logic for logging in a {@code Account} into the application and remote.
 *
 * @see Account
 * @see LogoutView
 */
public class StartPresenter extends Presenter<StartView> {

    private static String TAG = "StartPresenter";

    /**
     * Construction a {@code StartPresenter}.
     * <p>
     * On construction attempt to do an offline login through {@code processOfflineLoginAccount}.
     *
     * @param view    {@code StartView}
     * @param context {@code Context}
     */
    public StartPresenter(StartView view, Context context) {
        super(view, context);
        processOfflineLoginAccount(); // attempt to login from local saved state
    }

    /**
     * Control logging into a an arbitrary {@code Account} that was obtained from the saved login
     * account within Android's shared preferences. This allows for login from a offline state.
     */
    private void processOfflineLoginAccount() {
        // obtain the offline logged in account
        Account account = dataManager.getLoggedInAccount();
        if (account instanceof  Patient) {
            view.onLoginValidationSuccess(PatientHomeActivity.class);
        } else if (account instanceof CareProvider) {
            view.onLoginValidationSuccess(CareProviderHomeActivity.class);
        } else {
            Log.e(TAG, "no offline login account or invalid account type for offline login");
            dataManager.clearOfflineLoginAccount();
        }
    }

    /**
     * Attempt a login with a given user/pass combination with the remote authentication server.
     *
     * @param username {@code String}
     */
    public void onQRCodeScanned(String username) {
        try {
            // get the account associated with the user and password
            Account account = dataManager.validateLogin(new Username(username));
            if (account instanceof Patient) {
                dataManager.setOfflineLoginAccount(account);
                view.onLoginValidationSuccess(PatientHomeActivity.class);
            } else if (account instanceof CareProvider) {
                dataManager.setOfflineLoginAccount(account);
                view.onLoginValidationSuccess(CareProviderHomeActivity.class);
            } else {  // else we have failed the login
                // TODO: update text
                view.onLoginValidationFailure(context.getResources().getString(R.string.login_failed) + ": " + username);
            }
        } catch (IllegalArgumentException e) {
            // TODO: update text
            view.onLoginValidationFailure(context.getResources().getString(R.string.login_failed) + ": " + username);
        }
    }
}
