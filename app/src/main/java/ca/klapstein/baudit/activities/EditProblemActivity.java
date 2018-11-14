package ca.klapstein.baudit.activities;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.fragments.DatePickerDialogFragment;
import ca.klapstein.baudit.fragments.TimePickerDialogFragment;
import ca.klapstein.baudit.presenters.EditProblemPresenter;
import ca.klapstein.baudit.views.EditProblemView;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Activity for editing a {@code Problem}.
 * <p>
 * Should be only accessed by a {@code Patient}.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class EditProblemActivity extends AppCompatActivity
    implements EditProblemView, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private EditProblemPresenter presenter;
    private EditText titleInput;
    private Button dateButton;
    private Button timeButton;
    private EditText descriptionInput;
    private TextView recordsLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);
        Toolbar toolbar = findViewById(R.id.patient_home_toolbar);
        setSupportActionBar(toolbar);

        presenter = new EditProblemPresenter(this);

        titleInput = findViewById(R.id.edit_problem_title_input);

        dateButton = findViewById(R.id.edit_problem_date_button);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickedDateButton();
            }
        });

        timeButton = findViewById(R.id.edit_problem_time_button);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickedTimeButton();
            }
        });

        descriptionInput = findViewById(R.id.edit_problem_description_input);

        recordsLabel = findViewById(R.id.records_label);
    }

    @Override
    public void onStart() {
        super.onStart();
        int problemId = getIntent().getIntExtra("problemId", 0);
        if (problemId == 0) {
            getSupportActionBar().setTitle(R.string.new_problem);
        } else {
            getSupportActionBar().setTitle(R.string.edit_problem);
        }

        presenter.viewStarted(problemId);
        updateRecordCountText();
    }

    @Override
    public void updateTitleField(String title) {
        titleInput.setText(title);
    }

    @Override
    public void updateDateButton(String dateString) {
        dateButton.setText(dateString);
    }

    @Override
    public void updateTimeButton(String timeString) {
        timeButton.setText(timeString);
    }

    @Override
    public void updateDescriptionField(String description) {
        descriptionInput.setText(description);
    }

    @Override
    public void showDatePicker(Calendar calendar) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DatePickerDialogFragment mDateFragment = DatePickerDialogFragment.newInstance(calendar);
        mDateFragment.show(ft, "datePicker");
    }

    @Override
    public void showTimePicker(Calendar calendar) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        TimePickerDialogFragment mTimeFragment = TimePickerDialogFragment.newInstance(calendar);
        mTimeFragment.show(ft, "timePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        DateFormat mDateFormat = DateFormat.getDateInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        String dateForButton = mDateFormat.format(calendar.getTime());
        updateDateButton(dateForButton);
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        DateFormat mTimeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        String timeForButton = mTimeFormat.format(calendar.getTime());
        updateTimeButton(timeForButton);
    }

    private void updateRecordCountText() {
        recordsLabel.setText(String.format(
            getResources().getString(R.string.records_label),
            presenter.getRecordCount()
        ));
    }
}
