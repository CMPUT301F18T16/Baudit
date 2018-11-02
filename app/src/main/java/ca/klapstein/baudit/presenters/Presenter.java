package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.managers.BauditDataManager;
import ca.klapstein.baudit.views.View;

/**
 * Abstract MVP presenter base class.
 *
 * @param <V> the type of MVP view to apply to the presenter
 *
 * @see View
 */
abstract public class Presenter<V extends View> {

    protected V view;
    protected BauditDataManager dataManager;

    public Presenter(V view) {
        this.view = view;
        this.dataManager = new BauditDataManager();
    }
//
//    /**
//     * Hook to define behavior to be done when a Activity notifies that they have started.
//     */
//    abstract void notifyStart();
}
