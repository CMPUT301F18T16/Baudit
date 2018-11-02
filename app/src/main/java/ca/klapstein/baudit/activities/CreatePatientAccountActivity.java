package ca.klapstein.baudit.activities;

import android.os.Bundle;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.views.CreatePatientAccountView;

/**
 * Activity providing the inputs and validations for creating a
 * valid {@code Patient} Account for Baudit.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class CreatePatientAccountActivity extends AccountCreateActivity implements CreatePatientAccountView {
    private static final String TAG = "CreatePatientAccountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient_account);
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
}
