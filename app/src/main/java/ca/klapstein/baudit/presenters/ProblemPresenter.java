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
        patient = dataManager.getLoggedInPatient();
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
            }
            view.updateTitleField(problem.getTitle());
            view.updateDescriptionField(problem.getDescription());
            view.updateRecordList(problem.getRecordTreeSet());
            view.updateProblemTime(problem.getDate());
        } catch (Exception e) {
            Log.e(TAG, "failed to present problem", e);
            // TODO: error
        }
    }

    public void clickedDateButton() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(problem.getDate());
        view.showDatePicker(Calendar.getInstance());
    }

    public void clickedTimeButton() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(problem.getDate());
        view.showTimePicker(calendar);
    }

    public Record getRecordAt(int position) {
        return (Record) problem.getRecordTreeSet().toArray()[position];
    }

    public int getRecordCount() {
        return problem.getRecordTreeSet().size();
    }

    public void deleteRecordClicked(int recordPosition) {
        Record deletedRecord = (Record) problem.getRecordTreeSet().toArray()[recordPosition];
        problem.getRecordTreeSet().remove(deletedRecord);
        dataManager.commitPatient(patient);
        view.updateRecordList(problem.getRecordTreeSet());
    }

    public void commitProblem(int position, String title, String description, Date date) {
        problem.setTitle(title);
        problem.setDescription(description);
        problem.setDate(date);
        try {
            if (position == -1) {
                patient.getProblemTreeSet().add(problem);
            }
            dataManager.commitPatient(patient);
            view.commitProblemSuccess();
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "failed committing Problem", e);
            view.commitProblemFailure();
        }
    }

    public String getUsername() {
        patient = dataManager.getLoggedInPatient();
        if (patient != null) {
            return patient.getUsername().toString();
        } else {
            return "";
        }
    }
}
