package ca.klapstein.baudit.activities;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.SlideShowPresenter;
import ca.klapstein.baudit.views.SlideShowView;

import java.util.ArrayList;

/**
 * Dialog providing a "slideshow" of photos to be easily viewed.
 */
public class SlideShowDialog extends DialogFragment implements SlideShowView {
    private static final String TAG = "SlideShowDialog";
    private SlideShowPresenter presenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.slide_show_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        // TODO: more implementation

        presenter = new SlideShowPresenter(this);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void setImages(ArrayList<Bitmap> images) {

    }
}
