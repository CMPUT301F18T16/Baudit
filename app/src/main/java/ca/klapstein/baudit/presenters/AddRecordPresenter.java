package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.views.AddRecordView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Record} via a {@code EditRecordView}.
 *
 * @see ca.klapstein.baudit.data.Record
 * @see AddRecordView
 */
public class AddRecordPresenter extends RecordPresenter<AddRecordView> {
    private static final String TAG = "AddRecordPresenter";

    public AddRecordPresenter(AddRecordView view) {
        super(view);
    }
}
