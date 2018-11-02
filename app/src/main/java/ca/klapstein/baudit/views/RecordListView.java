package ca.klapstein.baudit.views;

import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordTreeSet;

public interface RecordListView {
    void addRecord(Record record);

    void setRecordList(RecordTreeSet recordTreeSet);
}
