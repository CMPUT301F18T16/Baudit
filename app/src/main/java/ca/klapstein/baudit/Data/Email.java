package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

/**
 * Data class representing a Baudit's {@code User}'s email.
 *
 * @see User
 */
public class Email {
    private static final String TAG = "Email";

    @NonNull
    private String email;

    public Email(@NonNull String email) {
        // TODO: add validation of email string
        this.email = email;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        // TODO: add validation of email string
        this.email = email;
    }

    @Override
    public int hashCode() {
        int result = 19;
        result = 31 * result + this.getEmail().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass().equals(Email.class)) {
            Email otherEmail = (Email) obj;
            return this.getEmail().equals(otherEmail.getEmail());
        } else {
            return false;
        }
    }
}
