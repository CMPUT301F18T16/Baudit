package ca.klapstein.baudit.activities;

import android.os.Bundle;
import ca.klapstein.baudit.R;

/**
 * Activity providing the inputs and validations for creating a
 * valid {@code CareProvider} Account for Baudit.
 *
 * @see ca.klapstein.baudit.data.CareProvider
 */
public class CreateCareProviderAccountActivity extends AccountCreateActivity {
    private static final String TAG = "CreateCareProviderAccountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_care_provider_account);
    }
}
