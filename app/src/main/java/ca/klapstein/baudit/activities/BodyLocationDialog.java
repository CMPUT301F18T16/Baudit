package ca.klapstein.baudit.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.BodyPhoto;
import ca.klapstein.baudit.data.BodyPhotoCoords;


/**
 * Dialog providing a prompt to pick a location on a {@code BodyPhoto}
 * thus obtaining a set of {@code BodyPhotoCoords}
 *
 * @see ca.klapstein.baudit.data.BodyPhoto
 * @see ca.klapstein.baudit.data.BodyPhotoCoords
 */
public class BodyLocationDialog extends DialogFragment {
    private static final String TAG = "BodyLocationDialog";

    @NonNull
    private BodyPhoto bodyPhoto;
    private BodyPhotoCoords bodyPhotoCoords;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.body_location_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        // TODO: more implementation

        // TODO: if no BodyPhoto is present prompt the user to provide one

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
