package ca.klapstein.baudit.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import ca.klapstein.baudit.Data.User;
import ca.klapstein.baudit.R;

/**
 * Activity for editing a {@code User}.
 * <p>
 * Should be accessed by both a {@code Patient} and {@code CareProvider}.
 *
 * @see ca.klapstein.baudit.Data.Patient
 * @see ca.klapstein.baudit.Data.CareProvider
 */
public class EditAccountActivity extends AppCompatActivity {
    private static final String TAG = "EditAccountActivity";

    @NonNull
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
    }
}
