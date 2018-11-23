package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.LoginPresenter;
/**
 * Activity presenting a login page for a Patient Baudit.
 *
 * @see ca.klapstein.baudit.data.Username
 * @see ca.klapstein.baudit.data.Email
 * @see ca.klapstein.baudit.data.Patient
 */
public class LoginPatientActivity extends LoginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login_patient);
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this, this);
    }

    @Override
    public void onLoginValidationSuccess() {
        startActivity(new Intent(LoginPatientActivity.this, PatientHomeActivity.class));
        finish();
    }

    @Override
    public void startRegistration() {
        Intent intent = new Intent(
                this,
                CreatePatientAccountActivity.class
        );
        startActivity(intent);
    }

    /**
     * Switch to the {@code CareProvider} login screen {@code LoginCareProviderActivity}.
     *
     * @see LoginCareProviderActivity
     */
    @Override
    public void switchLoginScreen() {
        startActivity(new Intent(this, LoginCareProviderActivity.class));
        finish();
    }
}

