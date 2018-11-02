package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.CreatePatientAccountPresenter;
import ca.klapstein.baudit.views.CreatePatientAccountView;

/**
 * Activity providing the inputs and validations for creating a
 * valid {@code Patient} Account for Baudit.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class CreatePatientAccountActivity extends AppCompatActivity implements CreatePatientAccountView {
    private static final String TAG = "CreatePatientAccountActivity";

    private CreatePatientAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient_account);
        this.presenter = new CreatePatientAccountPresenter(this);

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
    public void onStart() {
        super.onStart();
    }
}
