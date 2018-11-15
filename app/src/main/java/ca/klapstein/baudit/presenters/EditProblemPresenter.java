package ca.klapstein.baudit.presenters;

import android.content.Context;
import java.text.DateFormat;
import java.util.Calendar;

import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.views.EditProblemView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Problem} via a {@code EditProblemView}.
 *
 * @see ca.klapstein.baudit.data.Problem
 * @see EditProblemView
 */
public class EditProblemPresenter extends ProblemPresenter<EditProblemView> {

    public EditProblemPresenter(EditProblemView view, Context context) {
        super(view, context);
    }

    public void viewStarted(int problemId) {
        if (problemId == 0) { // If the problem is new
            Calendar calendar = Calendar.getInstance();

            DateFormat dateFormat = DateFormat.getDateInstance();
            String dateForButton = dateFormat.format(calendar.getTime());
            view.updateDateButton(dateForButton);

            DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
            String timeForButton = timeFormat.format(calendar.getTime());
            view.updateTimeButton(timeForButton);
        } else { // If the problem exists and is being edited
            // TODO: Replace with real data once implemented
            view.updateTitleField("Test");
            view.updateDateButton("November 12, 2018");
            view.updateTimeButton("21:42");
            view.updateDescriptionField("Test");
//            recordTreeSet = dataManager.getRecords();
        }
    }

    public void clickedDateButton() {
        // TODO: Get date from db and convert to calendar
        view.showDatePicker(Calendar.getInstance());
    }

    public void clickedTimeButton() {
        // TODO: Get time from db and convert to calendar
        view.showTimePicker(Calendar.getInstance());
    }

    public Record getRecordAt(int position) {
        return new Record("TODO", "Implement actual records.");
    }

    public int getRecordCount() {
        return 1;
    }
}
