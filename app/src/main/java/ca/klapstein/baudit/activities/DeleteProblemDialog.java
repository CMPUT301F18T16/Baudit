package ca.klapstein.baudit.activities;

import android.content.Context;
import ca.klapstein.baudit.presenters.DeleteProblemPresenter;

/**
 * Dialog providing a confirmation prompt to delete a {@code Problem}.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
public class DeleteProblemDialog extends DeleteDialog {
    private static final String TAG = "DeleteProblemDialog";
    private DeleteProblemPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new DeleteProblemPresenter(this, getContext());
    }

    @Override
    public CharSequence getDialogTitle() {
        return "Delete Problem";
    }

    @Override
    public void deleteConfirm() {

    }
}
