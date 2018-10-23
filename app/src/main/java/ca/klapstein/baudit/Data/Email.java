package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;
import android.telephony.PhoneNumberUtils;

public class Email{
    @NonNull
    private String email;

    public Email(@NonNull String email){
        // TODO: add validation of email string
        this.email = email;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        // TODO: add validation of email string
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (obj.getClass().equals(Email.class)){
            Email otherEmail = (Email) obj;
            return this.getEmail().equals(otherEmail.getEmail());
        } else {
            return false;
        }
    }
}
