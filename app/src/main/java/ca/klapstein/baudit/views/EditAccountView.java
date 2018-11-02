package ca.klapstein.baudit.views;

public interface EditAccountView extends View {
    void setEmail(String string);

    void setEmailError();

    void setUsername(String string);

    void setUserNameError();

    void commitEditAccount();
}
