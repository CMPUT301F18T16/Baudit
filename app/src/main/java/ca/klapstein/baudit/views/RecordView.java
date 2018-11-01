package ca.klapstein.baudit.views;

import java.util.Date;

public interface RecordView {
    void setDate(Date date);
    void setTitle(String title);
    void setComment(String comment);
    void setGEOLocation();
    void setBODYLocation();
    void setBODYPhoto();
}
