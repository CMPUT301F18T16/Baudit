package ca.klapstein.baudit.activities;

import android.os.Bundle;
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
public class AddRecordActivity extends RecordActivity<AddRecordPresenter> implements AddRecordView {
    private static final String TAG = "AddRecordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
    }

    @Override
    public void commitAddRecord() {

    }

    @Override
    public void setDate(Date date) {

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
    public void setBODYLocation() {

    }

    @Override
    public void setBODYPhoto() {

    }
}
