package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.EditRecordPresenter;
import ca.klapstein.baudit.views.EditRecordView;

import java.util.Date;

/**
 * Activity for editing a {@code Record}.
 * <p>
 * Should be only accessed by a {@code Record}.
 *
 * @see ca.klapstein.baudit.data.Record
 */
public class EditRecordActivity extends AppCompatActivity implements EditRecordView {
    private static final String TAG = "EditRecordActivity";
    private EditRecordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        presenter = new EditRecordPresenter(this, getApplicationContext());
    }

    @Override
    public void commitEditRecord() {
        finish();
    }

    @Override
    public void setDate(Date date) {

    }

    @Override
    public void setDateError() {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setTitleError() {

    }

    @Override
    public void setComment(String comment) {

    }

    @Override
    public void setCommentError() {

    }

    @Override
    public void setGEOLocation() {

    }

    @Override
    public void setGEOLocationError() {

    }

    @Override
    public void setBodyLocation() {

    }

    @Override
    public void setBodyLocationError() {

    }

    @Override
    public void setBodyPhoto() {

    }

    @Override
    public void setBodyPhotoError() {

    }

    @Override
    public void onStart() {

        super.onStart();
    }
}
