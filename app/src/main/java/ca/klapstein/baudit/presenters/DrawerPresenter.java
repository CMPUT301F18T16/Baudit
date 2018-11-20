package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.views.DrawerView;

public class DrawerPresenter extends Presenter<DrawerView> {
    private final Account account;

    public DrawerPresenter(DrawerView view, Context context) {
        super(view, context);
        account = dataManager.getLoggedInAccount();
    }

    public void viewStarted() {
        this.view.setUsername(account.getUsername().toString());
        this.view.setEmail(account.getContactInfo().getEmail().toString());
    }
}
