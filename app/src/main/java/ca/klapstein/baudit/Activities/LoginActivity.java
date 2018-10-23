package ca.klapstein.baudit.Activities;

import android.support.annotation.Nullable;
import ca.klapstein.baudit.Data.User;

/**
 * Activity presenting a login page for Baudit. Providing a page for the {@code User} to login with their
 * {@code Username} or {@code Email} and {@code Password} combination.
 *
 * @see ca.klapstein.baudit.Data.Username
 * @see ca.klapstein.baudit.Data.Email
 * @see ca.klapstein.baudit.Data.Password
 * @see ca.klapstein.baudit.Data.User
 */
public class LoginActivity {
    private static final String TAG = "LoginActivity";

    @Nullable
    private User user;
}
