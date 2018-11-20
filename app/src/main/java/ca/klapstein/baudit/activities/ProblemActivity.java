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

    private int problemId;
    private ProblemPresenter presenter;
    private RecordListAdapter adapter;
    private TextView titleView;
    private EditText titleInput;
    private ImageButton titleEditButton;
    private ImageButton titleSaveButton;
    private ImageButton titleCancelButton;
    private Button dateButton;
    private Button timeButton;
    private TextView descriptionView;
    private EditText descriptionInput;
    private ImageButton descriptionEditButton;
    private ImageButton descriptionSaveButton;
    private ImageButton descriptionCancelButton;
    private TextView recordsLabel;
    private Button commitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        Toolbar toolbar = findViewById(R.id.patient_home_toolbar);
        setSupportActionBar(toolbar);

        problemId = getIntent().getIntExtra("problemId", 0);

        presenter = new ProblemPresenter(this, getApplicationContext());

        initTitleViews();

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

        commitButton = findViewById(R.id.problem_commit_button);
        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commitProblem();
            }
        });

        initDescriptionViews();

        recordsLabel = findViewById(R.id.problem_records_label);

        Button addRecord = findViewById(R.id.problem_add_record_button);
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProblemActivity.this, RecordActivity.class);
                intent.putExtra("recordId", 0);
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
        presenter.viewStarted(problemId);
        updateRecordCountText();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateTitleField(String title) {
        if (title.isEmpty()) {
            getSupportActionBar().setTitle(R.string.new_problem);
        } else {
            getSupportActionBar().setTitle(title);
        }

        titleView.setText(title);
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

    private void initTitleViews() {
        titleView = findViewById(R.id.problem_title_view);
        titleInput = findViewById(R.id.problem_title_edit_text);
        titleEditButton = findViewById(R.id.problem_title_edit_button);
        titleEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitleEditMode(true);
            }
        });
        titleSaveButton = findViewById(R.id.problem_title_save_button);
        titleSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveTitleClicked(titleInput.getText().toString());
                setTitleEditMode(false);
            }
        });
        titleCancelButton = findViewById(R.id.problem_title_cancel_button);
        titleCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitleEditMode(false);
            }
        });
    }

    private void initDescriptionViews() {
        descriptionView = findViewById(R.id.problem_description_view);
        descriptionInput = findViewById(R.id.problem_description_edit_text);
        descriptionEditButton = findViewById(R.id.problem_description_edit_button);
        descriptionEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDescriptionEditMode(true);
            }
        });
        descriptionSaveButton = findViewById(R.id.problem_description_save_button);
        descriptionSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveDescriptionClicked(descriptionInput.getText().toString());
                setDescriptionEditMode(false);
            }
        });
        descriptionCancelButton = findViewById(R.id.problem_description_cancel_button);
        descriptionCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDescriptionEditMode(false);
            }
        });
    }

    private void setTitleEditMode(boolean editMode) {
        if (editMode) {
            getSupportActionBar().setTitle(getResources().getString(R.string.edit_problem));
            titleInput.setText(titleView.getText().toString());
            titleInput.setVisibility(View.VISIBLE);
            titleView.setVisibility(View.GONE);

            titleEditButton.setVisibility(View.GONE);
            titleSaveButton.setVisibility(View.VISIBLE);
            titleCancelButton.setVisibility(View.VISIBLE);
        } else {
            getSupportActionBar().setTitle(titleView.getText().toString());
            titleInput.setVisibility(View.GONE);
            titleView.setVisibility(View.VISIBLE);

            titleEditButton.setVisibility(View.VISIBLE);
            titleSaveButton.setVisibility(View.GONE);
            titleCancelButton.setVisibility(View.GONE);
        }
    }

    private void setDescriptionEditMode(boolean editMode) {
        if (editMode) {
            getSupportActionBar().setTitle(getResources().getString(R.string.edit_problem));
            descriptionInput.setText(descriptionView.getText().toString());
            descriptionInput.setVisibility(View.VISIBLE);
            descriptionView.setVisibility(View.GONE);

            descriptionEditButton.setVisibility(View.GONE);
            descriptionSaveButton.setVisibility(View.VISIBLE);
            descriptionCancelButton.setVisibility(View.VISIBLE);


        } else {
            getSupportActionBar().setTitle(titleView.getText().toString());
            descriptionInput.setVisibility(View.GONE);
            descriptionView.setVisibility(View.VISIBLE);

            descriptionEditButton.setVisibility(View.VISIBLE);
            descriptionSaveButton.setVisibility(View.GONE);
            descriptionCancelButton.setVisibility(View.GONE);
        }
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
                    intent.putExtra("recordId", 1); // Test ID
                    // TODO: Need a way to get the problem's ID to add to the intent
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
