package ca.klapstein.baudit.presenters;

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

    public Presenter(V view) {
        this.view = view;
        this.dataManager = new DataModel();
    }
//
//    /**
//     * Hook to define behavior to be done when a Activity notifies that they have started.
//     */
//    abstract void notifyStart();
}
