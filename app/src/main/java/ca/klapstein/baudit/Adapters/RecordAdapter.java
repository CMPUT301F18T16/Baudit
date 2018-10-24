package ca.klapstein.baudit.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * RecyclerView adapter for integrating a {@code RecordTreeSet}.
 *
 * @see ca.klapstein.baudit.Data.RecordTreeSet
 */
public class RecordAdapter extends RecyclerTreeSetAdapter {
    private static final String TAG = "RecordAdapter";

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
