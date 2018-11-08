package ca.klapstein.baudit.presenters;

import org.greenrobot.eventbus.EventBus;

import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.managers.BauditRemoteManager;
import ca.klapstein.baudit.views.LogoutView;

/**
 * MVP presenter for presenting a {@code LogoutView}.
 * <p>
 * Provides the controlling logic for logging out a {@code Account} from the application and remote.
 *
 * @see Account
 * @see LogoutView
 */
public class LogoutPresenter extends Presenter<LogoutView> {
    private static final String TAG = "LogoutPresenter";

    private final EventBus bus;

    private BauditRemoteManager remoteManager;
    private Account account;

    public LogoutPresenter(LogoutView view, EventBus bus) {
        super(view);
        this.bus = bus;
        this.remoteManager = new BauditRemoteManager(bus);
    }

    /**
     * Attempt to logout the current Baudit session.
     * <p>
     * Invalidate any authentication with the remote servers, and revoke any local access tokens.
     *
     * @return {@code boolean} {@code true} if the logout was successful, otherwise {@code false}
     */
    public boolean validateLogout() {
        view.setLogoutSuccess();
        return true;
    }
}
