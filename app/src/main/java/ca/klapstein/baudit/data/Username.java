package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

import ca.klapstein.baudit.managers.BauditRemoteManager;

/**
 * Data class representing a Baudit's {@code User}'s username.
 *
 * @see User
 */

public class Username {
    private static final String TAG = "Username";

    @NonNull
    private String username;

    public Username(@NonNull String username) throws IllegalArgumentException{
        this.setUsername(username);
    }

    @NonNull
    public String getUsername() {
        return this.username;
    }

    public void setUsername(@NonNull String username) throws IllegalArgumentException{
        BauditRemoteManager remoteManager = new BauditRemoteManager();
        int len = username.length();
        if(isValidLength(username)){
            this.username = username;
        } else{
            throw new IllegalArgumentException("Invalid username length, please try again");
        }

        if(remoteManager.uniqueID(username)){
            this.username = username;
        } else{
            throw new IllegalArgumentException("Username not unique, please try again");
        }

        if(isAlphaNumeric(username)){
            this.username = username;
        } else{
            throw new IllegalArgumentException("Invalid username length, please try again");
        }

    }

    public boolean isValidLength(String username){
        return (username.length()>=8 && username.length()<=20);
    }

    public boolean isAlphaNumeric(String filename){
        return filename.matches("^.*[^a-zA-Z0-9 ].*$");
    }


    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getUsername().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass().equals(Username.class)) {
            Username otherUsername = (Username) obj;
            return this.getUsername().equals(otherUsername.getUsername());
        } else {
            return false;
        }
    }
}
