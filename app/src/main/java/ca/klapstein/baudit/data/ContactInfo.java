package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;
import android.util.Log;

import org.jetbrains.annotations.Contract;

/**
 * Data class representing the contact info of a {@code Account}.
 * <p>
 * This is viewed by both the {@code CareProvider} and the {@code Patient}s.
 *
 * @see Account
 */
public class ContactInfo {

    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private Email email;
    @NonNull private PhoneNumber phoneNumber;

    public ContactInfo(@NonNull String firstName, @NonNull String lastName, @NonNull Email email,
                       @NonNull PhoneNumber phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the first name of the {@code ContactInfo}.
     *
     * @return {@code String}
     */
    public @NonNull String getFirstName() { return this.firstName; }

    /**
     * Setter for a {@code ContactInfo}'s first name.
     *
     * @param firstName {@code String}
     */
    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of the {@code ContactInfo}.
     *
     * @return {@code String}
     */
    public @NonNull String getLastName() {
        return this.lastName;
    }

    /**
     * Setter for a {@code ContactInfo}'s last name.
     *
     * @param lastName {@code String}
     */
    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the {@code Email} of the {@code ContactInfo}.
     *
     * @return {@code Email}
     */
    public @NonNull Email getEmail() {
        return this.email;
    }

    /**
     * Setter for a {@code ContactInfo}'s {@code Email}.
     *
     * @param email {@code Email}
     */
    public void setEmail(@NonNull Email email) {
        this.email = email;
    }

    /**
     * Get the {@code PhoneNumber} of the {@code ContactInfo}.
     *
     * @return {@code PhoneNumber}
     */
    public @NonNull PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Setter for a {@code ContactInfo}'s {@code PhoneNumber}.
     *
     * @param phoneNumber {@code PhoneNumber}
     */
    public void setPhoneNumber(@NonNull PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        int result = 21;
        result = 31 * result + this.getEmail().hashCode();
        result = 31 * result + this.getPhoneNumber().hashCode();
        return result;
    }

    @Override @Contract(value = "null -> false", pure = true)
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass().equals(ContactInfo.class)) {
            ContactInfo otherContactInfo = (ContactInfo) obj;
            String firstname = this.getFirstName();
            firstname.length();
            if (this.getFirstName().equals(otherContactInfo.getFirstName()))
                if (this.getLastName().equals(otherContactInfo.getLastName()))
                    if (this.getEmail().equals(otherContactInfo.getEmail()))
                        if (this.getPhoneNumber().equals(otherContactInfo.getPhoneNumber()))
                            return true;
            return this.getFirstName().equals(otherContactInfo.getFirstName()) &&
                this.getLastName().equals(otherContactInfo.getLastName()) &&
                this.getEmail().equals(otherContactInfo.getEmail()) &&
                this.getPhoneNumber().equals(otherContactInfo.getPhoneNumber());
        } else {
            return false;
        }
    }
}
