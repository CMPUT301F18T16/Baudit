package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * data class representing a Baudit's {@code User}'s email.
 *
 * @see User
 */
public class Email {
    private static final String TAG = "Email";
    /**
     * Apache commons EmailValidator.
     */
    private static final EmailValidator emailValidator = EmailValidator.getInstance();
    @NonNull
    private String email;

    public Email(String email) {
        if (isValid(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid Email String");
        }
    }

    /**
     * Validate whether a string is a valid email.
     * <p>
     * Wrapping of the Apache Commons EmailValidator.
     *
     * @param email {@code String}
     * @return {@code boolean}
     * @see EmailValidator
     */
    static public boolean isValid(String email) {
        return emailValidator.isValid(email);
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (isValid(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid Email String");
        }
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
