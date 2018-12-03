package ca.klapstein.baudit.views;

public interface CareProviderHomeView extends HomeView {
    void startScanQRCode();

    void updateScanQRCodeError();

    void updatePatientCountText();

    void updateRemovePatientError();
}
