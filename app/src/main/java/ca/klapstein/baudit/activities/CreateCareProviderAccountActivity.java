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
public class CreateCareProviderAccountActivity extends AppCompatActivity implements CreateCareProviderAccountView {
    private static final String TAG = "CreateCareProviderAccountActivity";

    private CreateCareProviderAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_care_provider_account);
        this.presenter = new CreateCareProviderAccountPresenter(this);
    }

    @Override
    public void setCareProviderIDError() {

    }

    @Override
    public void setEmail(String string) {

    }

    @Override
    public void setEmailError() {

    }

    @Override
    public void setUsername(String string) {

    }

    @Override
    public void setUserNameError() {

    }

    @Override
    public void setPassword(String string) {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void commitCreateAccount() {
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
