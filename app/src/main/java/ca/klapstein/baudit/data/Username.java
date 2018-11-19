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
        this.setUsernameString(username);
    }

    /**
     * Check that a {@code Account}'s username is valid.
     *
     * TODO: we are going to have to move the uniqueID checking outside of this class else
     * construction issues will occur in the full implementation
     *
     * @param username {@code String} the username string to test.
     * @return {@code boolean} {@code true} if the username is valid, otherwise {@code false}.
     */
    static public boolean isValid(String username) {
        int len = username.length();
        return len >= 8 && len <= 20;
    }

    @NonNull
    public String getUsernameString() {
        return this.username;
    }

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
