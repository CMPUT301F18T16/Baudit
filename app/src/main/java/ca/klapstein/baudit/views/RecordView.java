package ca.klapstein.baudit.views;

public interface RecordView extends View {
    void updateTitleField(String title);
    void updateCommentField(String comment);

    void commitRecord();

    void commitRecordFailure();

    void commitRecordSuccess();
}
