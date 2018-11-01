package ca.klapstein.baudit.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import ca.klapstein.baudit.data.ProblemTreeSet;

/**
 * RecyclerView adapter for integrating a {@code ProblemTreeSet}.
 *
 * @see ca.klapstein.baudit.data.Problem
 * @see ca.klapstein.baudit.data.ProblemTreeSet
 */
public class ProblemAdapter extends RecyclerTreeSetAdapter<ProblemTreeSet> {
    private static final String TAG = "ProblemAdapter";

    public ProblemAdapter(@NonNull ProblemTreeSet problemTreeSet) {
        this.treeSet = problemTreeSet;
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
