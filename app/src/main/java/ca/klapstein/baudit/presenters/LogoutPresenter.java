package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.User;
import ca.klapstein.baudit.managers.BauditRemoteManager;
import ca.klapstein.baudit.views.LogoutView;

/**
 * MVP presenter for presenting a {@code LogoutView}.
 * <p>
 * Provides the controlling logic for logging out a {@code User} from the application and remote.
 *
 * @see User
 * @see LogoutView
 */
public class LogoutPresenter extends Presenter<LogoutView> {
    private static final String TAG = "LogoutPresenter";

    private BauditRemoteManager remoteManager;
    private User user;

    public LogoutPresenter(LogoutView view){
        super(view);
        this.remoteManager = new BauditRemoteManager();
    }

    public void validateLogout(){
        view.setLogoutSuccess();
    }
}
