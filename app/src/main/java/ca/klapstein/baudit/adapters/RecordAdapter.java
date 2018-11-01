package ca.klapstein.baudit.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import ca.klapstein.baudit.data.RecordTreeSet;

/**
 * RecyclerView adapter for integrating a {@code RecordTreeSet}.
 *
 * @see ca.klapstein.baudit.data.Record
 * @see ca.klapstein.baudit.data.RecordTreeSet
 */
public class RecordAdapter extends RecyclerTreeSetAdapter<RecordTreeSet> {
    private static final String TAG = "RecordAdapter";

    public RecordAdapter(@NonNull RecordTreeSet recordTreeSet) {
        this.treeSet = recordTreeSet;
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
