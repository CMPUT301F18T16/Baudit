package ca.klapstein.baudit.views;

import java.util.Date;

public interface RecordView extends View {
    void setDate(Date date);

    void setTitle(String title);

    void setTitleError();

    void setComment(String comment);

    void setCommentError();

    void setGEOLocation();
    void setBODYLocation();
    void setBODYPhoto();
}
