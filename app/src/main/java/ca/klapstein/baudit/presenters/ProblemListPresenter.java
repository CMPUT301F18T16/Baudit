package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.views.ProblemListView;

public class ProblemListPresenter {
    private static final String TAG = "ProblemListPresenter";

    private ProblemListView view;
    private ProblemTreeSet problemTreeSet;

    public ProblemListPresenter(ProblemListView view) {
        this.view = view;
    }
}
