package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordTreeSet;
import ca.klapstein.baudit.views.RecordListView;
import ca.klapstein.baudit.views.RecordRowView;

/**
 * MVP presenter for presenting a {@code RecordTreeSet} on a {@code RecordListView}.
 *
 * @see RecordTreeSet
 * @see RecordListView
 */
public class RecordListPresenter extends Presenter<RecordListView> {
    private static final String TAG = "RecordListPresenter";

    private RecordTreeSet recordTreeSet;

    public RecordListPresenter(RecordListView view) {
        super(view);
    }

    public void onBindRecordRowViewAtPosition(RecordRowView rowView, int position) {
        Record record = (Record)recordTreeSet.toArray()[position];
        // rowView.setTimestampText(record.timestamp.toString());
    }

    public int getRecordCount() {
        return this.recordTreeSet.size();
    }
}
