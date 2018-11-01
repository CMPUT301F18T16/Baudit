package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.R;

/**
 * Abstract Class for interacting with a instance of a {@code Problem}.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
abstract public class ProblemActivity extends AppCompatActivity {
    private static final String TAG = "ProblemActivity";

    protected Problem problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
    }
}
