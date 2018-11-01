package ca.klapstein.baudit.activities;

import android.os.Bundle;
import ca.klapstein.baudit.R;

/**
 * Activity for adding a {@code Problem} to a {@code Patient}.
 *
 * @see ca.klapstein.baudit.data.Problem
 * @see ca.klapstein.baudit.data.Patient
 */
public class AddProblemActivity extends ProblemActivity {
    private static final String TAG = "AddProblemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);
    }
}
