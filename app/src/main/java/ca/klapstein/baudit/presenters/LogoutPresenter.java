package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.User;
import ca.klapstein.baudit.views.LogoutView;

public class LogoutPresenter {
    private static final String TAG = "LogoutPresenter";

    private LogoutView view;
    private User user;

    public LogoutPresenter(LogoutView view){
        this.view = view;
    }

    public void validateLogout(){
        view.setLogoutSuccess();
    }
}
