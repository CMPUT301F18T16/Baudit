package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

/**
 * Data class representing the contact info of a {@code Account}.
 * <p>
 * This is viewed by both the {@code CareProvider} and the {@code Patients}.
 *
 * @see Account
 */
public class ContactInfo {
    private static final String TAG = "ContactInfo";

    public ContactInfo(@NonNull Email email, @NonNull PhoneNumber phoneNumber) {
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
    }

    @NonNull
    private Email email;
    @NonNull
    private PhoneNumber phone;

    /**
     * Get the {@code PhoneNumber} of the {@code ContactInfo}
     *
     * @return {@code PhoneNumber}
     */
    public PhoneNumber getPhoneNumber() {
        return phone;
    }

    /**
     * Setter for a {@code ContactInfo}'s {@code PhoneNumber}.
     *
     * @param phone {@code PhoneNumber}
     */
    public void setPhoneNumber(PhoneNumber phone) {
        this.phone = phone;
    }

    /**
     * Get the {@code Email} of the {@code ContactInfo}
     *
     * @return {@code Email}
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Setter for a {@code ContactInfo}'s {@code Email}.
     *
     * @param email {@code Email}
     */
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
