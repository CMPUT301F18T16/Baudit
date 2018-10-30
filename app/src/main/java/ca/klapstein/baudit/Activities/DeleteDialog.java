package ca.klapstein.baudit.Activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import ca.klapstein.baudit.R;

/**
 * Abstract class defining the basis for {@code DeleteProblemDialog} and {@code DeleteRecordDialog}.
 *
 * @see DeleteProblemDialog
 * @see DeleteRecordDialog
 */
abstract public class DeleteDialog extends DialogFragment {
    private static final String TAG = "DeleteDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.delete_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle(getDialogTitle());
        // TODO: more implementation

        // Create the AlertDialog object and return it
        return builder.create();
    }

    /**
     * Get the Title to be set in {@code onCreateDialog}'s {@code AlertDialog.Builder}.
     *
     * @return {@code CharSequence}
     */
    abstract public CharSequence getDialogTitle();
}
