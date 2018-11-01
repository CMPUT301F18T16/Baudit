package ca.klapstein.baudit.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.User;

/**
 * Dialog providing a overview of an {@code User}.
 *
 * @see ca.klapstein.baudit.data.User
 */
public class ViewAccountDialog extends DialogFragment {
    private static final String TAG = "ViewAccountDialog";

    @NonNull
    private User user;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_account_create, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        // TODO: more implementation

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
