package ca.klapstein.baudit.Activities;

import android.support.annotation.Nullable;
import ca.klapstein.baudit.Data.User;

/**
 * Activity presenting a account creation page for Baudit.
 *
 * After filling out the required forms create a {@code User} and log them into Baudit.
 *
 * @see ca.klapstein.baudit.Data.Username
 * @see ca.klapstein.baudit.Data.Email
 * @see ca.klapstein.baudit.Data.Password
 * @see ca.klapstein.baudit.Data.PhoneNumber
 * @see ca.klapstein.baudit.Data.User
 */
public class AccountCreateActivity {
    private static final String TAG = "AccountCreateActivity";

    @Nullable
    private User user;
}
