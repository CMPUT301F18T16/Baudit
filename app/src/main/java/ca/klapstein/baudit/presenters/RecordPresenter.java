package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.views.RecordView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Record} via a {@code EditRecordView}.
 *
 * @see ca.klapstein.baudit.data.Record
 * @see RecordView
 */
public class RecordPresenter extends Presenter<RecordView> {

    private Record record;
    public RecordPresenter(RecordView view, Context context) {
        super(view, context);
    }

    public void viewStarted(int recordId) {
        if (recordId == 0) { // If the record is new
            view.updateTitleField("");
            record = new Record("Set title", "Set comment");
        } else { // If the record exists and is being edited
            // TODO: Replace with real data once implemented
            record = new Record("Set title", "Set comment");
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
//            patient.getProblemTreeSet().add(problem);
//            dataManager.commitPatient(patient);
            view.commitRecordSuccess();
        } catch (IllegalArgumentException e) {
            view.commitRecordFailure();
        }
    }
}
