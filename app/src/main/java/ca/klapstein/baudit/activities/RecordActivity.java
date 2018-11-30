package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.RecordPresenter;
import ca.klapstein.baudit.views.RecordView;

/**
 * Activity for editing a {@code Record}.
 * <p>
 * Should be only accessed by a {@code Record}.
 *
 * @see ca.klapstein.baudit.data.Record
 */
public class RecordActivity extends AppCompatActivity implements RecordView {

    private int recordId;
    private RecordPresenter presenter;

    private ImageButton titleEditButton;
    private ImageButton titleSaveButton;
    private ImageButton titleCancelButton;
    private TextView titleView;
    private EditText titleInput;

    private ImageButton commentEditButton;
    private ImageButton commentSaveButton;
    private ImageButton commentCancelButton;
    private ImageButton geolocationEditButton;
    private TextView commentView;
    private EditText commentInput;
    private Button commitButton;
    private ImageButton record_geolocation_edit_button;
    private int problemId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Toolbar toolbar = findViewById(R.id.record_toolbar);
        setSupportActionBar(toolbar);

        problemId = getIntent().getIntExtra("problemId", -1);
        recordId = getIntent().getIntExtra("recordId", -1);

        presenter = new RecordPresenter(this, getApplicationContext());
        geolocationEditButton = findViewById(R.id.record_geolocation_edit_button);
        geolocationEditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(
                        RecordActivity.this,
                        LocationActivity.class
                );
                intent.putExtra("recordId",recordId);
                startActivity(intent);
            }
        });
        commitButton = findViewById(R.id.record_commit_button);
        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commitRecord();
            }
        });

        record_geolocation_edit_button = findViewById(R.id.record_geolocation_edit_button);
        record_geolocation_edit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RecordActivity.this, LocationActivity.class);
                intent.putExtra("recordId",recordId);
                intent.putExtra("problemId",problemId);
                startActivity(intent);
            }
        });

        initTitleViews();
        initCommentViews();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted(problemId, recordId);
    }

    @Override
    public void updateTitleField(String title) {
        if (title.isEmpty()) {
            getSupportActionBar().setTitle(R.string.new_record);
        } else {
            getSupportActionBar().setTitle(title);
        }

        titleView.setText(title);
    }

    @Override
    public void updateCommentField(String comment) {
        commentView.setText(comment);
    }

    @Override
    public void commitRecordFailure() {
        Toast.makeText(this, getResources().getString(R.string.record_commit_failure), Toast.LENGTH_LONG).show();
    }

    @Override
    public void commitRecordSuccess() {
        Toast.makeText(this, getResources().getString(R.string.record_commit_success), Toast.LENGTH_LONG).show();
        finish();
    }

    private void initTitleViews() {
        titleView = findViewById(R.id.record_title_view);
        titleInput = findViewById(R.id.record_title_edit_text);
        titleEditButton = findViewById(R.id.record_title_edit_button);
        titleEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitleEditMode(true);
            }
        });
        titleSaveButton = findViewById(R.id.record_title_save_button);
        titleSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveTitleClicked(titleInput.getText().toString());
                setTitleEditMode(false);
            }
        });
        titleCancelButton = findViewById(R.id.record_title_cancel_button);
        titleCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitleEditMode(false);
            }
        });
    }

    private void initCommentViews() {
        commentView = findViewById(R.id.record_comment_view);
        commentInput = findViewById(R.id.record_comment_edit_text);
        commentEditButton = findViewById(R.id.record_comment_edit_button);
        commentEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCommentEditMode(true);
            }
        });
        commentSaveButton = findViewById(R.id.record_comment_save_button);
        commentSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveCommentClicked(commentInput.getText().toString());
                setCommentEditMode(false);
            }
        });
        commentCancelButton = findViewById(R.id.record_comment_cancel_button);
        commentCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCommentEditMode(false);
            }
        });
    }

    private void setTitleEditMode(boolean editMode) {
        if (editMode) {
            getSupportActionBar().setTitle(getResources().getString(R.string.edit_record));
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

    private void setCommentEditMode(boolean editMode) {
        if (editMode) {
            getSupportActionBar().setTitle(getResources().getString(R.string.edit_record));
            commentInput.setText(commentView.getText().toString());
            commentInput.setVisibility(View.VISIBLE);
            commentView.setVisibility(View.GONE);

            commentEditButton.setVisibility(View.GONE);
            commentSaveButton.setVisibility(View.VISIBLE);
            commentCancelButton.setVisibility(View.VISIBLE);


        } else {
            getSupportActionBar().setTitle(titleView.getText().toString());
            commentInput.setVisibility(View.GONE);
            commentView.setVisibility(View.VISIBLE);

            commentEditButton.setVisibility(View.VISIBLE);
            commentSaveButton.setVisibility(View.GONE);
            commentCancelButton.setVisibility(View.GONE);
        }
    }
}
