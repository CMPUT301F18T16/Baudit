package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber {
    private static final String TAG = "PhoneNumber";
    private static final Pattern phoneNumberPattern =
            Pattern.compile("\\(?(\\d{3})\\)?(?: ?|-?)(\\d{3})(?: ?|-?)(\\d{4})");

    @NonNull
    private String phoneNumber;

    public PhoneNumber(@NonNull String phoneNumber) throws IllegalArgumentException {
        this.setPhoneNumber(phoneNumber);
    }

    /**
     * Check that a string representing a phone number is valid.
     *
     * @param phoneNumber {@code String} the phone number string to test.
     * @return {@code boolean} {@code true} if the phone number is valid, otherwise {@code false}.
     */
    static public boolean isValid(String phoneNumber) {
        Matcher m = phoneNumberPattern.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * Get the {@code String} representation of the {@code PhoneNumber}.
     *
     * @return {@code String}
     */
    @NonNull
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Set the {@code String} representation of the {@code PhoneNumber}.
     *
     * @param phoneNumber {@code String}
     */
    public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (!isValid(phoneNumber)) {
            throw new IllegalArgumentException("invalid phone number");
        } else {
            // strip all non numeric characters
            this.phoneNumber = phoneNumber.replaceAll("[^\\d]", "");
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getPhoneNumber().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass().equals(PhoneNumber.class)) {
            PhoneNumber otherPhoneNumber = (PhoneNumber) obj;
            return this.getPhoneNumber().equals(otherPhoneNumber.getPhoneNumber());
        } else {
            return false;
        }
    }
}
