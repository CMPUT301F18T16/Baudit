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
abstract public class ProblemPresenter<V extends ProblemView> {
    private static final String TAG = "ProblemPresenter";

    protected Problem problem;
    private V view;

    public ProblemPresenter(V view) {
        this.view = view;
    }
}
