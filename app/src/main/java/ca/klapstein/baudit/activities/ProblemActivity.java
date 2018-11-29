package ca.klapstein.baudit.activities;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.fragments.DatePickerDialogFragment;
import ca.klapstein.baudit.fragments.TimePickerDialogFragment;
import ca.klapstein.baudit.presenters.ProblemPresenter;
import ca.klapstein.baudit.views.ProblemView;
import ca.klapstein.baudit.views.RecordRowView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.UUID;

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

    private int problemPosition;
    private ProblemPresenter presenter;
    private RecordListAdapter adapter;
    private TextView titleView;
    private EditText titleInput;
    private Button dateButton;
    private Button timeButton;
    private TextView descriptionView;
    private EditText descriptionInput;
    private TextView recordsLabel;
    private Calendar problemTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        Toolbar toolbar = findViewById(R.id.patient_home_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        problemPosition = getIntent().getIntExtra("problemPosition", -1);
        String mode = getIntent().getStringExtra("mode");

        presenter = new ProblemPresenter(this, getApplicationContext());

        titleView = findViewById(R.id.problem_title_view);
        titleInput = findViewById(R.id.problem_title_edit_text);

        descriptionView = findViewById(R.id.problem_description_view);
        descriptionInput = findViewById(R.id.problem_description_edit_text);

        Button cancelButton = findViewById(R.id.problem_cancel_button);
        Button saveButton = findViewById(R.id.problem_save_button);

        // Set view visibility based on what mode we are in
        if (mode.equals("view")) {
            getSupportActionBar().setTitle(R.string.view_problem);

            titleView.setVisibility(View.VISIBLE);
            titleInput.setVisibility(View.GONE);

            descriptionView.setVisibility(View.VISIBLE);
            descriptionInput.setVisibility(View.GONE);

            cancelButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.GONE);
        } else if (mode.equals("edit")) {
            if (problemPosition == -1) {
                getSupportActionBar().setTitle(R.string.new_problem);
            } else {
                getSupportActionBar().setTitle(R.string.edit_problem);
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

        recordsLabel = findViewById(R.id.problem_records_label);

        Button addRecordButton = findViewById(R.id.problem_add_record_button);
        addRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
                intent.putExtra("problemPosition", problemPosition);
                intent.putExtra("recordId", -1);
                startActivity(intent);
            }
        });

        RecyclerView recordRecyclerView = findViewById(R.id.problem_records_list);
        adapter = new RecordListAdapter();
        recordRecyclerView.setAdapter(adapter);
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted(problemPosition);
        updateRecordCountText();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateTitleField(String title) {
        titleView.setText(title);
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
        descriptionView.setText(description);
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
    public void commitProblemFailure() {
        // TODO:
    }

    @Override
    public void commitProblemSuccess() {
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        DateFormat mDateFormat = DateFormat.getDateInstance();
        problemTime.set(Calendar.YEAR, year);
        problemTime.set(Calendar.MONTH, month);
        problemTime.set(Calendar.DAY_OF_MONTH, day);
        String dateForButton = mDateFormat.format(problemTime.getTime());
        updateDateButton(dateForButton);
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        DateFormat mTimeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        problemTime.set(Calendar.HOUR_OF_DAY, hour);
        problemTime.set(Calendar.MINUTE, minute);
        String timeForButton = mTimeFormat.format(problemTime.getTime());
        updateTimeButton(timeForButton);
    }

    private void updateRecordCountText() {
        recordsLabel.setText(String.format(
            getResources().getString(R.string.records_label),
            presenter.getRecordCount()
        ));
    }

    private class RecordListAdapter extends RecyclerView.Adapter<RecordViewHolder> {

        @Override @NonNull
        public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_record, viewGroup, false);
            return new RecordViewHolder(v); //Wrap it in a ViewHolder.
        }

        @Override
        public void onBindViewHolder(@NonNull final RecordViewHolder viewHolder, int i) {
            Record record = presenter.getRecordAt(i);

            // TODO: Need a way to get image from records
            viewHolder.setTimestampText(record.getTimeStamp());
            viewHolder.setTitleText(record.getTitle());
            viewHolder.setCommentText(record.getComment());

            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                        ProblemActivity.this,
                        RecordActivity.class
                    );
                    intent.putExtra("problemPosition", problemPosition);
                    intent.putExtra("recordId", viewHolder.getAdapterPosition()); // Test ID
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return presenter.getRecordCount();
        }
    }

    private class RecordViewHolder extends RecyclerView.ViewHolder implements RecordRowView {

        private CardView cardView;
        private ImageView imageView;
        private TextView timestampView;
        private TextView titleView;
        private TextView commentView;

        private RecordViewHolder(CardView card) {
            super(card);
            cardView = card;
            imageView = card.findViewById(R.id.record_card_image);
            timestampView = card.findViewById(R.id.record_card_timestamp);
            titleView = card.findViewById(R.id.record_card_title);
            commentView = card.findViewById(R.id.record_card_description);
        }

        @Override
        public void onStart() {
            // Do nothing.
        }

        @Override
        public void setPreviewImage(Bitmap bmp) {
            imageView.setImageBitmap(bmp);
        }

        @Override
        public void setTimestampText(String timestamp) {
            timestampView.setText(timestamp);
        }

        @Override
        public void setTitleText(String title) {
            titleView.setText(title);
        }

        @Override
        public void setCommentText(String comment) {
            commentView.setText(comment);
        }
    }
}
