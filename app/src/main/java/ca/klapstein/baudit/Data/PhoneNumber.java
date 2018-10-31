package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber {
    private static final String TAG = "PhoneNumber";
    private static final String VALID_TEN_DIGIT_PHONE_NUMBER
        = "\\(?(\\d{3})\\)?(?: ?|-?)(\\d{3})(?: ?|-?)(\\d{4})";

    @NonNull
    private String phoneNumber;

    public PhoneNumber(@NonNull String phoneNumber) throws IllegalArgumentException {
        this.setPhoneNumber(phoneNumber);
    }

    @NonNull
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        Pattern p = Pattern.compile(VALID_TEN_DIGIT_PHONE_NUMBER);
        Matcher m = p.matcher(phoneNumber);
        if (m.matches()) {
            this.phoneNumber = m.group(1) + m.group(2) + m.group(3);
        } else {
            throw new IllegalArgumentException();
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
