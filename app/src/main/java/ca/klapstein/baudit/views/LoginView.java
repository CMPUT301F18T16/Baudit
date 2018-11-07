package ca.klapstein.baudit.views;

public interface LoginView extends View {
    void setUserNameError();

    void setPasswordError();

    void setLoginSuccess();
}
