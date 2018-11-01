package ca.klapstein.baudit.views;

public interface CreateUserView {
    void setEmail(String string);
    void setEmailError();

    void setUsername(String string);
    void setUserNameError();

    void setPassword(String string);
    void setPasswordError();
}
