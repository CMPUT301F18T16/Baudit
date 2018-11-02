package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.User;
import ca.klapstein.baudit.views.LoginView;

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
