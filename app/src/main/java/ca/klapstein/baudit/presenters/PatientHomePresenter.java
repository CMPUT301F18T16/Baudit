package ca.klapstein.baudit.presenters;

import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.models.DatabaseModel;
import ca.klapstein.baudit.views.ProblemListView;
import ca.klapstein.baudit.views.ProblemRowView;

/**
 * MVP presenter for presenting a {@code ProblemTreeSet} on a {@code ProblemListView}.
 *
 * @see ProblemTreeSet
 * @see ProblemListView
 */
public class PatientHomePresenter extends Presenter<ProblemListView> {

    private DatabaseModel dbModel;
    private ProblemTreeSet problemTreeSet;

    public PatientHomePresenter(ProblemListView view) {
        super(view);
        dbModel = new DatabaseModel();
        problemTreeSet = dbModel.getTestProblemTreeSet();
    }

    public Problem getProblemAt(int position) {
        return (Problem) problemTreeSet.toArray()[position];
    }

    public int getProblemCount() {
        return problemTreeSet.size();
    }
}
