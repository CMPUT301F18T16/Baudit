package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.EditProblemPresenter;
import ca.klapstein.baudit.views.EditProblemView;

import java.util.Date;

/**
 * Activity for editing a {@code Problem}.
 * <p>
 * Should be only accessed by a {@code Patient}.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class EditProblemActivity extends AppCompatActivity implements EditProblemView {

    private EditProblemPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);

        presenter = new EditProblemPresenter(this);

        int problemId = getIntent().getIntExtra("problemId", 0);
        if (problemId == 0) {
            // Set app bar title to say "New Problem"
        } else {
            // Set app bar title to say "Edit Problem"
            // Populate all fields with the problem data
        }
    }

    @Override
    public void commitEditProblem() {
        finish();
    }

    @Override
    public void setDateStarted(Date date) {

    }

    @Override
    public void setDateStartedError() {

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

    @Override
    public void onStart() {
        super.onStart();
    }
}
