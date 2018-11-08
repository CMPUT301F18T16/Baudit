package ca.klapstein.baudit.events.BauditRemoteManager;

public class LoginVerdict {
    private boolean isLoginValid;

    public LoginVerdict(boolean verdict) {
        this.isLoginValid = verdict;
    }

    public boolean isLoginValid() {
        return this.isLoginValid;
    }
}
