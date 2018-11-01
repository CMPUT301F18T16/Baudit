package ca.klapstein.baudit.activities;

import android.os.Bundle;
import ca.klapstein.baudit.R;


/**
 * Activity for adding a {@code Record} to a {@code Problem}.
 *
 * @see ca.klapstein.baudit.data.Record
 * @see ca.klapstein.baudit.data.Problem
 */
public class AddRecordActivity extends RecordActivity {
    private static final String TAG = "AddRecordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
    }
}
