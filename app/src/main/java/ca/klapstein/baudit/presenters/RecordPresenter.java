package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.RecordView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Record} via a {@code EditRecordView}.
 *
 * @see ca.klapstein.baudit.data.Record
 * @see RecordView
 */
public class RecordPresenter extends Presenter<RecordView> {

    public RecordPresenter(RecordView view) {
        super(view);
    }

    public void viewStarted(int recordId) {

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
