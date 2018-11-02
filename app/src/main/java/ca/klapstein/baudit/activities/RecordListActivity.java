package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.adapters.RecordAdapter;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordTreeSet;
import ca.klapstein.baudit.presenters.RecordListPresenter;
import ca.klapstein.baudit.views.RecordListView;

/**
 * Activity for listing {@code Record}s.
 *
 * @see ca.klapstein.baudit.data.Record
 */
public class RecordListActivity extends AppCompatActivity implements DeleteRecordDialog.onDeleteRecordListener, RecordListView {
    private static final String TAG = "RecordListActivity";

    private RecordTreeSet recordTreeSet;
    private RecordAdapter recordAdapter;
    private RecyclerView recordRecyclerView;
    private RecordListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        // TODO: get recordTreeSet from local storage/remote
        recordAdapter = new RecordAdapter(recordTreeSet);

        presenter = new RecordListPresenter(this);
        // TODO: init recordRecyclerView
    }

    /**
     * Launch a EditRecordActivity with the selected Record noted by its position within the {@code RecordTreeSet}.
     *
     * @param view     {@code View}
     * @param position {@code int} the position of the selected Record within the {@code RecordTreeSet}.
     */
    private void onRecordListItemClick(View view, final int position) {
        Intent intent = new Intent(this, EditRecordActivity.class);
        // TODO: add required args
        startActivity(intent);
    }

    /**
     * Launch a AddRecordActivity.
     */
    private void onAddRecordClick() {
        Intent intent = new Intent(this, AddRecordActivity.class);
        // TODO: add required args
        startActivity(intent);
    }

    /**
     * Dialog callback for when a Record is deleted.
     *
     * @param record {@code Record}
     */
    @Override
    public void onDeleteRecord(Record record) {
        // TODO: implement
    }

    @Override
    public void addRecord(Record record) {

    }

    @Override
    public void setRecordList(RecordTreeSet recordTreeSet) {

    }
}
