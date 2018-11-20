package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Password;
import ca.klapstein.baudit.data.Username;
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
        Username loginUsername = dataManager.getLoginAccountUsername();
        // TODO: validate we are the proper account type for login screen
        if (loginUsername != null) { // if we already have a login token in share prefs proceed logging in
            this.view.onLoginValidationSuccess();
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
