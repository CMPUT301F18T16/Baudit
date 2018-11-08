package ca.klapstein.baudit.views;

import ca.klapstein.baudit.events.NotifyLogInFailed;
import ca.klapstein.baudit.events.NotifyLogInSucceeded;

public interface LoginView extends View {
    void onLogInSuccess(NotifyLogInSucceeded event);

    void onLogInFailure(NotifyLogInFailed event);
}
