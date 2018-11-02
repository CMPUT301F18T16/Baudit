package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.RecordPresenter;
import ca.klapstein.baudit.views.RecordView;

/**
 * Abstract Class for interacting with a instance of a {@code Record}.
 *
 * @see ca.klapstein.baudit.data.Record
 */
abstract public class RecordActivity<P extends RecordPresenter> extends AppCompatActivity implements RecordView {
    private static final String TAG = "RecordActivity";

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
    }
}
