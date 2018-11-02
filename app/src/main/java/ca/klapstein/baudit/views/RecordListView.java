package ca.klapstein.baudit.views;

import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordTreeSet;

public interface RecordListView {
    void addRecord(Record record);

    void editRecord(Patient patient, final int position);

    void setRecordList(RecordTreeSet recordTreeSet);
}
