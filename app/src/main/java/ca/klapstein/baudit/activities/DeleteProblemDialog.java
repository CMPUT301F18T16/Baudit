package ca.klapstein.baudit.activities;

import android.content.Context;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.presenters.DeleteProblemPresenter;

/**
 * Dialog providing a confirmation prompt to delete a {@code Problem}.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
public class DeleteProblemDialog extends DeleteDialog {
    private static final String TAG = "DeleteProblemDialog";
    private onDeleteProblemListener onDeleteCallBack;

    private DeleteProblemPresenter presenter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DeleteProblemDialog.onDeleteProblemListener) {
            onDeleteCallBack = (DeleteProblemDialog.onDeleteProblemListener) context;
        }
        presenter = new DeleteProblemPresenter(this);
    }

    @Override
    public CharSequence getDialogTitle() {
        return "Delete Problem";
    }

    @Override
    public void deleteConfirm() {

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
