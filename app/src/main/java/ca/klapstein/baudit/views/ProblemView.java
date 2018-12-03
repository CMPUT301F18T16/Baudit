package ca.klapstein.baudit.views;

import ca.klapstein.baudit.data.RecordTreeSet;

import java.util.Calendar;

public interface ProblemView extends View {
    void updateTitleField(String title);
    void updateDateButton(String dateString);
    void updateTimeButton(String timeString);
    void updateDescriptionField(String description);
    void updateProblemHints();
    void showDatePicker(Calendar calendar);
    void showTimePicker(Calendar calendar);

    void updateViewProblemError();
    void commitProblemSuccess();

    void commitProblemFailure();
    void updateRecordList(RecordTreeSet records);
}
