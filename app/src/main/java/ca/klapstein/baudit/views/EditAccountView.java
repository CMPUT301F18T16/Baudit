package ca.klapstein.baudit.views;

public interface EditAccountView extends AccountView {
    void setEmailError();

    void setUserNameError();

    void commitEditAccount();
}
