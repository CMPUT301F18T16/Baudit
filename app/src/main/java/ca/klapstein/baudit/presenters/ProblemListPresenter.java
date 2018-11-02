package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.views.ProblemListView;
import ca.klapstein.baudit.views.ProblemRowView;

/**
 * MVP presenter for presenting a {@code ProblemTreeSet} on a {@code ProblemListView}.
 *
 * @see ProblemTreeSet
 * @see ProblemListView
 */
public class ProblemListPresenter {
    private static final String TAG = "ProblemListPresenter";

    private ProblemListView view;
    private ProblemTreeSet problemTreeSet;

    public ProblemListPresenter(ProblemListView view) {
        this.view = view;
    }

    public void onBindProblemRowViewAtPosition(ProblemRowView rowView, int position) {
        Problem patient = (Problem)problemTreeSet.toArray()[position];
        // rowView.setProblemTitleText(problem.title);
    }

    public int getProblemCount() {
        return problemTreeSet.size();
    }
}
