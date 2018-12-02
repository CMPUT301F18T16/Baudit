package ca.klapstein.baudit.views;

public interface ViewAccountView extends View {
    void updateUsernameDisplay(String username);
    void updateNameDisplay(String firstName, String lastName);
    void updateEmailDisplay(String email);
    void updatePhoneNumberDisplay(String phoneNumber);
    void updateViewAccountError();
}
