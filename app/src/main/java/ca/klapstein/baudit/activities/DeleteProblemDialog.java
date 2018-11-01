package ca.klapstein.baudit.activities;

import ca.klapstein.baudit.data.Problem;

/**
 * Dialog providing a confirmation prompt to delete a {@code Problem}.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
public class DeleteProblemDialog extends DeleteDialog {
    private static final String TAG = "DeleteProblemDialog";

    @Override
    public CharSequence getDialogTitle() {
        return "Delete Problem";
    }

    public interface onDeleteProblemListener {
        void onDeleteProblem(Problem problem);
    }
}
