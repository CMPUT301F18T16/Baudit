package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Record;

/**
 * Abstract Class for interacting with a instance of a {@code Record}.
 *
 * @see ca.klapstein.baudit.data.Record
 */
abstract public class RecordActivity extends AppCompatActivity {
    private static final String TAG = "RecordActivity";

    protected Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
    }
}
