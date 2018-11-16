package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
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

    private Account account;

    public LogoutPresenter(LogoutView view, Context context) {
        super(view, context);
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
