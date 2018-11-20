package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
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
    private static final String TAG = "ProblemPresenter";
    private final Patient patient;

    private Problem problem;

    public ProblemPresenter(ProblemView view, Context context) {
        super(view, context);
        patient = dataManager.getLoggedInPatient();
    }

    public void viewStarted(int position) {
        if (position == 0) { // If the problem is new
            problem = new Problem("Set Title", "Set description");
        } else { // If the problem exists and is being edited
            problem = (Problem) patient.getProblemTreeSet().toArray()[position];
        }
        view.updateTitleField(problem.getTitle());
        view.updateDateButton(DateFormat.getDateInstance().format(problem.getDate()));
        view.updateTimeButton(DateFormat.getTimeInstance(DateFormat.SHORT).format(problem.getDate()));
        view.updateDescriptionField(problem.getDescription());
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
        return (Record) problem.getRecordTreeSet().toArray()[position];
    }

    public int getRecordCount() {
        return problem.getRecordTreeSet().size();
    }

    public void saveTitleClicked(String newTitle) {
        try {
            problem.setTitle(newTitle);
            view.updateTitleField(newTitle);
        } catch (IllegalArgumentException e) {
            // TODO: error
        }
    }

    public void saveDescriptionClicked(String newDescription) {
        try {
            problem.setDescription(newDescription);
            view.updateDescriptionField(newDescription);
        } catch (IllegalArgumentException e) {
            // TODO: error
        }
    }

    public void commitProblem() {
        try {
            patient.getProblemTreeSet().add(problem);
            dataManager.commitPatient(patient);
            view.commitProblemSuccess();
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "failed saving problem", e);
            view.commitProblemFailure();
        }
    }
}
