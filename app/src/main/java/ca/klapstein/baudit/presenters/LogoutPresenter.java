package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.User;
import ca.klapstein.baudit.views.LogoutView;

/**
 * MVP presenter for presenting a {@code LogoutView}.
 * <p>
 * Provides the controlling logic for logging out a {@code User} from the application and remote.
 *
 * @see User
 * @see LogoutView
 */
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
