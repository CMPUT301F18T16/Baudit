package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

public class PhoneNumber {
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
}
