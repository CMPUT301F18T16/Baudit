package ca.klapstein.baudit.activities;

import android.content.Context;
import android.os.Bundle;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Record;

/**
 * Activity for editing a {@code Record}.
 * <p>
 * Should be only accessed by a {@code Record}.
 *
 * @see ca.klapstein.baudit.data.Record
 */
public class EditRecordActivity extends RecordActivity {
    private static final String TAG = "EditRecordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
    }
}
