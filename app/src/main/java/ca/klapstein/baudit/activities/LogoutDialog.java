package ca.klapstein.baudit.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import ca.klapstein.baudit.Data.User;
import ca.klapstein.baudit.R;

/**
 * Dialog providing a logout prompt.
 */
public class LogoutDialog extends DialogFragment {
    private static final String TAG = "LogoutDialog";

    @NonNull
    private User user;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.logout_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        // TODO: more implementation

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
