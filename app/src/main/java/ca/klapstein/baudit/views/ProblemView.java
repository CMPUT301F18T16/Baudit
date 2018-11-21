package ca.klapstein.baudit.views;

import java.util.Calendar;

public interface ProblemView extends View {
    void updateTitleField(String title);
    void updateDateButton(String dateString);
    void updateTimeButton(String timeString);
    void updateDescriptionField(String description);
    void showDatePicker(Calendar calendar);
    void showTimePicker(Calendar calendar);

    void commitProblemSuccess();

    void commitProblemFailure();
}
