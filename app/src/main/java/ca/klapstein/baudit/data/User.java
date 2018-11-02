package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

/**
 * Abstract class representing a basic account/user of Baudit.
 */
public abstract class User {
    private static final String TAG = "User";

    @NonNull
    private ContactInfo contactInfo;

    @NonNull
    private Username username;

    @NonNull
    private Password password;

    @NonNull
    public Username getUsername() {
        return username;
    }

    public void setUsername(@NonNull Username username) {
        this.username = username;
    }

    @NonNull
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(@NonNull ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @NonNull
    public Password getPassword() {
        return password;
    }

    public void setPassword(@NonNull Password password) {
        this.password = password;
    }
}