package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.Password;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Username;
import ca.klapstein.baudit.activities.LoginCareProviderActivity;
import ca.klapstein.baudit.activities.LoginPatientActivity;
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
  
    private static String TAG = "LoginPresenter";

    private Context context;

    public LoginPresenter(LoginView view, Context context) {
        super(view, context);
        this.context = context;

        // If the user is already logged in, go to patient home without validation
        if (dataManager.getLoggedInAccount() != null) {
            this.view.onLoginValidationSuccess();
        }
        // attempt to login from local saved state
        processOfflineLoginAccount();
    }

    /**
     * Control logging in as a {@code CareProvider} from an offline state.
     */
    private void offlineLoginCareProvider(CareProvider careProvider) {
        if (view.getClass() == LoginPatientActivity.class) {
            view.switchLoginScreen();
        } else {
            dataManager.setLoginAccountUserName(careProvider.getUsername());
            view.onLoginValidationSuccess();
        }
    }

    /**
     * Control logging in as a {@code Patient} from an offline line state.
     */
    private void offlineLoginPatient(Patient patient) {
        if (view.getClass() == LoginCareProviderActivity.class) {
            view.switchLoginScreen();
        } else {
            dataManager.setLoginAccountUserName(patient.getUsername());
            view.onLoginValidationSuccess();
        }
    }

    /**
     * Control logging into a an arbitrary {@code Account} that was obtained from the saved login account within
     * Android's shared preferences. This allows for login from a offline state.
     */
    private void processOfflineLoginAccount() {
        // obtain the offline logged in account
        Account account = dataManager.getLoggedInAccount();
        if (account instanceof CareProvider) {
            offlineLoginCareProvider((CareProvider) account);
        } else if (account instanceof Patient) {
            offlineLoginPatient((Patient) account);
        } else {
            Log.e(TAG, "no offline login account or invalid account type for offline login");
            dataManager.clearLoginAccountUserName();
        }
    }

    /**
     * Attempt a login with a given user/pass combination with the remote authentication server.
     *
     * @param username {@code String}
     * @param password {@code String}
     */
    public void onLoginButtonClicked(String username, String password) {
        // get the account associated with the user and password
        Account account = dataManager.validateLogin(new Username(username), new Password(password));
        // if it is null we failed the login
        if (account == null) {
            view.onLoginValidationFailure(context.getResources().getString(R.string.login_failed));
            // if we are logging in a CareProvider ensure the account is actually a CareProvider
        } else if (view.getClass() == LoginCareProviderActivity.class &&
                dataManager.getCareProvider(account.getUsername()) != null) {
            dataManager.setLoginAccountUserName(account.getUsername());
            view.onLoginValidationSuccess();
            // if we are logging in a Patient ensure the account is actually a Patient
        } else if (view.getClass() == LoginPatientActivity.class &&
                dataManager.getPatient(account.getUsername()) != null) {
            dataManager.setLoginAccountUserName(account.getUsername());
            view.onLoginValidationSuccess();
        } else {  // else we have failed the login
            view.onLoginValidationFailure(context.getResources().getString(R.string.login_failed));
        }
    }
}
