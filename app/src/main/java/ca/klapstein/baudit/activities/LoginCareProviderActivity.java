package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.LoginPresenter;

/**
 * Activity presenting a login page for a CareProvider Baudit. Providing a page for the {@code CareProvider}
 * to login with their {@code Username} or {@code Email} and {@code Password} combination.
 *
 * @see ca.klapstein.baudit.data.Username
 * @see ca.klapstein.baudit.data.Email
 * @see ca.klapstein.baudit.data.Password
 * @see ca.klapstein.baudit.data.CareProvider
 */
public class LoginCareProviderActivity extends LoginActivity {

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
    public boolean login(){
        // TODO: implement
        return true;
    }

    /**
     * After logging in as a {@code CareProvider} start the {@code PatientListActivity}.
     *
     * @see PatientListActivity
     */
    public void onLogin() {
        Intent intent = new Intent(this, PatientListActivity.class);
        startActivity(intent);
    }

    @Override
    public void setUserNameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void setLoginSuccess() {

    }
}
