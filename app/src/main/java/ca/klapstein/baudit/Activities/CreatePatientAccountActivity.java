package ca.klapstein.baudit.Activities;

import android.os.Bundle;
import ca.klapstein.baudit.R;

public class CreatePatientAccountActivity extends AccountCreateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient_account);
    }
}