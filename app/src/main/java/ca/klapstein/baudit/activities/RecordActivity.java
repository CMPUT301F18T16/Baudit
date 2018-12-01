package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.GeoLocation;
import ca.klapstein.baudit.presenters.RecordPresenter;
import ca.klapstein.baudit.views.RecordView;

import static ca.klapstein.baudit.activities.ProblemActivity.PROBLEM_POSITION_EXTRA;

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

    private int problemPosition;
    private int recordPosition;
    private RecordPresenter presenter;

    private TextView titleView;
    private EditText titleInput;
    private TextView commentView;
    private EditText commentInput;
    private TextView locationView;
    private PlaceAutocompleteFragment autocompleteFragment;
    private GeoLocation geoLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Toolbar toolbar = findViewById(R.id.record_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        problemPosition = getIntent().getIntExtra(PROBLEM_POSITION_EXTRA, -1);
        recordPosition = getIntent().getIntExtra(RECORD_POSITION_EXTRA, -1);
        String mode = getIntent().getStringExtra(RECORD_MODE_EXTRA);

        presenter = new RecordPresenter(this, getApplicationContext());

        titleView = findViewById(R.id.record_title_view);
        titleInput = findViewById(R.id.record_title_edit_text);

        commentView = findViewById(R.id.record_comment_view);
        commentInput = findViewById(R.id.record_comment_edit_text);

        locationView = findViewById(R.id.record_location_view);

        autocompleteFragment = (PlaceAutocompleteFragment)
            getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        // TODO: This is a heuristic. Set to Canada for now
        LatLng sw = new LatLng(41.6751050889, -140.99778);
        LatLng ne = new LatLng(83.23324, -52.6480987209);
        autocompleteFragment.setBoundsBias(new LatLngBounds(sw, ne));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                geoLocation = new GeoLocation(
                    place.getName().toString(),
                    place.getLatLng().latitude,
                    place.getLatLng().longitude
                );
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
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
                    recordPosition,
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
            autocompleteFragment.getView().setVisibility(View.GONE);

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

            locationView.setVisibility(View.GONE);
            autocompleteFragment.getView().setVisibility(View.VISIBLE);

            cancelButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.VISIBLE);
        }
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
    public void updateLocationField(GeoLocation geoLocation) {
        if (geoLocation != null) {
            this.geoLocation = geoLocation;
            locationView.setText(geoLocation.getAddress());
            autocompleteFragment.setText(geoLocation.getAddress());
        }
    }

    @Override
    public void setNewRecordHints() {
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
}
