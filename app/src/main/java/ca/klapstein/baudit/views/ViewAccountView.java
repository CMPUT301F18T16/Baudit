package ca.klapstein.baudit.views;

public interface ViewAccountView extends View {
    void updateUsernameDisplay();

    void updateFirstNameDisplay();

    void updateLastNameDisplay();

    void updateEmailDisplay();

    void updatePhoneNumberDisplay();

    void updateViewAccountError();
}
