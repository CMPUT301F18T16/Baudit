package ca.klapstein.baudit.views;

public interface ProblemListView extends View {
    void updateAccountLoadError();

    void updateList();

    void updateProblemNumber(int problemNumber);
}
