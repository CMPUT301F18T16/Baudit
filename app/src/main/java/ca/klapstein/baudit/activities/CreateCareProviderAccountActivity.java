package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.CreateCareProviderAccountPresenter;
import ca.klapstein.baudit.views.CreateCareProviderAccountView;

/**
 * Activity providing the inputs and validations for creating a
 * valid {@code CareProvider} Account for Baudit.
 *
 * @see ca.klapstein.baudit.data.CareProvider
 */
public class CreateCareProviderAccountActivity extends AppCompatActivity
    implements CreateCareProviderAccountView {

    private CreateCareProviderAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_care_provider_account);

        presenter = new CreateCareProviderAccountPresenter(this, getApplicationContext());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * Displays an error message if the inputted username is invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updateUsernameError(String message) {
        // TODO: Set error message to text view
    }

    /**
     * Displays an error message if the inputted email is invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updateEmailError(String message) {
        // TODO: Set error message to text view
    }

    /**
     * Displays an error message if the inputted phone number is invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updatePhoneNumberError(String message) {
        // TODO: Set error message to text view
    }

    /**
     * Displays an error message if the inputted password(s) is/are invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updatePasswordError(String message) {
        // TODO: Set error message to text view
    }

    /**
     * Displays an error message if the inputted MINC ID is invalid.
     *
     * @param message {@code String}
     */
    @Override
    public void updateCareProviderIdError(String message) {
        // TODO: Set error message to text view
    }

    /**
     * Responds to account confirmation by opening the new care provider account's home screen.
     */
    @Override
    public void onAccountConfirmed() {
        finish();
    }

}
