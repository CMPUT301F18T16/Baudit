package ca.klapstein.baudit.managers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ca.klapstein.baudit.events.BauditRemoteManager.LoginVerdict;
import ca.klapstein.baudit.events.LoginPresenter.ValidateLoginInformation;

/**
 * Helper class for managing Baudit's remote (i.e. ElasticSearch) usage.
 */
public class BauditRemoteManager {
    private static final String TAG = "BauditRemoteManager";
    private final EventBus bus;

    public BauditRemoteManager(EventBus bus) {
        this.bus = bus;
    }

    public boolean uniqueID(String username) {
        // TODO: implement uniqueness checking of a userid given a string
        return true;
    }

    @Subscribe
    public void onLoginValidationRequested(ValidateLoginInformation event) {
        bus.post(new LoginVerdict(true)); // TODO: Properly Validate
    }
}
