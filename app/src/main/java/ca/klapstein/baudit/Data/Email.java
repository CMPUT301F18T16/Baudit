package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

public class Email {
    @NonNull
    private String email;

    public Email(@NonNull String email){
        // TODO: add validation of email string
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // TODO: add validation of email string
        this.email = email;
    }
}
