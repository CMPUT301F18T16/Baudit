package ca.klapstein.baudit.views;

public interface CareProviderHomeView extends View {
    void startScanQRCode();
    void updateScanQRCodeError();
    void updatePatientCount(int patientNumber);
    void updateRemovePatientError();

    void updateUsernameDisplay(String username);

    void updateEmailDisplay(String email);

    void updateAccountLoadError();

    void updateList();
}
