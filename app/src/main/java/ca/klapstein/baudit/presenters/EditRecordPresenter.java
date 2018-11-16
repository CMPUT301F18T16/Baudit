package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.views.EditRecordView;

/**
 * MVP presenter for presenting and controlling the editing of a {@code Record} via a {@code EditRecordView}.
 *
 * @see ca.klapstein.baudit.data.Record
 * @see EditRecordView
 */
public class EditRecordPresenter extends RecordPresenter<EditRecordView> {
    private static final String TAG = "EditRecordPresenter";

    public EditRecordPresenter(EditRecordView view, Context context) {
        super(view, context);
    }
}
