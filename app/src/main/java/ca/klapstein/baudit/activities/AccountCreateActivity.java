package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.data.User;
import ca.klapstein.baudit.R;

/**
 * Activity presenting a account creation page for Baudit.
 * <p>
 * After filling out the required forms create a {@code User} and log them into Baudit.
 *
 * @see ca.klapstein.baudit.data.Username
 * @see ca.klapstein.baudit.data.Email
 * @see ca.klapstein.baudit.data.Password
 * @see ca.klapstein.baudit.data.PhoneNumber
 * @see ca.klapstein.baudit.data.User
 */
public class AccountCreateActivity extends AppCompatActivity {
    private static final String TAG = "AccountCreateActivity";

    @Nullable
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_create);
        // TODO: parse inputs and create the user
    }
}
