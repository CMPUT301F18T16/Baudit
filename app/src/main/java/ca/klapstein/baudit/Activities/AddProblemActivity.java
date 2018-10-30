package ca.klapstein.baudit.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ca.klapstein.baudit.Data.Problem;
import ca.klapstein.baudit.R;

/**
 * Activity for adding a {@code Problem} to a {@code Patient}.
 *
 * @see ca.klapstein.baudit.Data.Problem
 * @see ca.klapstein.baudit.Data.Patient
 */
public class AddProblemActivity extends ProblemActivity {
    private static final String TAG = "AddProblemActivity";

    @Nullable
    Problem problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);
    }
}
