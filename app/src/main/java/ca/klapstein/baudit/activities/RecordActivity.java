package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.GeoLocation;
import ca.klapstein.baudit.presenters.RecordPresenter;
import ca.klapstein.baudit.views.RecordView;

import static ca.klapstein.baudit.activities.CameraActivity.*;
import static ca.klapstein.baudit.activities.ProblemActivity.PROBLEM_POSITION_EXTRA;
import static ca.klapstein.baudit.util.BitmapRotater.RotateBitmap90;

/**
 * Activity for editing a {@code Record}.
 * <p>
 * Should be only accessed by a {@code Record}.
 *
 * @see ca.klapstein.baudit.data.Record
 */
public class RecordActivity extends AppCompatActivity implements RecordView {

    private static final String TAG = "RecordActivity";

    public static final String RECORD_POSITION_EXTRA = "recordPosition";
    public static final String RECORD_MODE_EXTRA = "mode";
    private static final int REQUEST_GEOLOCATION = 123;

    private int problemPosition;
    private int recordPosition;
    private RecordPresenter presenter;
    private String mode;

    private TextView timestampView;
    private TextView titleView;
    private EditText titleInput;
    private TextView commentView;
    private EditText commentInput;
    private TextView locationView;
    private GeoLocation geoLocation = null;
    private ImageView recordImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Toolbar toolbar = findViewById(R.id.record_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        problemPosition = getIntent().getIntExtra(PROBLEM_POSITION_EXTRA, -1);
        recordPosition = getIntent().getIntExtra(RECORD_POSITION_EXTRA, -1);
        mode = getIntent().getStringExtra(RECORD_MODE_EXTRA);

        presenter = new RecordPresenter(this, getApplicationContext());

        timestampView = findViewById(R.id.record_timestamp_text);

        titleView = findViewById(R.id.record_title_view);
        titleInput = findViewById(R.id.record_title_edit_text);

        commentView = findViewById(R.id.record_comment_view);
        commentInput = findViewById(R.id.record_comment_edit_text);

        locationView = findViewById(R.id.record_location_view);

        ImageButton geolocationEditButton = findViewById(R.id.record_geolocation_edit_button);
        geolocationEditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RecordActivity.this, LocationActivity.class);
                intent.putExtra(RECORD_POSITION_EXTRA, recordPosition);
                intent.putExtra(PROBLEM_POSITION_EXTRA, problemPosition);
                startActivityForResult(intent,REQUEST_GEOLOCATION);
            }
        });
        recordImage = findViewById(R.id.recordImage);

        ImageView addPhotoImage = findViewById(R.id.addPhotoImageView);
        addPhotoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordActivity.this, CameraActivity.class);
                intent.putExtra(RECORD_PHOTO_FIELD, true);
                intent.putExtra(RECORD_PHOTO_PROBLEM_ID_FIELD, problemPosition);
                intent.putExtra(RECORD_PHOTO_RECORD_ID_FIELD, recordPosition);
                startActivity(intent);
            }
        });

        Button cancelButton = findViewById(R.id.record_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button saveButton = findViewById(R.id.record_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commitRecord(
                    titleInput.getText().toString(),
                    commentInput.getText().toString(),
                    geoLocation
                );
            }
        });

        if ("view".equals(mode)) {
            getSupportActionBar().setTitle(getResources().getString(R.string.view_record));

            titleView.setVisibility(View.VISIBLE);
            titleInput.setVisibility(View.GONE);

            commentView.setVisibility(View.VISIBLE);
            commentInput.setVisibility(View.GONE);

            locationView.setVisibility(View.VISIBLE);
            geolocationEditButton.setVisibility(View.GONE);

            recordImage.setVisibility(View.VISIBLE);
            addPhotoImage.setVisibility(View.GONE);

            cancelButton.setVisibility(View.GONE);
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

            locationView.setVisibility(View.VISIBLE);
            geolocationEditButton.setVisibility(View.VISIBLE);

            addPhotoImage.setVisibility(View.VISIBLE);
            recordImage.setVisibility(View.GONE);

            cancelButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GEOLOCATION) {
            if (resultCode == RESULT_OK) {
                Double latitude = data.getDoubleExtra("Latitude", 53);
                Double longitude = data.getDoubleExtra("Longitude", -113);
                String address = data.getStringExtra("Address");
                if(geoLocation == null) {
                    geoLocation = new GeoLocation(address, latitude, longitude);
                }else{
                    geoLocation.setAddress(address);
                    geoLocation.setLat(latitude);
                    geoLocation.setLon(longitude);
                }
                Log.d(TAG, "obtained Geolocation for: " + geoLocation.getAddress() +"at coordinates:" +geoLocation.getLat() +geoLocation.getLon());
                updateLocationField(geoLocation);

            } else if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "cancelled location activity");
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.viewStarted(problemPosition, recordPosition);
    }

    @Override
    public void updateImageField(Bitmap bitmap){
        if (bitmap != null)
            recordImage.setImageBitmap(RotateBitmap90(bitmap));
    }

    @Override
    public void updateTimestampField(String timestamp) {
        timestampView.setText(timestamp);
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
    public void updateLocationField(GeoLocation geoLocation) {
        if (geoLocation != null) {
            this.geoLocation = geoLocation;
            locationView.setText(geoLocation.getAddress());
        }
    }

    @Override
    public void updateRecordHints() {
        titleView.setHint(R.string.default_title);
        titleInput.setHint(R.string.default_title);
        commentView.setHint(R.string.default_comment);
        commentInput.setHint(R.string.default_comment);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if ("edit".equals(mode) && recordPosition == -1) {
            return true;
        }
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.record_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.record_play_slideshow:
                startActivity(new Intent(RecordActivity.this,SlideshowActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void updateViewRecordError() {
        Toast.makeText(this, getResources().getString(R.string.record_load_error), Toast.LENGTH_LONG).show();
        finish();
    }
}
