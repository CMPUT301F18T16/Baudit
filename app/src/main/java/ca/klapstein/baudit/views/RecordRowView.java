package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

public interface RecordRowView extends View {
    void setTimestampText(String timestamp);

    void setPreviewImage(Bitmap bmp);

    void setTitleText(String title);

    void setCommentText(String comment);
}
