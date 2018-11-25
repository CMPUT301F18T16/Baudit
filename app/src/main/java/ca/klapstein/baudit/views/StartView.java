package ca.klapstein.baudit.views;

public interface StartView extends View {
    void onLoginValidationSuccess(Class homeClass);
    void onLoginValidationFailure(String message);
    void startRegistration();
    void startScanQRCode();
}
