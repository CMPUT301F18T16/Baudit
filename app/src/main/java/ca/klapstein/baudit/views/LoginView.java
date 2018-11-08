package ca.klapstein.baudit.views;

import ca.klapstein.baudit.events.LoginPresenter.NotifyLogInFailed;
import ca.klapstein.baudit.events.LoginPresenter.NotifyLogInSucceeded;

public interface LoginView extends View {
    void onLogInSuccess(NotifyLogInSucceeded event);

    void onLogInFailure(NotifyLogInFailed event);
}
