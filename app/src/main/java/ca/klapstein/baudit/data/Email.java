package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;
import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.Contract;

/**
 * Data class representing a Baudit's {@code Account}'s email.
 *
 * @see Account
 */
public class Email {

    /**
     * Apache commons EmailValidator.
     */
    private static final EmailValidator emailValidator = EmailValidator.getInstance();

    @NonNull private String email;

    public Email(@NonNull String email) {
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
     * @return {@code boolean} {@code true} if the email is valid, otherwise {@code false}
     * @see EmailValidator
     */
    static public boolean isValid(String email) {
        return emailValidator.isValid(email);
    }

    /**
     * Get the {@code String} representation of the {@code Email}.
     *
     * @return {@code String}
     */
    @NonNull
    public String getEmail() {
        return email;
    }

    /**
     * Set the {@code String} representation of the {@code Email}.
     *
     * @param email {@code String}
     */
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

    @Override @Contract(value = "null -> false", pure = true)
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
