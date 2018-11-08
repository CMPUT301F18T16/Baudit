package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.views.AccountView;

/**
 * MVP presenter for a {@code Account}.
 */
abstract public class AccountPresenter<V extends AccountView> extends Presenter<V> {
    private static final String TAG = "AccountPresenter";

    protected Account account;

    public AccountPresenter(V view) {
        super(view);
    }

    public boolean validateUser(String username, String email) {
        return true;
    }
}
