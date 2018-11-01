package ca.klapstein.baudit.activities;

/**
 * Dialog providing a confirmation prompt to delete a {@code Problem}.
 *
 * @see ca.klapstein.baudit.Data.Problem
 */
public class DeleteProblemDialog extends DeleteDialog {
    private static final String TAG = "DeleteProblemDialog";

    @Override
    public CharSequence getDialogTitle() {
        return "Delete Problem";
    }
}
