package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.LoginPresenter;

/**
 * Activity presenting a login page for a CareProvider Baudit.
 *
 * @see ca.klapstein.baudit.data.Username
 * @see ca.klapstein.baudit.data.Email
 * @see ca.klapstein.baudit.data.CareProvider
 */
public class LoginCareProviderActivity extends LoginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login_care_provider);
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this, this);
    }

    @Override
    public void startRegistration() {
        Intent intent = new Intent(
                this,
                CreateCareProviderAccountActivity.class
        );
        startActivity(intent);
    }

    @Override
    public void onLoginValidationSuccess() {
        startActivity(new Intent(this, CareProviderHomeActivity.class));
        finish();
    }

    /**
     * Switch to the {@code Patient} login screen {@code LoginPatientActivity}.
     *
     * @see LoginPatientActivity
     */
    @Override
    public void switchLoginScreen() {
        startActivity(new Intent(this, LoginPatientActivity.class));
        finish();
    }
}
