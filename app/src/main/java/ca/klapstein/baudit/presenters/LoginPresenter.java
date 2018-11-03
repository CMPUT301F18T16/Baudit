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

    public LoginPresenter(LoginView view){
        super(view);
        this.remoteManager = new BauditRemoteManager();
    }

    public boolean validateLogin(String username, String password){
        return true;
    }
}
