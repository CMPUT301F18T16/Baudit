package ca.klapstein.baudit.views;

import java.util.Date;

public interface RecordView extends View {
    void setDate(Date date);

    void setDateError();

    void setTitle(String title);

    void setTitleError();

    void setComment(String comment);

    void setCommentError();

    void setGEOLocation();

    void setGEOLocationError();

    void setBodyLocation();

    void setBodyLocationError();

    void setBodyPhoto();

    void setBodyPhotoError();
}
