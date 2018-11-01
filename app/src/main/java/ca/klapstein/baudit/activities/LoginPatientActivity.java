package ca.klapstein.baudit.activities;

import android.os.Bundle;
import ca.klapstein.baudit.R;

/**
 * Activity presenting a login page for a Patient Baudit. Providing a page for the {@code Patient}
 * to login with their {@code Username} or {@code Email} and {@code Password} combination.
 *
 * @see ca.klapstein.baudit.data.Username
 * @see ca.klapstein.baudit.data.Email
 * @see ca.klapstein.baudit.data.Password
 * @see ca.klapstein.baudit.data.Patient
 */
public class LoginPatientActivity extends LoginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);
    }

    public void onLogin() {
        // TODO: implement
    }
}
