package ca.klapstein.baudit.views;

import ca.klapstein.baudit.data.GeoLocation;

public interface RecordView extends View {
    void updateTitleField(String title);
    void updateCommentField(String comment);
    void updateLocationField(GeoLocation location);
    void commitRecordFailure();
    void commitRecordSuccess();
}
