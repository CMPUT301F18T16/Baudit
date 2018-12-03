package ca.klapstein.baudit.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordTreeSet;
import ca.klapstein.baudit.fragments.DatePickerDialogFragment;
import ca.klapstein.baudit.fragments.TimePickerDialogFragment;
import ca.klapstein.baudit.presenters.ProblemPresenter;
import ca.klapstein.baudit.views.ProblemView;

import static ca.klapstein.baudit.activities.MapRecordsActivity.MAP_RECORDS_MODE;
import static ca.klapstein.baudit.activities.MapRecordsActivity.MAP_RECORDS_PROBLEM_POSITION;
import static ca.klapstein.baudit.activities.MapRecordsActivity.MAP_RECORDS_USERNAME;
import static ca.klapstein.baudit.activities.RecordActivity.RECORD_POSITION_EXTRA;

/**
 * Activity for editing a {@code Problem}.
 * <p>
 * Should be only accessed by a {@code Patient}.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class ProblemActivity extends AppCompatActivity
    implements ProblemView, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    public static final String PROBLEM_POSITION_EXTRA = "problemPosition";
    public static final String PROBLEM_MODE_EXTRA = "mode";

    private int problemPosition;
    private ProblemPresenter presenter;
    private Calendar problemTime = new GregorianCalendar();

    private TextView titleView;
    private EditText titleInput;
    private Button dateButton;
    private Button timeButton;
    private TextView descriptionView;
    private EditText descriptionInput;
    private TextView recordCountText;
    private LinearLayout recordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        Toolbar toolbar = findViewById(R.id.patient_home_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);

        problemPosition = getIntent().getIntExtra(PROBLEM_POSITION_EXTRA, -1);
        String mode = getIntent().getStringExtra(PROBLEM_MODE_EXTRA);

        presenter = new ProblemPresenter(this, getApplicationContext());

        titleView = findViewById(R.id.problem_title_view);
        titleInput = findViewById(R.id.problem_title_edit_text);

        descriptionView = findViewById(R.id.problem_description_view);
        descriptionInput = findViewById(R.id.problem_description_edit_text);

        Button addRecordButton = findViewById(R.id.problem_add_record_button);

        Button cancelButton = findViewById(R.id.problem_cancel_button);
        Button saveButton = findViewById(R.id.problem_save_button);

        // Set view visibility based on what mode we are in
        if ("view".equals(mode)) {
            getSupportActionBar().setTitle(R.string.view_problem);

            titleView.setVisibility(View.VISIBLE);
            titleInput.setVisibility(View.GONE);

            descriptionView.setVisibility(View.VISIBLE);
            descriptionInput.setVisibility(View.GONE);

            addRecordButton.setVisibility(View.VISIBLE);

            cancelButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.GONE);
        } else if ("edit".equals(mode)) {
            if (problemPosition == -1) {
                getSupportActionBar().setTitle(R.string.new_problem);
                addRecordButton.setVisibility(View.GONE);
            } else {
                getSupportActionBar().setTitle(R.string.edit_problem);
                addRecordButton.setVisibility(View.VISIBLE);
            }

            titleView.setVisibility(View.GONE);
            titleInput.setVisibility(View.VISIBLE);

            descriptionView.setVisibility(View.GONE);
            descriptionInput.setVisibility(View.VISIBLE);

            cancelButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.VISIBLE);
        }

        dateButton = findViewById(R.id.problem_date_button);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickedDateButton();
            }
        });

        timeButton = findViewById(R.id.problem_time_button);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickedTimeButton();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commitProblem(
                    problemPosition,
                    titleInput.getText().toString(),
                    descriptionInput.getText().toString(),
                    problemTime.getTime()
                );
            }
        });

        recordCountText = findViewById(R.id.problem_records_label);

        addRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
                intent.putExtra(PROBLEM_POSITION_EXTRA, problemPosition);
                intent.putExtra(RECORD_POSITION_EXTRA, -1);
                intent.putExtra(PROBLEM_MODE_EXTRA, "edit");
                startActivity(intent);
            }
        });

        recordList = findViewById(R.id.problem_records_list);
        presenter.viewStarted(problemPosition);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateRecordCountText();
        updateTimeButton(problemTime.getTime());
        updateDateButton(problemTime.getTime());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.problem_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.problem_map_records:
                Intent intent = new Intent(getApplicationContext(), MapRecordsActivity.class);
                intent.putExtra(MAP_RECORDS_MODE, "single");
                intent.putExtra(MAP_RECORDS_USERNAME, presenter.getUsername());
                intent.putExtra(MAP_RECORDS_PROBLEM_POSITION, problemPosition);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Bitmap createImage(int width, int height, int color) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(0F, 0F, (float) width, (float) height, paint);
        return bitmap;
    }

    @Override
    public void updateRecordList(@NonNull RecordTreeSet records) {
        recordList.removeAllViews();
        int index = 0;
        for (Record record : records) {
            final int recordPosition = index;
            final CardView recordView = (CardView) LayoutInflater.from(recordList.getContext())
                .inflate(R.layout.card_record, recordList, false);
            ImageView recordImage = recordView.findViewById(R.id.record_card_image);
            recordImage.setImageBitmap(createImage(64, 64, Color.LTGRAY)); // TODO: Replace with actual image if available
            TextView recordTimestamp = recordView.findViewById(R.id.record_card_timestamp);
            TextView recordTitle = recordView.findViewById(R.id.record_card_title);
            TextView recordComment = recordView.findViewById(R.id.record_card_comment);

            recordTimestamp.setText(record.getTimeStamp());
            recordTitle.setText(record.getTitle());
            recordComment.setText(record.getComment());

            recordView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                        ProblemActivity.this,
                        RecordActivity.class
                    );
                    intent.putExtra(PROBLEM_POSITION_EXTRA, problemPosition);
                    intent.putExtra(RECORD_POSITION_EXTRA, recordPosition);
                    intent.putExtra(PROBLEM_MODE_EXTRA, "view");
                    startActivity(intent);
                }
            });

            recordView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu menu = new PopupMenu(getApplicationContext(), recordView);
                    menu.inflate(R.menu.record_popup_menu);
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Intent intent = new Intent(
                                getApplicationContext(),
                                RecordActivity.class
                            );
                            switch (item.getItemId()) {
                                case R.id.edit_record:
                                    intent.putExtra(PROBLEM_POSITION_EXTRA, problemPosition);
                                    intent.putExtra(RECORD_POSITION_EXTRA, recordPosition);
                                    intent.putExtra(PROBLEM_MODE_EXTRA, "edit");
                                    startActivity(intent);
                                    break;
                                case R.id.delete_record:
                                    new AlertDialog.Builder(ProblemActivity.this)
                                        .setTitle(R.string.delete_record_question)
                                        .setCancelable(true)
                                        .setNegativeButton(R.string.cancel, null)
                                        .setPositiveButton(R.string.delete,
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface di, int i) {
                                                    presenter.deleteRecordClicked(
                                                        recordPosition
                                                    );
                                                }
                                            })
                                        .show();
                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                    menu.show();
                    return true;
                }
            });

            recordList.addView(recordView);

            index++;
        }

        updateRecordCountText();
    }

    @Override
    public void updateTitleField(String title) {
        titleView.setText(title);
        titleInput.setText(title);
    }

    @Override
    public void updateDateButton(Date date) {
        DateFormat mDateFormat = DateFormat.getDateInstance();
        String dateForButton = mDateFormat.format(date);
        dateButton.setText(dateForButton);
    }

    @Override
    public void updateTimeButton(Date date) {
        DateFormat mTimeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        String timeForButton = mTimeFormat.format(date);
        timeButton.setText(timeForButton);
    }

    @Override
    public void updateProblemTime(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        problemTime.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        problemTime.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        problemTime.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        problemTime.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR));
        problemTime.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        updateDateButton(problemTime.getTime());
        updateTimeButton(problemTime.getTime());
    }

    @Override
    public void updateDescriptionField(String description) {
        descriptionView.setText(description);
        descriptionInput.setText(description);
    }

    @Override
    public void updateProblemHints() {
        titleView.setHint(R.string.default_title);
        titleInput.setHint(R.string.default_title);
        descriptionView.setHint(R.string.default_description);
        descriptionInput.setHint(R.string.default_description);
    }

    @Override
    public void showDatePicker(Calendar calendar) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DatePickerDialogFragment mDateFragment = DatePickerDialogFragment.newInstance(calendar);
        mDateFragment.show(ft, DatePickerDialogFragment.TAG);
    }

    @Override
    public void showTimePicker(Calendar calendar) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        TimePickerDialogFragment mTimeFragment = TimePickerDialogFragment.newInstance(calendar);
        mTimeFragment.show(ft, TimePickerDialogFragment.TAG);
    }

    @Override
    public void commitProblemFailure() {
        Toast.makeText(this, getResources().getString(R.string.problem_commit_failure), Toast.LENGTH_LONG).show();
    }

    @Override
    public void commitProblemSuccess() {
        Toast.makeText(this, getResources().getString(R.string.problem_commit_success), Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        problemTime.set(Calendar.YEAR, year);
        problemTime.set(Calendar.MONTH, month);
        problemTime.set(Calendar.DAY_OF_MONTH, day);
        updateDateButton(problemTime.getTime());
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        problemTime.set(Calendar.HOUR_OF_DAY, hour);
        problemTime.set(Calendar.MINUTE, minute);
        updateTimeButton(problemTime.getTime());
    }

    private void updateRecordCountText() {
        recordCountText.setText(String.format(
            getResources().getString(R.string.records_label),
            presenter.getRecordCount()
        ));
    }
}
