package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
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

    private Account account;

    public LoginPresenter(LoginView view, Context context) {
        super(view, context);
    }

    /**
     * Attempt a login with a given user/pass combination with the remote authentication server.
     *
     * @param username {@code String}
     * @param password {@code String}
     */
    public void onLoginButtonClicked(String username, String password) {
        if (dataManager.validateLogin(username, password)) {
            this.view.onLoginValidationSuccess();
        } else {
            this.view.onLoginValidationFailure();
        }
    }
}
