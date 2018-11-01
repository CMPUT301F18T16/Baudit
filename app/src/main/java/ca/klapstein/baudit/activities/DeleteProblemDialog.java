package ca.klapstein.baudit.activities;

import android.content.Context;
import ca.klapstein.baudit.data.Problem;

/**
 * Dialog providing a confirmation prompt to delete a {@code Problem}.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
public class DeleteProblemDialog extends DeleteDialog {
    private static final String TAG = "DeleteProblemDialog";
    private onDeleteProblemListener onDeleteCallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DeleteProblemDialog.onDeleteProblemListener) {
            onDeleteCallBack = (DeleteProblemDialog.onDeleteProblemListener) context;
        }
    }

    @Override
    public CharSequence getDialogTitle() {
        return "Delete Problem";
    }

    /**
     * Interface to provide Activity-Fragment communication.
     * <p>
     * Provides a callback stub when a {@code Problem} is deleted.
     */
    public interface onDeleteProblemListener {
        void onDeleteProblem(Problem problem);
    }
}
