package ca.klapstein.baudit.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import ca.klapstein.baudit.activities.ProblemActivity;

import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment {
    public static final String TAG = "datePicker";

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
            (ProblemActivity)getActivity(),
            getArguments().getInt(YEAR),
            getArguments().getInt(MONTH),
            getArguments().getInt(DAY)
        );
    }
}
