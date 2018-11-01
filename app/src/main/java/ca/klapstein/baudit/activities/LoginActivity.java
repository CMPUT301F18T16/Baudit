package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.Data.User;
import ca.klapstein.baudit.R;

/**
 * Activity presenting a login page for Baudit. Providing a page for the {@code User} to login with their
 * {@code Username} or {@code Email} and {@code Password} combination.
 *
 * @see ca.klapstein.baudit.Data.Username
 * @see ca.klapstein.baudit.Data.Email
 * @see ca.klapstein.baudit.Data.Password
 * @see ca.klapstein.baudit.Data.User
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Nullable
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
