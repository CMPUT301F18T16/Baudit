package ca.klapstein.baudit;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;

/**
 * Data class representing the contact info of a {@code User}.
 *
 * This is viewed by both the {@code CareProvider} and the {@code Patients}.
 *
 * @see User
 */
class ContactInfo {
    @NonNull
    private ContactsContract.CommonDataKinds.Email email;
    @NonNull
    private ContactsContract.CommonDataKinds.Phone phone;

    public ContactsContract.CommonDataKinds.Phone getPhone() {
        return phone;
    }

    public void setPhone(ContactsContract.CommonDataKinds.Phone phone) {
        this.phone = phone;
    }

    public ContactsContract.CommonDataKinds.Email getEmail() {
        return email;
    }

    public void setEmail(ContactsContract.CommonDataKinds.Email email) {
        this.email = email;
    }
}
