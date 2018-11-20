package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
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
    private static final String TAG = "LoginPresenter";

    public LoginPresenter(LoginView view, Context context) {
        super(view, context);
        // attempt to login from local saved state
        processLoginAccount(dataManager.getLoggedInAccount());
    }

    private void loginCareProvider() {
        if (view.getClass() == LoginPatientActivity.class) {
            this.view.switchLoginScreen();
        } else {
            this.view.onLoginValidationSuccess();
        }
    }

    private void loginPatient() {
        if (view.getClass() == LoginCareProviderActivity.class) {
            this.view.switchLoginScreen();
        } else {
            this.view.onLoginValidationSuccess();
        }
    }

    private void processLoginAccount(Account account) {
        if (account instanceof CareProvider) {
            loginCareProvider();
        } else if (account instanceof Patient) {
            loginPatient();
        } else {
            Log.e(TAG, "invalid account type for login");
            dataManager.clearLoginAccountUserName();
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
            Username loginUsername = new Username(username);
            if (dataManager.validateLogin(loginUsername, new Password(password))) {
                dataManager.setLoginAccountUserName(loginUsername);
                this.view.onLoginValidationSuccess();
            } else {
                this.view.onLoginValidationFailure();
            }
        } catch (IllegalArgumentException e) {
            this.view.onLoginValidationFailure();
        }
    }
}
