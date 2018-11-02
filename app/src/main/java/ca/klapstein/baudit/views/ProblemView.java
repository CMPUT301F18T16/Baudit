package ca.klapstein.baudit.views;

import java.util.Date;

public interface ProblemView extends View {
    void setDateStarted(Date date);

    void setTitle(String title);

    void setTitleError();

    void setDescription(String description);

    void setDescriptionError();
}
