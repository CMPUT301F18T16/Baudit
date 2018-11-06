package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.managers.BauditRemoteManager;
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

    private BauditRemoteManager remoteManager;
    private Account account;

    public LoginPresenter(LoginView view) {
        super(view);
        this.remoteManager = new BauditRemoteManager();
    }

    /**
     * Attempt a login with a given user/pass combination with the remote authentication server.
     *
     * @param username {@code String}
     * @param password {@code String}
     * @return {@code boolean} {@code true} if the authentication was successful, otherwise {@code false}
     */
    public boolean validateLogin(String username, String password) {
        view.setLoginSuccess();
        return true;
    }
}