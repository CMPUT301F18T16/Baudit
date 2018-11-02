package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.managers.BauditDataManager;
import ca.klapstein.baudit.views.View;

/**
 * Abstract MVP presenter acting as a base template to build presenters off of.
 *
 * @param <V>
 */
abstract public class Presenter<V extends View> {

    protected V view;
    protected BauditDataManager dataManager;

    public Presenter(V view) {
        this.view = view;
        this.dataManager = new BauditDataManager();
    }
}
