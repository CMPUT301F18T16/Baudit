package ca.klapstein.baudit.Data;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;

/**
 * Data class representing the contact info of a {@code User}.
 * <p>
 * This is viewed by both the {@code CareProvider} and the {@code Patients}.
 *
 * @see User
 */
public class ContactInfo {
    private static final String TAG = "ContactInfo";

    @NonNull
    private Email email;
    @NonNull
    private PhoneNumber phone;

    public PhoneNumber getPhoneNumber() {
        return phone;
    }

    public void setPhoneNumber(PhoneNumber phone) {
        this.phone = phone;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
