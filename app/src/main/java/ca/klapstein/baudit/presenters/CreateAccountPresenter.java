package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.views.CreateAccountView;

abstract public class CreateAccountPresenter<V extends CreateAccountView> extends Presenter<V> {

    protected Account account;

    public CreateAccountPresenter(V view, Context context) {
        super(view, context);
    }

    public boolean validateBaseAccount(String username, String email) {
        // TODO:
        return true;
    }
}
