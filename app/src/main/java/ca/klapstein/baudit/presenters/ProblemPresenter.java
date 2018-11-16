package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.views.ProblemView;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Problem} via a {@code ProblemView}.
 *
 * @see ca.klapstein.baudit.data.Problem
 * @see ProblemView
 */
public class ProblemPresenter extends Presenter<ProblemView> {

    public ProblemPresenter(ProblemView view, Context context) {
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

            view.updateTitleField("");
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

    public void saveTitleClicked(String newTitle) {
        // TODO: Save the new title
        view.updateTitleField(newTitle);
    }

    public void saveDescriptionClicked(String newDescription) {
        // TODO: Save the new description
        view.updateDescriptionField(newDescription);
    }
}
