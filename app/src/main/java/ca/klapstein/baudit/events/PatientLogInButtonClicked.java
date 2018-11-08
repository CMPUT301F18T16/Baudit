package ca.klapstein.baudit.events;

public class PatientLogInButtonClicked {
    private String username;
    private String password;

    public PatientLogInButtonClicked(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
