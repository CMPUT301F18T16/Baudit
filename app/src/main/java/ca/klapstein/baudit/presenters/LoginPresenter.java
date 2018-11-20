package ca.klapstein.baudit.presenters;

import android.content.Context;

import ca.klapstein.baudit.R;
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

    private Context context;

    public LoginPresenter(LoginView view, Context context) {
        super(view, context);
        this.context = context;

        // If the user is already logged in, go to patient home without validation
        if (dataManager.getLoggedInAccount() != null) {
            this.view.onLoginValidationSuccess();
        } else {
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
                view.onLoginValidationSuccess();
            } else {
                view.onLoginValidationFailure(
                    context.getResources().getString(R.string.login_failed)
                );
            }
        } catch (IllegalArgumentException e) {
            view.onLoginValidationFailure(
                context.getResources().getString(R.string.login_failed)
            );
        }
    }
}
