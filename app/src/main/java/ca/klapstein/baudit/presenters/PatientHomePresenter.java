package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.views.ProblemListView;

/**
 * MVP presenter for presenting a {@code ProblemTreeSet} on a {@code ProblemListView}.
 *
 * @see ProblemTreeSet
 * @see ProblemListView
 */
public class PatientHomePresenter extends Presenter<ProblemListView> {

    private ProblemTreeSet problemTreeSet;

    public PatientHomePresenter(ProblemListView view) {
        super(view);
        problemTreeSet = dataManager.getProblemTreeSet();
    }

    public Problem getProblemAt(int position) {
        return (Problem) problemTreeSet.toArray()[position];
    }

    public int getProblemCount() {
        return problemTreeSet.size();
    }

    public void notifyStarted() {
        view.update();
    }
}
