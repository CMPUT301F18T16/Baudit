package ca.klapstein.baudit.views;

public interface ViewAccountView extends View {
    void setUsername(String username);

    void setEmail(String email);

    void setPhoneNumber(String phoneNumber);
    void setViewAccountError();
}
