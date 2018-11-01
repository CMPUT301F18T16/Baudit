package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.User;

/**
 * Activity presenting a login page for Baudit. Providing a page for the {@code User} to login with their
 * {@code Username} or {@code Email} and {@code Password} combination.
 *
 * @see ca.klapstein.baudit.data.Username
 * @see ca.klapstein.baudit.data.Email
 * @see ca.klapstein.baudit.data.Password
 * @see ca.klapstein.baudit.data.User
 */
public abstract class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Nullable
    protected User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Attempt to login a {@code User}.
     *
     * @return {@code true} if the login was successful, otherwise return {@code false}.
     */
    abstract boolean login();

    /**
     * Functional stub defining what to do after a successful login.
     *
     * Should be called after {@code login} returns {@code true}.
     */
    abstract void onLogin();
}
