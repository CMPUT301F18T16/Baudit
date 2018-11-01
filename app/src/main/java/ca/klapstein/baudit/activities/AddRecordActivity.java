package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.R;


/**
 * Activity for adding a {@code Record} to a {@code Problem}.
 *
 * @see ca.klapstein.baudit.data.Record
 * @see ca.klapstein.baudit.data.Problem
 */
public class AddRecordActivity extends RecordActivity {
    private static final String TAG = "AddRecordActivity";

    @Nullable
    Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
    }
}
