package ca.klapstein.baudit.Listeners;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * RecyclerTouchListener is based off code by Ravi Tamada provided at:
 * <p>
 * https://www.androidhive.info/2016/01/android-working-with-recycler-view/
 * <p>
 * Provides a {@code ClickListener} interface that then provides stubs for a {@code onClick} and
 * {@code onLongClick} methods for RecyclerViews.
 */
public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    /**
     * A {@code ClickListener} interface that provides stubs for a {@code onClick} and
     * {@code onLongClick} methods for RecyclerViews.
     *
     */
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
