package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

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
        // TODO: implement
        return true;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        if (isValid(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password String");
        }
    }
}
