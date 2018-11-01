package ca.klapstein.baudit.activities;

import ca.klapstein.baudit.data.Record;

/**
 * Dialog providing a confirmation prompt to delete a {@code Record}.
 *
 * @see ca.klapstein.baudit.data.Record
 */
public class DeleteRecordDialog extends DeleteDialog {
    private static final String TAG = "DeleteRecordDialog";

    @Override
    public CharSequence getDialogTitle() {
        return "Delete Record";
    }

    public interface onDeleteRecordListener {
        void onDeleteRecord(Record problem);
    }
}
