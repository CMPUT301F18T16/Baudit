package ca.klapstein.baudit.views;

/**
 * Abstract MVP view base class.
 */
public interface View {

    /**
     * All Activities must include an update onto their onStart method to send a
     * notify their startup to their presenter.
     * <p>
     * i.e, when the view starts it notifies the presenter to query for updates on the model.
     * If such updates exist the presented gives the updates back to the view.
     */
    void onStart();
}
