package ca.klapstein.baudit.Data;

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

    @Override
    public int hashCode() {
        int result = 21;
        result = 31 * result + this.getEmail().hashCode();
        result = 31 * result + this.getPhoneNumber().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass().equals(ContactInfo.class)) {
            ContactInfo otherContactInfo = (ContactInfo) obj;
            return this.getEmail().equals(otherContactInfo.getEmail()) &&
                    this.getPhoneNumber().equals(otherContactInfo.getPhoneNumber());
        } else {
            return false;
        }
    }
}
