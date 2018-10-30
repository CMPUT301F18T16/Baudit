package ca.klapstein.baudit.Activities;

import android.os.Bundle;
import ca.klapstein.baudit.R;

/**
 * Activity for editing a {@code Problem}.
 * <p>
 * Should be only accessed by a {@code Patient}.
 *
 * @see ca.klapstein.baudit.Data.Patient
 */
public class EditProblemActivity extends ProblemActivity {
    private static final String TAG = "EditProblemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);
    }
}
