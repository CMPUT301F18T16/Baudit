package ca.klapstein.baudit.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import ca.klapstein.baudit.Data.ProblemTreeSet;

/**
 * RecyclerView adapter for integrating a {@code ProblemTreeSet}.
 *
 * @see ca.klapstein.baudit.Data.Problem
 * @see ca.klapstein.baudit.Data.ProblemTreeSet
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
