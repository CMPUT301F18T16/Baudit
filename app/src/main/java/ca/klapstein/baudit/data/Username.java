package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

/**
 * Data class representing a Baudit's {@code Account}'s username.
 *
 * @see Account
 */
public class Username {

    private String username;

    public Username(@NonNull String username) throws IllegalArgumentException {
        this.setUsername(username);
    }

    /**
     * Check that a {@code Account}'s username is valid.
     *
     * @param username {@code String} the username string to test.
     * @return {@code boolean} {@code true} if the username is valid, otherwise {@code false}.
     */
    static public boolean isValid(@NonNull String username) {
        int len = username.length();
        return 8 <= len && len <= 20;
    }

    /**
     * Get the {@code String} representation of the {@code Username}.
     *
     * @return {@code String}
     */
    @NonNull
    @Override
    public String toString() {
        return this.username;
    }

    /**
     * Set the {@code String} representation of the {@code Username}.
     *
     * @param username {@code String}
     * @throws IllegalArgumentException if the given username string is invalid
     */
    public void setUsername(@NonNull String username) throws IllegalArgumentException {
        if (isValid(username)) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Invalid username");
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.toString().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass().equals(Username.class)) {
            Username otherUsername = (Username) obj;
            return this.toString().equals(otherUsername.toString());
        } else {
            return false;
        }
    }
}
