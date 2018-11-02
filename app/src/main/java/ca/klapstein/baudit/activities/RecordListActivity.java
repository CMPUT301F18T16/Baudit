package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordTreeSet;
import ca.klapstein.baudit.presenters.RecordListPresenter;
import ca.klapstein.baudit.views.RecordListView;
import ca.klapstein.baudit.views.RecordRowView;

/**
 * Activity for listing {@code Record}s.
 *
 * @see ca.klapstein.baudit.data.Record
 */
public class RecordListActivity extends AppCompatActivity implements RecordListView, DeleteRecordDialog.onDeleteRecordListener {
    private static final String TAG = "RecordListActivity";

    private RecordListPresenter presenter;
    private RecyclerView recordRecyclerView;
    private RecordListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        // TODO: get recordTreeSet from local storage/remote

        presenter = new RecordListPresenter(this);

        recordRecyclerView = findViewById(R.id.record_list);
        adapter = new RecordListAdapter();
        recordRecyclerView.setAdapter(adapter);
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
    public void editRecord(Patient patient, int position) {

    }

    @Override
    public void setRecordList(RecordTreeSet recordTreeSet) {

    }

    private class RecordListAdapter extends RecyclerView.Adapter<RecordViewHolder> {
        private static final String TAG = "RecordListAdapter";

        public RecordListAdapter() {
            super();
        }

        @NonNull
        @Override
        public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_record, viewGroup, false);
            return new RecordViewHolder(v); //Wrap it in a ViewHolder.
        }

        @Override
        public void onBindViewHolder(@NonNull RecordViewHolder viewHolder, int i) {
            presenter.onBindRecordRowViewAtPosition(viewHolder, i);
        }

        @Override
        public int getItemCount() {
            return presenter.getRecordCount();
        }
    }

    private class RecordViewHolder extends RecyclerView.ViewHolder implements RecordRowView {
        private static final String TAG = "RecordViewHolder";

        CardView mCardView;
        TextView mTimestampView;

        RecordViewHolder(CardView card) {
            super(card);
            mCardView = card;
            mTimestampView = card.findViewById(R.id.record_timestamp);
        }

        @Override
        public void setTimestampText(String timestamp) {
            mTimestampView.setText(timestamp);
        }
    }
}
