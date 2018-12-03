package ca.klapstein.baudit.views;

public interface ProblemRowView extends View {
    void updateProblemTitleText(String title);

    void updateProblemDateText(String date);

    void updateProblemDescriptionText(String description);
}
