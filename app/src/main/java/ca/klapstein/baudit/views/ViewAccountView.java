package ca.klapstein.baudit.views;

public interface ViewAccountView extends View {
    void setUsername();

    void setFirstName();

    void setLastName();
    void setEmail();
    void setPhoneNumber();
    void setViewAccountError();
}
