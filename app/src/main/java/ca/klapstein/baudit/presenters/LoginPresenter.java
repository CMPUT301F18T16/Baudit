package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.activities.LoginCareProviderActivity;
import ca.klapstein.baudit.activities.LoginPatientActivity;
import ca.klapstein.baudit.data.*;
import ca.klapstein.baudit.views.LoginView;
import ca.klapstein.baudit.views.LogoutView;

/**
 * MVP presenter for presenting a {@code LoginView}.
 * <p>
 * Provides the controlling logic for logging in a {@code Account} into the application and remote.
 *
 * @see Account
 * @see LogoutView
 */
public class LoginPresenter extends Presenter<LoginView> {
    private static String TAG = "LoginPresenter";

    private Context context;

    /**
     * Construction a {@code LoginPresenter}.
     * <p>
     * On construction attempt to do an offline login through {@code processOfflineLoginAccount}.
     *
     * @param view    {@code LoginView} should be either a instance of {@code LoginPatientActivity} or
     *                {@code LoginCareProviderActivity}.
     * @param context {@code Context}
     */
    public LoginPresenter(LoginView view, Context context) {
        super(view, context);
        this.context = context;

        // attempt to login from local saved state
        processOfflineLoginAccount();
    }

    /**
     * Control logging in as a {@code CareProvider} from an offline state.
     */
    private void offlineLoginCareProvider() {
        if (view.getClass() == LoginCareProviderActivity.class) {
            view.onLoginValidationSuccess();
        } else {
            view.switchLoginScreen();
        }
    }

    /**
     * Control logging in as a {@code Patient} from an offline line state.
     */
    private void offlineLoginPatient() {
        if (view.getClass() == LoginPatientActivity.class) {
            view.onLoginValidationSuccess();
        } else {
            view.switchLoginScreen();
        }
    }

    /**
     * Control logging into a an arbitrary {@code Account} that was obtained from the saved login account within
     * Android's shared preferences. This allows for login from a offline state.
     */
    private void processOfflineLoginAccount() {
        // obtain the offline logged in account
        Account account = dataManager.getLoggedInAccount();
        if (account instanceof CareProvider) {
            offlineLoginCareProvider();
        } else if (account instanceof Patient) {
            offlineLoginPatient();
        } else {
            Log.e(TAG, "no offline login account or invalid account type for offline login");
            dataManager.clearOfflineLoginAccount();
        }
    }

    /**
     * Attempt a login with a given user/pass combination with the remote authentication server.
     *
     * @param username {@code String}
     * @param password {@code String}
     */
    public void onLoginButtonClicked(String username, String password) {
        try {
            // get the account associated with the user and password
            Account account = dataManager.validateLogin(new Username(username), new Password(password));
            // if it is null we failed the login
            if (view.getClass() == LoginCareProviderActivity.class && account instanceof CareProvider) {
                dataManager.setOfflineLoginAccount(account);
                view.onLoginValidationSuccess();
                // if we are logging in a Patient ensure the account is actually a Patient
            } else if (view.getClass() == LoginPatientActivity.class && account instanceof Patient) {
                dataManager.setOfflineLoginAccount(account);
                view.onLoginValidationSuccess();
            } else {  // else we have failed the login
                view.onLoginValidationFailure(context.getResources().getString(R.string.login_failed));
            }
        } catch (IllegalArgumentException e) {
            view.onLoginValidationFailure(context.getResources().getString(R.string.login_failed));
        }
    }
}
