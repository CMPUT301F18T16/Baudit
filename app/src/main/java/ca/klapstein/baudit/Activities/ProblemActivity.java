package ca.klapstein.baudit.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.Data.Problem;
import ca.klapstein.baudit.R;

/**
 * Abstract Class for interacting with a instance of a {@code Problem}.
 *
 * @see ca.klapstein.baudit.Data.Problem
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
