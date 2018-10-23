package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

public class PhoneNumber {
    @NonNull
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
