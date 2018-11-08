package ca.klapstein.baudit.views;

public interface CreateAccountView extends AccountView {
    void setEmailError();

    void setUserNameError();

    void setPassword(String string);

    void setPasswordError();

    void commitCreateAccount();
}
