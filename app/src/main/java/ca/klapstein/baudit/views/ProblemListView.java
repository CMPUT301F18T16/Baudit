package ca.klapstein.baudit.views;

import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;

public interface ProblemListView {
    void addProblem(Problem problem);

    void setProblemList(ProblemTreeSet problemTreeSet);
}
