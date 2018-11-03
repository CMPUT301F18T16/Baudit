package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.LoginPresenter;
import ca.klapstein.baudit.views.LoginView;

/**
 * Activity presenting a login page for a CareProvider Baudit. Providing a page for the {@code CareProvider}
 * to login with their {@code Username} or {@code Email} and {@code Password} combination.
 *
 * @see ca.klapstein.baudit.data.Username
 * @see ca.klapstein.baudit.data.Email
 * @see ca.klapstein.baudit.data.Password
 * @see ca.klapstein.baudit.data.CareProvider
 */
public class LoginCareProviderActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_care_provider);

        presenter = new LoginPresenter(this);
    }

    /**
     * Attempt a login as a {@code CareProvider}.
     *
     * @return {@code true} if the login was successful, otherwise return {@code false}.
     */
    public boolean login() {
        // TODO: implement
        return true;
    }

    @Override
    public void setUserNameError() {

    }

    @Override
    public void setPasswordError() {

    }

    /**
     * After logging in as a {@code CareProvider} start the {@code PatientListActivity}.
     *
     * @see PatientListActivity
     */
    @Override
    public void setLoginSuccess() {
        Intent intent = new Intent(this, PatientListActivity.class);
        startActivity(intent);
        finish();
    }
}
