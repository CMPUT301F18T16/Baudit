package ca.klapstein.baudit.views;

public interface PatientRowView extends View {
    void updatePatientNameText(String firstName, String lastName);
    void updatePatientUsernameText(String username);
    void updatePatientProblemNum(int problemNum);
}
