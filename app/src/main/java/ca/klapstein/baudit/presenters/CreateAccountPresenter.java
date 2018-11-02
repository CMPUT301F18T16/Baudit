package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.User;
import ca.klapstein.baudit.views.CreateUserView;

public class CreateAccountPresenter<V extends CreateUserView> extends Presenter<V> {
    private static final String TAG = "CreateAccountPresenter";

    protected User user;

    public CreateAccountPresenter(V view) {
        super(view);
    }

    public boolean validateBaseAccount(String username, String email) {
        return true;
    }
}
