package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

public class PhoneNumber {
    private static final String TAG = "PhoneNumber";

    @NonNull
    private String phoneNumber;

    public PhoneNumber(@NonNull String phoneNumber){
        // TODO: add validation of phone number string
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        // TODO: add validation of phone number string
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getPhoneNumber().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (obj.getClass().equals(PhoneNumber.class)){
            PhoneNumber otherPhoneNumber = (PhoneNumber) obj;
            return this.getPhoneNumber().equals(otherPhoneNumber.getPhoneNumber());
        } else {
            return false;
        }
    }
}
