package ca.klapstein.baudit.activities;

import android.os.Bundle;
import ca.klapstein.baudit.R;

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
    }

    public void onLogin() {
        // TODO: implement
    }
}
