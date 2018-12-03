package ca.klapstein.baudit.views;

import ca.klapstein.baudit.data.RecordTreeSet;

import java.util.Calendar;
import java.util.Date;

public interface ProblemView extends View {
    void updateTitleField(String title);
    void updateDateButton(Date date);
    void updateTimeButton(Date date);
    void updateProblemTime(Date date);
    void updateDescriptionField(String description);
    void updateProblemHints();
    void showDatePicker(Calendar calendar);
    void showTimePicker(Calendar calendar);

    void updateDeleteRecordError();
    void updateRecordNumber(int recordNumber);
    void updateViewProblemError();
    void commitProblemSuccess();
    void commitProblemFailure();
    void updateRecordList(RecordTreeSet records);
}
