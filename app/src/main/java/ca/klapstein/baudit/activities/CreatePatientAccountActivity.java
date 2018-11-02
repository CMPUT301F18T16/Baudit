package ca.klapstein.baudit.activities;

import android.os.Bundle;
import ca.klapstein.baudit.R;

/**
 * Activity providing the inputs and validations for creating a
 * valid {@code Patient} Account for Baudit.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class CreatePatientAccountActivity extends AccountCreateActivity {
    private static final String TAG = "CreatePatientAccountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient_account);
    }
}
