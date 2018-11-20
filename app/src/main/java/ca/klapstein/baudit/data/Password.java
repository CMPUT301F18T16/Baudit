package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

import java.util.regex.Pattern;

/**
 * Data class representing a Baudit's {@code Account}'s password.
 *
 * @see Account
 */
public class Password {
    private static final String TAG = "Password";

    @NonNull
    private String password;

    public Password(@NonNull String password) throws IllegalArgumentException {
        if (isValid(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password String");
        }
    }

    /**
     * Validate whether a string is a valid Password.
     *
     * @param password {@code String}
     * @return {@code boolean} {@code true} if the password is valid, otherwise {@code false}
     */
    static public boolean isValid(String password) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        boolean hasSpecialChar = p.matcher(password).find();
        return !(password.length() < 8 || password.length() > 20 || hasSpecialChar);
    }

    /**
     * Get the {@code String} representation of the {@code Password}.
     *
     * @return {@code String}
     */
    @NonNull
    @Override
    public String toString() {
        return password;
    }

    /**
     * Set the {@code String} representation of the {@code Password}.
     *
     * @param password {@code String}
     */
    public void setPassword(@NonNull String password) {
        if (isValid(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password String");
        }
    }
}
