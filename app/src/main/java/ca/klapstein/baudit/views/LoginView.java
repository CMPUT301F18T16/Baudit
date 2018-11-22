package ca.klapstein.baudit.views;

public interface LoginView extends View {
    void onLoginValidationSuccess();
    void onLoginValidationFailure(String message);

    void startScanQRCode();
    void switchLoginScreen();
}
