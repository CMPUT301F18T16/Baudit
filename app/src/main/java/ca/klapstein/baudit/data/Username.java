package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;
import ca.klapstein.baudit.models.RemoteModel;

/**
 * Data class representing a Baudit's {@code Account}'s username.
 *
 * @see Account
 */
public class Username {

    private String username;

    public Username(@NonNull String username) throws IllegalArgumentException {
        this.setUsernameString(username);
    }

    /**
     * Check that a {@code Account}'s username is valid.
     *
     * @param username {@code String} the username string to test.
     * @return {@code boolean} {@code true} if the username is valid, otherwise {@code false}.
     */
    static public boolean isValid(String username) {
        RemoteModel remoteManager = new RemoteModel();
        int len = username.length();
        return len >= 8 && len <= 20 && remoteManager.uniqueID(username);
    }

    /**
     * Get the {@code String} representation of the {@code Username}.
     *
     * @return {@code String}
     */
    @NonNull
    public String getUsernameString() {
        return this.username;
    }

    /**
     * Setter for a {@code Username}'s username.
     *
     * @param username {@code String}
     */
    public void setUsernameString(@NonNull String username) throws IllegalArgumentException {
        if (!isValid(username)) {
            throw new IllegalArgumentException("Invalid username");
        } else {
            this.username = username;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getUsernameString().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass().equals(Username.class)) {
            Username otherUsername = (Username) obj;
            return this.getUsernameString().equals(otherUsername.getUsernameString());
        } else {
            return false;
        }
    }
}
