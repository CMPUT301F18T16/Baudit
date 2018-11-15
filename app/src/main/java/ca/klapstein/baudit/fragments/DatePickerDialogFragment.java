package ca.klapstein.baudit.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.Calendar;

import ca.klapstein.baudit.activities.EditProblemActivity;

public class DatePickerDialogFragment extends DialogFragment {

    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";

    public static DatePickerDialogFragment newInstance(@NonNull Calendar date) {
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();

        Bundle args = new Bundle();
        args.putInt(YEAR, date.get(Calendar.YEAR));
        args.putInt(MONTH, date.get(Calendar.MONTH));
        args.putInt(DAY, date.get(Calendar.DAY_OF_MONTH));
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(
            getActivity(),
            (EditProblemActivity)getActivity(),
            getArguments().getInt(YEAR),
            getArguments().getInt(MONTH),
            getArguments().getInt(DAY)
        );
    }
}
