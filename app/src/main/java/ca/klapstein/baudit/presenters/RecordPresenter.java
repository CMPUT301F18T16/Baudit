package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.views.RecordView;

/**
 * Abstract MVP presenter for presenting {@code Records}s.
 *
 * @param <V> the {@code RecordView} subclass to apply to the presenter.
 * @see Record
 * @see RecordView
 */
abstract public class RecordPresenter<V extends RecordView> extends Presenter<V> {
    private static final String TAG = "RecordPresenter";

    protected Record record;

    RecordPresenter(V view) {
        super(view);
    }


}
