package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.views.RecordView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Record} via a {@code EditRecordView}.
 *
 * @see ca.klapstein.baudit.data.Record
 * @see RecordView
 */
public class RecordPresenter extends Presenter<RecordView> {

    private final Patient patient;
    private Record record;
    private Problem problem;
    public RecordPresenter(RecordView view, Context context) {
        super(view, context);
        // TODO: load patient via other method so that care provider can obtain record aswell
        patient = dataManager.getLoggedInPatient();

    }

    public void viewStarted(int problemId, int recordId) {
        if (problemId == -1) {
            problem = new Problem("Set Title", "Set description");

        } else {
            problem = (Problem) patient.getProblemTreeSet().toArray()[problemId];
        }

        if (recordId == -1) { // If the record is new
            view.updateTitleField("");
            record = new Record("Set title", "Set comment");
        } else { // If the record exists and is being edited
            // TODO: Replace with real data once implemented
            record = (Record) problem.getRecordTreeSet().toArray()[recordId];
        }
        view.updateTitleField(record.getTitle());
        view.updateCommentField(record.getComment());
    }

    public void saveTitleClicked(String newTitle) {
        try {
            record.setTitle(newTitle);
            view.updateTitleField(newTitle);
        } catch (IllegalArgumentException e) {
            // TODO: error
        }
    }

    public void saveCommentClicked(String newComment) {
        try {
            record.setComment(newComment);
            view.updateCommentField(newComment);
        } catch (IllegalArgumentException e) {
            // TODO: error
        }
    }

    public void commitRecord() {
        try {
            // TODO: commit to remote/local
            problem.getRecordTreeSet().add(record);
            patient.getProblemTreeSet().add(problem);
            dataManager.commitPatient(patient);
            view.commitRecordSuccess();
        } catch (IllegalArgumentException e) {
            view.commitRecordFailure();
        }
    }
}
