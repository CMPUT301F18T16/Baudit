package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

import java.util.ArrayList;

public interface RecordView extends View {
    void updateTitleField(String title);
    void updateCommentField(String comment);
    void commitRecordFailure();
    void commitRecordSuccess();
    void updatePhotosLayout(ArrayList<Bitmap> photos);
}
