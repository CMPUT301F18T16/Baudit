package ca.klapstein.baudit.Adapters;

import android.support.v7.widget.RecyclerView;

import java.util.TreeSet;

/**
 * Abstract RecyclerView adapter.
 */
abstract class RecyclerTreeSetAdapter<T extends TreeSet> extends RecyclerView.Adapter {
    private static final String TAG = "RecyclerTreeSetAdapter";

    T treeSet;

    @Override
    public int getItemCount() {
        return treeSet.size();
    }
}
