package ca.klapstein.baudit.views;

public interface CreateAccountView extends View {
    void updateUsernameError(String message);
    void updateEmailError(String message);
    void updatePhoneNumberError(String message);
    void updatePasswordError(String message);
    void onAccountConfirmed();
}
