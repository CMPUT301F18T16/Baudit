package ca.klapstein.baudit.views;

public interface EditAccountView extends View {
    void updateFirstNameField(String firstName);

    void updateLastNameField(String lastName);

    void updateEmailField(String email);

    void updatePhoneNumberField(String phoneNumber);

    void updateEmailError(String message);

    void updatePhoneNumberError(String message);

    void commitAccountEditFailure();

    void commitAccountEditSuccess();

}
