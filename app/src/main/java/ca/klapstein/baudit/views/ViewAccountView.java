package ca.klapstein.baudit.views;

public interface ViewAccountView extends View {
    void updateUsernameDisplay(String username);

    void updateFirstNameDisplay(String firstName);

    void updateLastNameDisplay(String lastName);

    void updateEmailDisplay(String email);

    void updatePhoneNumberDisplay(String phoneNumber);

    void updateViewAccountError();
}
