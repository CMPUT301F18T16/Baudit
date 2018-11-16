package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.models.DataModel;
import ca.klapstein.baudit.views.View;

/**
 * Abstract MVP presenter base class.
 *
 * @param <V> the type of MVP view to apply to the presenter
 * @see View
 */
abstract public class Presenter<V extends View> {
    private static final String TAG = "Presenter";

    protected V view;
    protected DataModel dataManager;

    public Presenter(V view, Context context) {
        this.view = view;
        // TODO: pass context
        this.dataManager = new DataModel(context);
    }
//
//    /**
//     * Hook to define behavior to be done when a Activity notifies that they have started.
//     */
//    abstract void notifyStart();
}
