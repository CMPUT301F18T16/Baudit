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
    protected Context context;

    public Presenter(V view, Context context) {
        this.view = view;
        this.dataManager = new DataModel(context);
        this.context = context;
    }
}
