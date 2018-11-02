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
    private static final String TAG = "EditProblemActivity";
    private EditProblemPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);

        presenter = new EditProblemPresenter(this);
    }

    @Override
    public void commitEditProblem() {

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
}
