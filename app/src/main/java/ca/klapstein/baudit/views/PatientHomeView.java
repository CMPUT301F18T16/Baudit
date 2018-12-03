package ca.klapstein.baudit.views;

public interface PatientHomeView extends HomeView {
    void updateDeleteProblemError();

    void updateProblemNumber(int problemNumber);
}
