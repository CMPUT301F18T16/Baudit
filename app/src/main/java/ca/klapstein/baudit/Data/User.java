package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

/**
 * Abstract class representing a basic account/user of Baudit.
 */
public abstract class User {
    @NonNull
    private ContactInfo contactInfo;
    @NonNull
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
