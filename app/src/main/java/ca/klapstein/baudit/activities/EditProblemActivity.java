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
import android.widget.TimePicker;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.fragments.DatePickerDialogFragment;
import ca.klapstein.baudit.fragments.TimePickerDialogFragment;
import ca.klapstein.baudit.presenters.EditProblemPresenter;
import ca.klapstein.baudit.views.EditProblemView;

import java.util.Calendar;
import java.util.Date;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);
        Toolbar toolbar = findViewById(R.id.patient_home_toolbar);
        setSupportActionBar(toolbar);

        presenter = new EditProblemPresenter(this);

        Button dateButton = findViewById(R.id.edit_problem_date_button);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickedDateButton();
            }
        });

        Button timeButton = findViewById(R.id.edit_problem_time_button);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickedTimeButton();
            }
        });

         int problemId = getIntent().getIntExtra("problemId", 0);
         if (problemId == 0) {
             getSupportActionBar().setTitle(R.string.new_problem);
         } else {
             getSupportActionBar().setTitle(R.string.edit_problem);
             // Populate all fields with the problem data
         }
    }

    public void showDatePicker(Calendar calendar) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DatePickerDialogFragment mDateFragment = DatePickerDialogFragment.newInstance(calendar);
        mDateFragment.show(ft, "datePicker");
    }

    public void showTimePicker(Calendar calendar) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        TimePickerDialogFragment mTimeFragment = TimePickerDialogFragment.newInstance(calendar);
        mTimeFragment.show(ft, "timePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
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
