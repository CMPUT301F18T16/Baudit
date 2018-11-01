package ca.klapstein.baudit.activities;

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
}
