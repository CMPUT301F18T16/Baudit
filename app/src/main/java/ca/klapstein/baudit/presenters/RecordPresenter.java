package ca.klapstein.baudit.presenters;

import android.content.Context;

import ca.klapstein.baudit.views.RecordView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Record} via a {@code EditRecordView}.
 *
 * @see ca.klapstein.baudit.data.Record
 * @see RecordView
 */
public class RecordPresenter extends Presenter<RecordView> {

    public RecordPresenter(RecordView view, Context context) {
        super(view, context);
    }

    public void viewStarted(int recordId) {
        if (recordId == 0) { // If the record is new
            view.updateTitleField("");
        } else { // If the record exists and is being edited
            // TODO: Replace with real data once implemented
            view.updateTitleField("Test");
            view.updateCommentField("Test");
        }
    }

    public void saveTitleClicked(String newTitle) {
        // TODO: Commit change to database
        view.updateTitleField(newTitle);
    }

    public void saveCommentClicked(String newComment) {
        // TODO: Commit change to database
        view.updateCommentField(newComment);
    }
}
