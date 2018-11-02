package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.User;
import ca.klapstein.baudit.views.LoginView;
import ca.klapstein.baudit.views.LogoutView;

/**
 * MVP presenter for presenting a {@code LoginView}.
 * <p>
 * Provides the controlling logic for logging in a {@code User} into the application and remote.
 *
 * @see User
 * @see LogoutView
 */
public class LoginPresenter {
    private static final String TAG = "LoginPresenter";

    private LoginView view;
    private User user;

    public LoginPresenter(LoginView view){
        this.view = view;
    }

    public boolean validateLogin(String username, String password){
        // TODO: implement
        return true;
    }
}
