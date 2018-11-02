package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.User;
import ca.klapstein.baudit.views.View;

/**
 * MVP presenter for a {@code User}.
 */
abstract public class AccountPresenter<V extends View> extends Presenter<V> {
    private static final String TAG = "AccountPresenter";

    protected User user;

    public AccountPresenter(V view) {
        super(view);
    }
}
