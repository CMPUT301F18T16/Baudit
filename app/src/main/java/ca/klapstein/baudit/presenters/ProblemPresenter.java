package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.views.ProblemView;

/**
 * Abstract MVP presenter for presenting {@code Problem}s.
 *
 * @param <V> the {@code ProblemView} subclass to apply to the presenter.
 * @see Problem
 * @see ProblemView
 */
abstract public class ProblemPresenter<V extends ProblemView> extends Presenter<V> {
    private static final String TAG = "ProblemPresenter";

    protected Problem problem;

    ProblemPresenter(V view) {
        super(view);
    }
}
