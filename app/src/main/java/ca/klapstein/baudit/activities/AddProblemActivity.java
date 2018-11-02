package ca.klapstein.baudit.activities;

import android.os.Bundle;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.views.AddProblemView;

import java.util.Date;

/**
 * Activity for adding a {@code Problem} to a {@code Patient}.
 *
 * @see ca.klapstein.baudit.data.Problem
 * @see ca.klapstein.baudit.data.Patient
 */
public class AddProblemActivity extends ProblemActivity implements AddProblemView {
    private static final String TAG = "AddProblemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);
    }

    @Override
    public void commitAddProblem() {

    }

    @Override
    public void setDateStarted(Date date) {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setTitleError() {

    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public void setDescriptionError() {

    }
}
