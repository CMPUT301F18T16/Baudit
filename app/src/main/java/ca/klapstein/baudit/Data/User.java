package ca.klapstein.baudit.Data;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.TimeZone;

/**
 * Abstract class representing a basic account/user of Baudit.
 */
public abstract class User {
    private static final String TAG = "User";

    @NonNull
    private ContactInfo contactInfo;

    @NonNull
    private Username username;

    @NonNull
    private Password password;

    @NonNull
    public Username getUsername() {
        return username;
    }

    public void setUsername(@NonNull Username username) {
        this.username = username;
    }

    @NonNull
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(@NonNull ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @NonNull
    public Password getPassword() {
        return password;
    }

    public void setPassword(@NonNull Password password) {
        this.password = password;
    }

    // UC-03.03.01: login into account
    public boolean attemptLogin(Username username, Password password){
        if (this.getUsername().getUsername().equals(username.getUsername())) {
            if (this.getPassword().getPassword().equals(password.getPassword()))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    // UC-07.03.01: add comment record to medical problem
    public void addCommentRecord(Record record, Problem problem){
        RecordTreeSet recordTreeSet = problem.getRecordTreeSet();
        DateFormat date  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        String timestamp = date.format(new Date());
        //
        recordTreeSet.add(record);
        record.username = this.username;
        record.timestamp = timestamp;
    }

    // UC-03.04.01: logout of account TODO
    public void logout() {
        // call LogoutDialog activity
        // go back to MAIN
    }
}
