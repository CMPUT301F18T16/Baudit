package ca.klapstein.baudit.views;

public interface EditAccountView extends View {
    void updateFirstNameField(String firstName);

    void updateLastNameField(String lastName);

    void updateEmailField(String email);

    void updatePhoneNumberField(String phoneNumber);

    void commitAccountEditFailure();

    void commitAccountEditSuccess();

}
