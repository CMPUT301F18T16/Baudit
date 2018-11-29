package ca.klapstein.baudit.views;

public interface EditAccountView extends View {
    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setEmail(String email);

    void setPhoneNumber(String phoneNumber);

    void commitAccountEditFailure();

    void commitAccountEditSuccess();

}
