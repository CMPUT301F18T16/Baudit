package ca.klapstein.baudit.presenters;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.events.BauditRemoteManager.LoginVerdict;
import ca.klapstein.baudit.events.LoginPatientActivity.PatientLogInButtonClicked;
import ca.klapstein.baudit.events.LoginPresenter.NotifyLogInFailed;
import ca.klapstein.baudit.events.LoginPresenter.NotifyLogInSucceeded;
import ca.klapstein.baudit.events.LoginPresenter.ValidateLoginInformation;
import ca.klapstein.baudit.managers.BauditRemoteManager;
import ca.klapstein.baudit.views.LoginView;
import ca.klapstein.baudit.views.LogoutView;

/**
 * MVP presenter for presenting a {@code LoginView}.
 * <p>
 * Provides the controlling logic for logging in a {@code Account} into the application and remote.
 *
 * @see Account
 * @see LogoutView
 */
public class LoginPresenter extends Presenter<LoginView> {
    private static final String TAG = "LoginPresenter";

    private final EventBus bus;

    private BauditRemoteManager remoteManager;
    private Account account;

    public LoginPresenter(LoginView view, EventBus bus) {
        super(view);
        this.bus = bus;
        this.remoteManager = new BauditRemoteManager(bus);
    }

    /**
     * Send an event to the BauditRemoteManager to validate login information
     *
     * @param event {@code PatientLoginButtonClicked}
     */
    @Subscribe
    public void validateLogin(PatientLogInButtonClicked event) {
        bus.post(new ValidateLoginInformation(event.getUsername(), event.getPassword()));
    }

    @Subscribe
    public void onLoginVerdict(LoginVerdict event) {
        if (event.isLoginValid()) {
            bus.post(new NotifyLogInSucceeded());
        } else {
            bus.post(new NotifyLogInFailed());
        }

    }
}
