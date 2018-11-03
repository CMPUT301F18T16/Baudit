package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.AddRecordPresenter;
import ca.klapstein.baudit.views.AddRecordView;

import java.util.Date;


/**
 * Activity for adding a {@code Record} to a {@code Problem}.
 *
 * @see ca.klapstein.baudit.data.Record
 * @see ca.klapstein.baudit.data.Problem
 */
public class AddRecordActivity extends AppCompatActivity implements AddRecordView {
    private static final String TAG = "AddRecordActivity";
    private AddRecordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        presenter = new AddRecordPresenter(this);
    }

    @Override
    public void commitAddRecord() {
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
}
