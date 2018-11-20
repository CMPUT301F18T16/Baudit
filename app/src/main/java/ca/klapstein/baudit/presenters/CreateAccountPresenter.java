package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.views.CreateAccountView;

abstract public class CreateAccountPresenter<V extends CreateAccountView> extends Presenter<V> {
    private static final String TAG = "CreateAccountPresenter";

    protected Account account;

    public CreateAccountPresenter(V view, Context context) {
        super(view, context);
    }

    public boolean validateBaseAccount(String username, String email) {
        return true;
    }
}
