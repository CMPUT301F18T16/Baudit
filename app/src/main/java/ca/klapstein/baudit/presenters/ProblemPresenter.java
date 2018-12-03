package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.util.Log;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.views.ProblemView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Problem} via a {@code ProblemView}.
 *
 * @see ca.klapstein.baudit.data.Problem
 * @see ProblemView
 */
public class ProblemPresenter extends Presenter<ProblemView> {

    private static final String TAG = "ProblemPresenter";

    private Patient patient;
    private Problem problem;

    public ProblemPresenter(ProblemView view, Context context) {
        super(view, context);
    }

    public void viewStarted(int position) {
        try {
            patient = dataManager.getLoggedInPatient();
            if (position == -1) { // If the problem is new
                problem = new Problem(
                        context.getResources().getString(R.string.default_title),
                        context.getResources().getString(R.string.default_description)
                );
                view.updateProblemHints();
            } else { // If the problem exists and is being edited
                problem = (Problem) patient.getProblemTreeSet().toArray()[position];
                view.updateTitleField(problem.getTitle());
                view.updateDescriptionField(problem.getDescription());
            }
            view.updateProblemTime(problem.getDate());
            view.updateRecordList(problem.getRecordTreeSet());
            view.updateRecordNumber(problem.getRecordTreeSet().size());
        } catch (Exception e) {
            Log.e(TAG, "failed to present problem", e);
            view.updateViewProblemError();
        }
    }

    public void clickedDateButton() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(problem.getDate());
        view.showDatePicker(calendar);
    }

    public void clickedTimeButton() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(problem.getDate());
        view.showTimePicker(calendar);
    }

    public void deleteRecordClicked(int recordPosition) {
        try {
            Record deletedRecord = (Record) problem.getRecordTreeSet().toArray()[recordPosition];
            problem.getRecordTreeSet().remove(deletedRecord);
            dataManager.commitPatient(patient);
            view.updateRecordList(problem.getRecordTreeSet());
            view.updateRecordNumber(problem.getRecordTreeSet().size());
        } catch (Exception e) {
            Log.e(TAG, "failed to delete record", e);
            view.updateDeleteRecordError();
        }
    }

    /**
     * Commit a {@code Problem} to storage.
     *
     * @param title       {@code String}
     * @param description {@code String}
     * @param date        {@code Date}
     */
    public void commitProblem(String title, String description, Date date) {
        problem.setTitle(title);
        problem.setDescription(description);
        problem.setDate(date);
        try {
            patient.getProblemTreeSet().add(problem);
            dataManager.commitPatient(patient);
            view.commitProblemSuccess();
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "failed committing Problem", e);
            view.commitProblemFailure();
        }
    }

    /**
     * Return the {@code String} representation of the logged in {@code Patient}.
     *
     * @return {@code String}
     */
    public String getUsername() {
        try {
            patient = dataManager.getLoggedInPatient();
            return patient.getUsername().toString();
        } catch (Exception e) {
            Log.e(TAG, "failed to get patient username", e);
            view.updateViewProblemError();
            return "Unknown User";
        }
    }
}
