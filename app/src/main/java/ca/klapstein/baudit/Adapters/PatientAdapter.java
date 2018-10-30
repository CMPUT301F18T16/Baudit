package ca.klapstein.baudit.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import ca.klapstein.baudit.Data.PatientTreeSet;

/**
 * RecyclerView adapter for integrating a {@code PatientTreeSet}.
 *
 * @see ca.klapstein.baudit.Data.Patient
 * @see ca.klapstein.baudit.Data.PatientTreeSet
 */
public class PatientAdapter extends RecyclerTreeSetAdapter<PatientTreeSet> {
    private static final String TAG = "PatientAdapter";


    public PatientAdapter(@NonNull PatientTreeSet patientTreeSet) {
        this.treeSet = patientTreeSet;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }
}
