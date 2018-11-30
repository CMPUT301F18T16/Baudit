package ca.klapstein.baudit.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

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

    private int problemPosition;
    private int recordPosition;
    private RecordPresenter presenter;

    private TextView titleView;
    private EditText titleInput;
    private TextView commentView;
    private EditText commentInput;
    private GridLayout photosLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Toolbar toolbar = findViewById(R.id.record_toolbar);
        setSupportActionBar(toolbar);

        problemPosition = getIntent().getIntExtra("problemPosition", -1);
        recordPosition = getIntent().getIntExtra("recordPosition", -1);
        String mode = getIntent().getStringExtra("mode");

        presenter = new RecordPresenter(this, getApplicationContext());

        titleView = findViewById(R.id.record_title_view);
        titleInput = findViewById(R.id.record_title_edit_text);

        commentView = findViewById(R.id.record_comment_view);
        commentInput = findViewById(R.id.record_comment_edit_text);

        Button saveButton = findViewById(R.id.record_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commitRecord(
                    recordPosition,
                    titleInput.getText().toString(),
                    commentInput.getText().toString()
                );
            }
        });

        if ("view".equals(mode)) {
            getSupportActionBar().setTitle(getResources().getString(R.string.view_record));

            titleView.setVisibility(View.VISIBLE);
            titleInput.setVisibility(View.GONE);

            commentView.setVisibility(View.VISIBLE);
            commentInput.setVisibility(View.GONE);

            saveButton.setVisibility(View.GONE);
        } else if ("edit".equals(mode)) {
            if (recordPosition == -1) {
                getSupportActionBar().setTitle(getResources().getString(R.string.new_record));
            } else {
                getSupportActionBar().setTitle(getResources().getString(R.string.edit_record));
            }

            titleView.setVisibility(View.GONE);
            titleInput.setVisibility(View.VISIBLE);

            commentView.setVisibility(View.GONE);
            commentInput.setVisibility(View.VISIBLE);

            saveButton.setVisibility(View.VISIBLE);
        }

        photosLayout = findViewById(R.id.record_photos_layout);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted(problemPosition, recordPosition);
    }

    @Override
    public void updateTitleField(String title) {
        titleView.setText(title);
        titleInput.setText(title);
    }

    @Override
    public void updateCommentField(String comment) {
        commentView.setText(comment);
        commentInput.setText(comment);
    }

    @Override
    public void updatePhotosLayout(ArrayList<Bitmap> photos) {
        photosLayout.removeAllViews();
        for (Bitmap photo: photos) {
            ImageView photoView = (ImageView) LayoutInflater.from(photosLayout.getContext())
                .inflate(R.layout.photo_tile, photosLayout, false);
            photoView.setImageBitmap(photo);
            photosLayout.addView(photoView);
        }
        ImageButton addPhotoButton = (ImageButton) LayoutInflater.from(photosLayout.getContext())
            .inflate(R.layout.add_photo_button, photosLayout, false);

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Open camera activity
            }
        });

        photosLayout.addView(addPhotoButton);
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
}
