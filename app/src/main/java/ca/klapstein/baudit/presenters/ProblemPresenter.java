package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.views.EditProblemView;

import java.util.Date;

/**
 * Abstract MVP presenter for presenting {@code Problem}s.
 *
 * @param <V> the {@code EditProblemView} subclass to apply to the presenter.
 * @see Problem
 * @see EditProblemView
 */
abstract public class ProblemPresenter<V extends EditProblemView> extends Presenter<V> {
    private static final String TAG = "ProblemPresenter";

    protected Problem problem;

    ProblemPresenter(V view) {
        super(view);
    }

    public boolean validateProblem(String title, String description, Date startDate) {
        return true;
    }
}
