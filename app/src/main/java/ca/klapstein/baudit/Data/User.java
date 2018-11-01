package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.TimeZone;

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