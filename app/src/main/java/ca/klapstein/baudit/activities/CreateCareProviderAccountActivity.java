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

    @Override
    public void updateUsernameError(String message) {
    }

    @Override
    public void updateEmailError(String message) {
    }

    @Override
    public void updatePhoneNumberError(String message) {
    }

    @Override
    public void updatePasswordError(String message) {
    }

    @Override
    public void updateCareProviderIdError(String message) {
    }

    @Override
    public void onAccountConfirmed() {
        finish();
    }

}
