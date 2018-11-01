package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import ca.klapstein.baudit.Adapters.RecordAdapter;
import ca.klapstein.baudit.Data.RecordTreeSet;
import ca.klapstein.baudit.R;

/**
 * Activity for listing {@code Record}s.
 *
 * @see ca.klapstein.baudit.Data.Record
 */
public class RecordListActivity extends AppCompatActivity {
    private static final String TAG = "RecordListActivity";

    private RecordTreeSet recordTreeSet;
    private RecordAdapter recordAdapter;
    private RecyclerView recordRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        // TODO: get recordTreeSet from local storage/remote
        recordAdapter = new RecordAdapter(recordTreeSet);

        // TODO: init recordRecyclerView
    }
}
