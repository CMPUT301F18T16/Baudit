package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

/**
 * Data class representing a Baudit's {@code User}'s username.
 *
 * @see User
 */
public class Username {
    private static final String TAG = "Username";

    @NonNull
    private String username;

    public Username(@NonNull String username) {
        // TODO: validate username
        this.username = username;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        // TODO: validate username
        this.username = username;
    }
}
