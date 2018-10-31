package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.klapstein.baudit.Managers.BauditRemoteManager;

/**
 * Data class representing a Baudit's {@code User}'s password.
 *
 * @see User
 */
public class Password {
    private static final String TAG = "Password";
    private static final String VALID_PASSWORD = "[a-zA-Z0-9]{8,20}";

    @NonNull
    private String password;

    public Password(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) throws IllegalArgumentException {
        Pattern p = Pattern.compile(VALID_PASSWORD);
        Matcher m = p.matcher(password);
        if (m.matches()){
            this.password = password;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getPassword().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass().equals(Password.class)) {
            Password otherPassword = (Password) obj;
            return this.getPassword().equals(otherPassword.getPassword());
        } else {
            return false;
        }
    }
}
