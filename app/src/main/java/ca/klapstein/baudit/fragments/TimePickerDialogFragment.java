package ca.klapstein.baudit.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import ca.klapstein.baudit.activities.ProblemActivity;

import java.util.Calendar;

public class TimePickerDialogFragment extends DialogFragment {
    public static final String TAG = "timePicker";

    private static final String HOUR = "hour";
    private static final String MINUTES = "minutes";

    public static TimePickerDialogFragment newInstance(@NonNull Calendar time) {
        TimePickerDialogFragment fragment = new TimePickerDialogFragment();

        Bundle args = new Bundle();
        args.putInt(HOUR, time.get(Calendar.HOUR_OF_DAY));
        args.putInt(MINUTES, time.get(Calendar.MINUTE));
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(
            getActivity(),
            (ProblemActivity)getActivity(),
            getArguments().getInt(HOUR),
            getArguments().getInt(MINUTES),
            false
        );
    }
}
