package ca.klapstein.baudit.views;

public interface LoginView extends View {
    void onLoginValidationSuccess();
    void onLoginValidationFailure();

    void switchLoginScreen();
}
