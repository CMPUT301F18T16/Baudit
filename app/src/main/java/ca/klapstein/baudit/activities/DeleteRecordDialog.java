package ca.klapstein.baudit.activities;

import android.content.Context;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.presenters.DeleteRecordPresenter;

/**
 * Dialog providing a confirmation prompt to delete a {@code Record}.
 *
 * @see ca.klapstein.baudit.data.Record
 */
public class DeleteRecordDialog extends DeleteDialog {
    private static final String TAG = "DeleteRecordDialog";
    private onDeleteRecordListener onDeleteCallBack;

    private DeleteRecordPresenter presenter;

    @Override
    public CharSequence getDialogTitle() {
        return "Delete Record";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onDeleteRecordListener) {
            onDeleteCallBack = (onDeleteRecordListener) context;
        }
        presenter = new DeleteRecordPresenter(this);
    }

    @Override
    public void deleteConfirm() {

    }

    /**
     * Interface to provide Activity-Fragment communication.
     * <p>
     * Provides a callback stub when a {@code Record} is deleted.
     */
    public interface onDeleteRecordListener {
        void onDeleteRecord(Record record);
    }
}
