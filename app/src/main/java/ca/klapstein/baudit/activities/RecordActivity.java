package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.RecordPresenter;
import ca.klapstein.baudit.views.RecordView;

import java.util.Date;

/**
 * Activity for editing a {@code Record}.
 * <p>
 * Should be only accessed by a {@code Record}.
 *
 * @see ca.klapstein.baudit.data.Record
 */
public class RecordActivity extends AppCompatActivity implements RecordView {

    private RecordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        presenter = new RecordPresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();
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
}
