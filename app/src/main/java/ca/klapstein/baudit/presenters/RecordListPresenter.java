package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordTreeSet;
import ca.klapstein.baudit.views.RecordListView;
import ca.klapstein.baudit.views.RecordRowView;

public class RecordListPresenter {
    private static final String TAG = "RecordListPresenter";

    private RecordListView view;
    private RecordTreeSet recordTreeSet;

    public RecordListPresenter(RecordListView view) {
        this.view = view;
    }

    public void onBindRecordRowViewAtPosition(RecordRowView rowView, int position) {
        Record record = (Record)recordTreeSet.toArray()[position];
        // rowView.setTimestampText(record.timestamp.toString());
    }

    public int getRecordCount() {
        return this.recordTreeSet.size();
    }
}
