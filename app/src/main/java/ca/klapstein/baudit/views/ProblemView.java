package ca.klapstein.baudit.views;

import java.util.Calendar;
import java.util.Date;
import ca.klapstein.baudit.data.RecordTreeSet;

public interface ProblemView extends View {
    void updateTitleField(String title);
    void updateDateButton(Date date);
    void updateTimeButton(Date date);
    void updateProblemTime(Date date);
    void updateDescriptionField(String description);
    void updateProblemHints();
    void showDatePicker(Calendar calendar);
    void showTimePicker(Calendar calendar);
    void commitProblemSuccess();
    void commitProblemFailure();
    void updateRecordList(RecordTreeSet records);
}
