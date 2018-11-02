package ca.klapstein.baudit.activities;

import android.os.Bundle;
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
public class EditRecordActivity extends RecordActivity<EditRecordPresenter> implements EditRecordView {
    private static final String TAG = "EditRecordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        presenter = new EditRecordPresenter(this);
    }

    @Override
    public void commitEditRecord() {

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
