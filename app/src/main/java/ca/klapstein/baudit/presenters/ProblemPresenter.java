package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.views.ProblemView;

abstract public class ProblemPresenter<T extends ProblemView> {
    private static final String TAG = "ProblemPresenter";

    protected Problem problem;
    private T view;

    public ProblemPresenter(T view) {
        this.view = view;
    }
}
