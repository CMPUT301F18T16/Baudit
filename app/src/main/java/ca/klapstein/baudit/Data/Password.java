package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

/**
 * Data class representing a Baudit's {@code User}'s password.
 *
 * @see User
 */
public class Password {
    private static final String TAG = "Password";

    @NonNull
    private String password;

    public Password(@NonNull String password){
        // TODO: validate password
        this.password = password;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        // TODO: validate password
        this.password = password;
    }
}
