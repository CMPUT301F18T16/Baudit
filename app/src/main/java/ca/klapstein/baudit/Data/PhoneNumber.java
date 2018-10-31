package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

import java.util.regex.Pattern;

public class PhoneNumber {
    private static final String TAG = "PhoneNumber";
    private static final String VALID_CANADIAN_PHONE_NUMBER
        = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

    @NonNull
    private String phoneNumber;

    public PhoneNumber(@NonNull String phoneNumber) throws InvalidPhoneNumberException {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new InvalidPhoneNumberException();
        }
    }

    @NonNull
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new InvalidPhoneNumberException();
        }
    }

    private boolean isValidPhoneNumber(String number) {
        Pattern p = Pattern.compile(VALID_CANADIAN_PHONE_NUMBER);
        return p.matcher(number).matches();
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

    private class InvalidPhoneNumberException extends Exception {}
}
