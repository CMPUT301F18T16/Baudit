package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.views.HomeView;

/**
 * Abstract Presenter class for presenting the home screen of a {@code Account}.
 *
 * @param <V> {@code extends HomeView}
 */
public abstract class HomePresenter<V extends HomeView> extends Presenter<V> {

    public HomePresenter(V view, Context context) {
        super(view, context);
    }

    /**
     * Attempt to logout the current Baudit session.
     * <p>
     * Invalidate any authentication with the remote servers, and revoke any local access tokens.
     */
    public void logoutClicked() {
        dataManager.clearOfflineLoginAccount();
    }
}
