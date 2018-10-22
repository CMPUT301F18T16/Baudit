package ca.klapstein.baudit;

/**
 * Abstract class representing a basic account/user of Baudit.
 */
abstract class User {
    private ContactInfo contactInfo;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
