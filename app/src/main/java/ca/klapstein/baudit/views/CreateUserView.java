package ca.klapstein.baudit.views;

public interface CreateUserView extends View {
    void setEmail(String string);
    void setEmailError();

    void setUsername(String string);
    void setUserNameError();

    void setPassword(String string);
    void setPasswordError();
}
