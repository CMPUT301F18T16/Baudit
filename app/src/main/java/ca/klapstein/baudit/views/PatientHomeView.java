package ca.klapstein.baudit.views;

public interface PatientHomeView extends ProblemListView {
    void updateDeleteProblemError();

    void updateUsernameDisplay(String username);

    void updateEmailDisplay(String email);

    void updateAccountLoadError();
    void updateProblemNumber(int problemNumber);
}
