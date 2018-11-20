package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

/**
 * Data class representing a basic account/account of Baudit.
 */
public class Account implements Comparable<Account> {

    public Account(@NonNull Username username, @NonNull ContactInfo contactInfo,
                   @NonNull Password password) {
        setUsername(username);
        setContactInfo(contactInfo);
        setPassword(password);
    }

    @NonNull
    private ContactInfo contactInfo;

    @NonNull
    private Username username;

    @NonNull
    private Password password;

    /**
     * Get the {@code Username} of the {@code Account}.
     *
     * @return {@code Username}
     */
    @NonNull
    public Username getUsername() {
        return username;
    }

    /**
     * Setter for a {@code Accounts}'s {@code Username}.
     *
     * @param username {@code Username}
     */
    public void setUsername(@NonNull Username username) {
        this.username = username;
    }

    /**
     * Get the {@code ContactInfo} of the {@code Account}.
     *
     * @return {@code ContactInfo}
     */
    @NonNull
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    /**
     * Setter for a {@code Accounts}'s {@code ContactInfo}.
     *
     * @param contactInfo {@code ContactInfo}
     */
    public void setContactInfo(@NonNull ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * Get the {@code Password} of the {@code Account}.
     *
     * @return {@code Password}
     */
    @NonNull
    public Password getPassword() {
        return password;
    }

    /**
     * Setter for a {@code Accounts}'s {@code Password}.
     *
     * @param password {@code Password}
     */
    public void setPassword(@NonNull Password password) {
        this.password = password;
    }

    @Override
    public int compareTo(@NonNull Account account) {
        return this.getUsername().toString()
                .compareTo(account.getUsername().toString());
    }
}